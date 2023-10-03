package com.guilherme.delfino.pubsub.subscribers.infra.configuration;

import com.guilherme.delfino.pubsub.subscribers.domain.exceptions.EntityNotFound
import graphql.GraphQLError
import jakarta.validation.ConstraintViolationException
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.core.io.ClassPathResource
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler
import org.springframework.graphql.execution.ErrorType
import org.springframework.graphql.server.webmvc.GraphiQlHandler
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.RouterFunctions
import org.springframework.web.servlet.function.ServerResponse
import javax.naming.InsufficientResourcesException

@ControllerAdvice
class GraphQLControllerAdvice {
    @GraphQlExceptionHandler
    fun handleEntityNotFound(ex: EntityNotFound): GraphQLError? {
        return GraphQLError.newError().errorType(ErrorType.BAD_REQUEST).message(ex.message).build()
    }

    @GraphQlExceptionHandler
    fun handleValidation(ex: ConstraintViolationException): GraphQLError? {
        val message = ex.message;
        return GraphQLError.newError().errorType(ErrorType.BAD_REQUEST).message("Error in validation, message: $message").build()
    }

    @Bean
    @Order(0)
    fun graphiQlRouterFunction(): RouterFunction<ServerResponse> {
        var builder: RouterFunctions.Builder = RouterFunctions.route();
        val graphiQlPage = ClassPathResource("graphiql/index.html");
        val graphiQLHandler = GraphiQlHandler("/api/v1/subscribers", "", graphiQlPage);
        builder = builder.GET("/api/v1/subscribers/docs", graphiQLHandler::handleRequest);
        return builder.build();
    }
}
