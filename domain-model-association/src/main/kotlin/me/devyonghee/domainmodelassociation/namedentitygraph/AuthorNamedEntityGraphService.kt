package me.devyonghee.domainmodelassociation.namedentitygraph

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorNamedEntityGraphService(
    private val entityManager: EntityManager,
    private val authorNamedEntityGraphRepository: AuthorNamedEntityGraphRepository
) {
    @Transactional
    fun `entityManager 로 entityGraph 사용`(): AuthorNamedEntityGraph {
        val entityGraph = entityManager.getEntityGraph("author-books-graph")
        val properties = mapOf("javax.persistence.fetchgraph" to entityGraph)
        return entityManager.find(AuthorNamedEntityGraph::class.java, 1L, properties)
    }

    @Transactional
    fun `jpql 에도 entityGraph 사용`(): AuthorNamedEntityGraph {
        val entityGraph = entityManager.getEntityGraph("author-books-graph")

        return entityManager.createQuery(
            """
            SELECT a FROM AuthorNamedEntityGraph a WHERE a.id = :id
        """.trimIndent(), AuthorNamedEntityGraph::class.java
        ).setParameter("id", 1L)
            .setHint("javax.persistence.fetchgraph", entityGraph)
            .singleResult
    }

    @Transactional
    fun `criteriaBuilder 에도 사용`(): AuthorNamedEntityGraph {
        val entityGraph = entityManager.getEntityGraph("author-books-graph")

        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(AuthorNamedEntityGraph::class.java)
        val root = criteriaQuery.from(AuthorNamedEntityGraph::class.java)

        criteriaQuery.where(criteriaBuilder.equal(root.get<Long>("id"), 1L))

        return entityManager.createQuery(criteriaQuery)
            .setHint("javax.persistence.fetchgraph", entityGraph)
            .singleResult
    }

    @Transactional
    fun findAllAge45Gt(): List<AuthorNamedEntityGraph> {
        return authorNamedEntityGraphRepository.findAll(AuthorSpecs.isAge45Gt())
    }
}
