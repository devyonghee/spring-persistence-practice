package me.devyonghee.domainmodelassociation.manytomany

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManyToManyService(
    private val authorManyToManyRepository: AuthorManyToManyRepository,
    private val bookManyToManyRepository: BookManyToManyRepository
) {
    @Transactional
    fun deleteAuthorById(id: Long) {
        val author: AuthorManyToMany = authorManyToManyRepository.findByName("name")
            ?: throw IllegalArgumentException("Author not found")

        bookManyToManyRepository.deleteByAuthorIdentifier(author.id)
        authorManyToManyRepository.deleteByIdentifier(author.id)
    }

    // 영속성 컨텍스트에 로드를 하지 않음
    @Transactional
    fun `영속성 컨텍스트에 로드를 하지 않음`() {
        val authors: List<AuthorManyToMany> = authorManyToManyRepository.findByAge(34)

        bookManyToManyRepository.deleteBulkByAuthors(authors)
        authorManyToManyRepository.deleteAllInBatch(authors)
    }

    @Transactional
    fun `영속성 컨텍스트에 로드 된 경우 deleteAllInBatch 사용`() {
        val author: AuthorManyToMany = authorManyToManyRepository.findByName("name")
            ?: throw IllegalArgumentException("Author not found")

        bookManyToManyRepository.deleteAllInBatch(author.books)
        authorManyToManyRepository.deleteAllInBatch(listOf(author))
    }
}