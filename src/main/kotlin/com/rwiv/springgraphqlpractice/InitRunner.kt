package com.rwiv.springgraphqlpractice

import com.rwiv.springgraphqlpractice.persistence.Account
import com.rwiv.springgraphqlpractice.persistence.AccountRepository
import com.rwiv.springgraphqlpractice.persistence.Post
import com.rwiv.springgraphqlpractice.persistence.PostRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitRunner(
    private val postRepository: PostRepository,
    private val accountRepository: AccountRepository,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val a1 = accountRepository.save(Account("user1", "1234"))
        val p1 = postRepository.save(Post("post1", a1))
        val p2 = postRepository.save(Post("post2", a1))
    }
}
