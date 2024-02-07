package com.rwiv.springgraphqlpractice.security

import com.rwiv.springgraphqlpractice.persistence.AccountRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

@Component
class AuthFilter(
    private val accountRepository: AccountRepository,
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val matcher = AntPathRequestMatcher("/**")
        val match = matcher.matcher(request as HttpServletRequest)
        if (!match.isMatch) {
            return chain.doFilter(request, response)
        }

        val securityContext = SecurityContextHolder.getContext()
        if (securityContext?.authentication?.isAuthenticated == true) {
            return chain.doFilter(request, response)
        }

        val reqApiKey: String = request.getHeader("Authorization")
            ?: return chain.doFilter(request, response)

        if (reqApiKey == "1234") {
            val account = accountRepository.findAll().first()
            val successToken = UsernamePasswordAuthenticationToken(
                account.username, account.password, listOf()
            )
            securityContext.authentication = successToken
        }

        chain.doFilter(request, response)
    }
}
