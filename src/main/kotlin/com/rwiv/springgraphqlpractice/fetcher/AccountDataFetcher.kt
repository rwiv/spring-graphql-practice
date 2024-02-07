package com.rwiv.springgraphqlpractice.fetcher

import com.netflix.graphql.dgs.*
import com.rwiv.springgraphqlpractice.persistence.Account
import com.rwiv.springgraphqlpractice.persistence.AccountRepository
import org.springframework.security.core.Authentication

@DgsComponent
class AccountDataFetcher(
    private val accountRepository: AccountRepository,
) {

    @DgsQuery
    fun account(@InputArgument id: String): Account {
        return accountRepository.findById(id.toLong()).get()
    }

    @DgsData(parentType = "Account")
    fun password(dfe: DgsDataFetchingEnvironment, auth: Authentication): String {
        if (auth.credentials != "1234") {
            throw RuntimeException("not authenticated")
        }
        val account = dfe.getSource<Account>()
        return account.password
    }
}
