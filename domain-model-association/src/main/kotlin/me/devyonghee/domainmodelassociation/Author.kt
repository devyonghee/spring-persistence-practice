package me.devyonghee.domainmodelassociation

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Author(
    var name: String,
    var age: Int,

    // cascade: 부모측에서 자식 측 엔터티로만 상태가 전파되어야 함
    // mappedBy: 양방향 연관관계 특성 부여, 자식 측에 @ManyToOne 지정
    // orphanRemoval: 참조되지 않는 자식들의 삭제 보장
    // 기본적으로 부모 측 엔티티를 가져와도 자식 측의 엔티티를 가져오지 않는 lazy 로딩 전략을 사용, 쿼리 기반에서만 즉시 가져오는 것이 좋음
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "author", orphanRemoval = true)
    var books: List<Book> = emptyList(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
) {


    // 메소드를 통한 연관관계 양쪽 상태 동기화, 동기화하지 않으면 엔티티 상태 전환으로 예상치 못한 동작 발생 가능
    fun addBook(book: Book) {
        book.author = this
        books += book
    }

    fun removeBook(book: Book) {
        book.author = null
        books -= book
    }

    fun clearBooks() {
        books.forEach { it.author = null }
        books = emptyList()
    }

    // toString 은 기본 속성만 포함되는 것이 좋음, 지연 속성이나 연관관계를 포함하면 SQL 을 실행하거나 LazyInitializationException 발생 가능
    override fun toString(): String {
        return "Author(id=$id)"
    }
}