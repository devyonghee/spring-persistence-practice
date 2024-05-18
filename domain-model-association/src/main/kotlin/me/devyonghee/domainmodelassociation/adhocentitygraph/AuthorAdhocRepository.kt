package me.devyonghee.domainmodelassociation.adhocentitygraph

import me.devyonghee.domainmodelassociation.bidirectional.Author
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface AuthorAdhocRepository : JpaRepository<Author, Long>,
    JpaSpecificationExecutor<Author> {

    @EntityGraph(attributePaths = ["book"], type = EntityGraph.EntityGraphType.FETCH)
    override fun findAll(): List<Author>

    @EntityGraph(attributePaths = ["book"], type = EntityGraph.EntityGraphType.FETCH)
    fun findByAgeLessThanOrOrderByNameDesc(age: Int): List<Author>

    @EntityGraph(attributePaths = ["book"], type = EntityGraph.EntityGraphType.FETCH)
    override fun findAll(spec: Specification<Author>): List<Author>

    @EntityGraph(attributePaths = ["book"], type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT a FROM Author a WHERE a.age BETWEEN 20 AND 40")
    fun fetchAllAgeBetween20And40();
}