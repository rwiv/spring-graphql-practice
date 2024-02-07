package com.rwiv.springgraphqlpractice.resolver

import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.internal.DgsWebMvcRequestData
import com.netflix.graphql.dgs.internal.method.ArgumentResolver
import graphql.schema.DataFetchingEnvironment
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.context.request.ServletWebRequest

@Component
class DsgHttpServletRequestArgumentResolver :ArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == HttpServletRequest::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, dfe: DataFetchingEnvironment): Any {
        val servletWebRequest = (DgsDataFetchingEnvironment(dfe).getDgsContext().requestData as DgsWebMvcRequestData).webRequest as ServletWebRequest
        return servletWebRequest.request
    }
}