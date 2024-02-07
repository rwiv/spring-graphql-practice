package com.rwiv.springgraphqlpractice.fetcher

import com.netflix.graphql.dgs.*
import com.rwiv.springgraphqlpractice.persistence.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.context.request.ServletWebRequest

@DgsComponent
class PostDataFetcher(
    private val postRepository: PostRepository,
) {

    @DgsQuery
    fun post(@InputArgument id: String, auth: Authentication, req1: ServletWebRequest, req2: HttpServletRequest): Post {
        println(auth)
        return postRepository.findById(id.toLong()).get()
    }

    /**
     * 알아서 Long -> String으로 변환되므로 해당 @DgsData를 등록할 필요 없다.
     */
//    @DgsData(parentType = "Post")
//    fun id(dfe: DgsDataFetchingEnvironment): String {
//        val post = dfe.getSource<Post>()
//        if (post.id == null) {
//            throw RuntimeException("post id is null")
//        }
//        return post.id.toString()
//    }

    /**
     * query 시에 entity 전체를 직렬화하지 않기에 해당 @DgsData를 등록하지 않아도 N+1 문제는 발생하지 않는다.
     */
//    @DgsData(parentType = "Post")
//    fun author(dfe: DgsDataFetchingEnvironment): Account {
//        val post = dfe.getSource<Post>()
//        return post.author
//    }
}
