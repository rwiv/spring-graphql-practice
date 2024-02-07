package com.rwiv.springgraphqlpractice

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping
    fun hello(authentication: Authentication): String {
        println(authentication)
        return "hello"
    }
}
