package com.rwiv.springgraphqlpractice.persistence

import jakarta.persistence.*

@Entity
@Table(name = "post")
class Post(

    @Column
    val title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    val author: Account,

    id: Long? = null,
) : BaseEntity(id)
