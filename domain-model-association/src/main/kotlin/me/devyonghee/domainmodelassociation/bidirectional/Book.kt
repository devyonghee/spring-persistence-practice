package me.devyonghee.domainmodelassociation.bidirectional

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import me.devyonghee.domainmodelassociation.namedentitygraph.PublisherNamedSubGraph

@Entity
class Book(
    var title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    // 혼동되지 않도록 JoinColumn 을 통해 의도된 컬럼명을 지정하는 것이 좋음
    @JoinColumn(name = "author_id")
    var author: Author? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    var publisher: Publisher,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {

    // equals, hashCode 메서드를 오버라이딩 하여 자식측에서의 모든 엔티티 상태 전환에서 동일한 결과를 얻을 수 있음
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Author
        // 초기 생성 상태를 비교하기 위해 id 가 0이 아닌 경우만 비교
        return (id != 0L) && id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Book(title='$title', id=$id)"
    }
}