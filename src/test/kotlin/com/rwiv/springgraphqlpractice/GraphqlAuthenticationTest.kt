package com.rwiv.springgraphqlpractice

import com.netflix.graphql.dgs.client.MonoGraphQLClient
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

class GraphqlAuthenticationTest {

    @Test fun `test http auth`() {
        val client = WebClient.create("http://localhost:8080")
        val result = client
            .get()
            .header("Authorization", "1234")
            .retrieve()
            .toEntity(String::class.java)
            .block()

        println(result?.body)
    }

    @Test fun `test graphql auth`() {
        val client = MonoGraphQLClient.createWithWebClient(WebClient.create("http://localhost:8080/graphql")) {
            it.add("Authorization", "1234")
        }

        val result = client.reactiveExecuteQuery("""
            {
                post(id: "1") {
                    title
                }
            }
        """.trimIndent()).block()

//        val result = client.reactiveExecuteQuery("""
//            {
//              account(id: "1") {
//                 id
//                 username
//                 password
//              }
//            }
//        """.trimIndent()).block()

        println(result?.data)
    }
}