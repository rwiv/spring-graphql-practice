package com.rwiv.springgraphqlpractice.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
}
