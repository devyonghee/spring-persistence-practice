package me.devyonghee.domainmodelassociation.manytomany

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface AuthorManyToManyRepository : JpaRepository<AuthorManyToMany, Long> {

    fun findByName(name: String): AuthorManyToMany?
    fun findByAge(age: Int): List<AuthorManyToMany>

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from AuthorManyToMany a WHERE a.id = ?1")
    fun deleteByIdentifier(id: Long): Int
}