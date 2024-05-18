package me.devyonghee.domainmodelassociation.namedentitygraph

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.NamedAttributeNode
import jakarta.persistence.NamedEntityGraph
import jakarta.persistence.OneToMany

@Entity
@NamedEntityGraph(
    name = "author-books-graph",
    attributeNodes = [NamedAttributeNode("books")]
)
class AuthorNamedEntityGraph(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String,
    var age: Int,
    @OneToMany(cascade = [jakarta.persistence.CascadeType.ALL], mappedBy = "author", orphanRemoval = true)
    var books: List<BookNamedEntityGraph> = mutableListOf(),
) {
}