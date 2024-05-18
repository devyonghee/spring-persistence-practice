package me.devyonghee.domainmodelassociation.namedentitygraph

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.NamedAttributeNode
import jakarta.persistence.NamedEntityGraph
import jakarta.persistence.NamedSubgraph
import jakarta.persistence.OneToMany

@Entity
@NamedEntityGraph(
    name = "author-books-graph",
    attributeNodes = [NamedAttributeNode("books")],
)
@NamedEntityGraph(
    name = "author-books-publisher-graph",
    attributeNodes = [NamedAttributeNode(value = "books", subgraph = "publisher-subgraph")],
    subgraphs = [NamedSubgraph(name = "publisher-subgraph", attributeNodes = [NamedAttributeNode("publisher")])]
)
class AuthorNamedEntityGraph(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var name: String,
    var age: Int,
    @OneToMany(
        cascade = [CascadeType.ALL],
        mappedBy = "author",
        orphanRemoval = true
    ) var books: List<BookNamedEntityGraph> = mutableListOf(),
)