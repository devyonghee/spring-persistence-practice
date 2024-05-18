package me.devyonghee.domainmodelassociation.namedentitygraph

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import me.devyonghee.domainmodelassociation.bidirectional.Author

@Entity
class BookNamedEntityGraph(
    var title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    // 혼동되지 않도록 JoinColumn 을 통해 의도된 컬럼명을 지정하는 것이 좋음
    @JoinColumn(name = "author_id")
    var author: Author? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
)