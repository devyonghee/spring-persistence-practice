package me.devyonghee.domainmodelassociation.namedentitygraph

import org.springframework.data.jpa.domain.Specification

private val AGE: Int = 45

object AuthorSpecs {

    fun isAge45Gt(): Specification<AuthorNamedEntityGraph> {
        return Specification { root, _, builder ->
            builder.greaterThan(root.get("age"), AGE)
        }
    }
}