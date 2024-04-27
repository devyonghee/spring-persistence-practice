package me.devyonghee.domainmodelassociation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.optional.shouldBePresent
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorTest @Autowired constructor(
    private val authorRepository: AuthorRepository,
) : StringSpec({

    "Author Insert Test" {
        // given
        val author = Author("devyonghee", 10)
        val book = Book("practice")
        author.addBook(book)
        // when
        val savedAuthor: Author = authorRepository.save(author)
        // then
        authorRepository.findById(savedAuthor.id) shouldBePresent {
            it.id shouldBe savedAuthor.id
        }
    }
})
