package com.example.demo


import com.expediagroup.graphql.generator.execution.GraphQLContext
import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component


@Component
class CustomContextFactory: GraphQLContextFactory<CustomContext, ServerHttpRequest> {

    override suspend fun generateContext(request: ServerHttpRequest): CustomContext {
        val language = request.headers.acceptLanguage.firstOrNull()?.range ?: "en-us"
        return CustomContext(language = language)
    }
}

class CustomContext(val language: String) : GraphQLContext