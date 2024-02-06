package com.rwiv.springgraphqlpractice.fetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.rwiv.springgraphqlpractice.persistence.Account
import com.rwiv.springgraphqlpractice.persistence.AccountRepository

@DgsComponent
class AccountDataFetcher(
    private val accountRepository: AccountRepository,
) {

    @DgsQuery
    fun account(@InputArgument id: String): Account {
        return accountRepository.findById(id.toLong()).get()
    }
}
