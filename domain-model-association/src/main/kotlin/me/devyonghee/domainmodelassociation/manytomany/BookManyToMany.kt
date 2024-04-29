package me.devyonghee.domainmodelassociation.manytomany

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
class BookManyToMany(
    var title: String,

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    var authors: Set<AuthorManyToMany> = mutableSetOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
)