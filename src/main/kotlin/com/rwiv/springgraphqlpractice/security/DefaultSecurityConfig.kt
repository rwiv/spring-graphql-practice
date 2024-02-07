package com.rwiv.springgraphqlpractice.security

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class DefaultSecurityConfig(
    private val authFilter: AuthFilter,
) {

    val permitList = arrayOf(
        "/test",
        "/graphql/**"
    )
    val ignoreList = arrayOf(
        "/graphiql/**",
        "/favicon.ico",
        "/js/**",
    )

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { conf -> conf
            .requestMatchers(*permitList).permitAll()
            .anyRequest().authenticated()
//            .anyRequest().permitAll()
        }

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)

        http.csrf { conf -> conf.disable() }

        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity -> web
            .ignoring()
            .requestMatchers(*ignoreList)
            .requestMatchers(PathRequest.toH2Console())
        }
    }
}
