package me.devyonghee.domainmodelassociation.manytomany

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface BookManyToManyRepository : JpaRepository<BookManyToMany, Long> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Book b where b.author = ?1")
    fun deleteByAuthorIdentifier(id: Long): Int

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Book b where b.author IN ?1")
    fun deleteBulkByAuthors(authors: List<AuthorManyToMany>): Int
}