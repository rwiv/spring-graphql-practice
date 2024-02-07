package com.rwiv.springgraphqlpractice.resolver

import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.internal.DgsWebMvcRequestData
import com.netflix.graphql.dgs.internal.method.ArgumentResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.context.request.ServletWebRequest

@Component
class DgsServletWebRequestArgumentResolver : ArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == ServletWebRequest::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, dfe: DataFetchingEnvironment): Any {
        return (DgsDataFetchingEnvironment(dfe).getDgsContext().requestData as DgsWebMvcRequestData).webRequest as ServletWebRequest
    }
}