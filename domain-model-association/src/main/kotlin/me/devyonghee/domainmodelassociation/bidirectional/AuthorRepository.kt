package me.devyonghee.domainmodelassociation.bidirectional

import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Long>