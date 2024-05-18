package me.devyonghee.domainmodelassociation.manytomany

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
class AuthorManyToMany(
    var name: String,
    var age: Int,

    // 성능을 위해 `List` 대신 `Set` 사용
    // cascade: 다른 저자에 의해 book 이 참조될 수 있으므로 명시적으로 PERSIST, MERGE 설정
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    // 혼동되지 않도록 명시적으로 연결 테이블 지정
    @JoinTable(
        name = "author_book",
        joinColumns = [JoinColumn(name = "author_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")]
    )
    var books: Set<BookManyToMany> = mutableSetOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
) {
    // 메소드를 통한 연관관계 양쪽 상태 동기화
    fun addBook(book: BookManyToMany) {
        book.authors += this
        books += book
    }

    fun removeBook(book: BookManyToMany) {
        book.authors -= this
        books -= book
    }

    fun clearBooks() {
        books.forEach { it.authors -= this }
        books = emptySet()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuthorManyToMany

        return id != 0L && id == other.id
    }

    // toString 은 기본 속성만 포함되는 것이 좋음, 지연 속성이나 연관관계를 포함하면 SQL 을 실행하거나 LazyInitializationException 발생 가능
    override fun toString(): String {
        return "Author(id=$id)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}