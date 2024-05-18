package me.devyonghee.domainmodelassociation.namedentitygraph

import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface AuthorNamedEntityGraphRepository : JpaRepository<AuthorNamedEntityGraph, Long>,
    JpaSpecificationExecutor<AuthorNamedEntityGraph> {

    @EntityGraph(value = "author-books-graph", type = EntityGraph.EntityGraphType.FETCH)
    override fun findAll(): List<AuthorNamedEntityGraph>

    @EntityGraph(value = "author-books-graph", type = EntityGraph.EntityGraphType.FETCH)
    fun findByAgeLessThanOrOrderByNameDesc(age: Int): List<AuthorNamedEntityGraph>

    @EntityGraph(value = "author-books-graph", type = EntityGraph.EntityGraphType.FETCH)
    override fun findAll(spec: Specification<AuthorNamedEntityGraph>): List<AuthorNamedEntityGraph>

    @EntityGraph(value = "author-books-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT a FROM AuthorAdhocEntityGraph a WHERE a.age BETWEEN 20 AND 40")
    fun fetchAllAgeBetween20And40();
}