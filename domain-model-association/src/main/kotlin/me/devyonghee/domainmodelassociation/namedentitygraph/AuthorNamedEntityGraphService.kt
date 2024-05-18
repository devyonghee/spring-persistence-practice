package me.devyonghee.domainmodelassociation.namedentitygraph

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorNamedEntityGraphService(
    private val authorNamedEntityGraphRepository: AuthorNamedEntityGraphRepository
) {
    @Transactional
    fun findAllAge45Gt(): List<AuthorNamedEntityGraph> {
        return authorNamedEntityGraphRepository.findAll(AuthorSpecs.isAge45Gt())
    }
}
