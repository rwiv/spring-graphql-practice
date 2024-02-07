package com.rwiv.springgraphqlpractice.persistence

import jakarta.persistence.*

@Entity
@Table(name = "account")
class Account(

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.REMOVE])
    val posts: MutableList<Post> = ArrayList(),

    id: Long? = null,
) : BaseEntity(id)
