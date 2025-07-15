package com.application.cerebro.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> youtubeIngestionService(){
        return GatewayRouterFunctions.route("youtube_ingestion_service")
                .route(RequestPredicates.path("/api/generate"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> llmProcessorServiceRoute(){
        return GatewayRouterFunctions.route("llm_processor_service")
                .route(RequestPredicates.path("/api/get/**"), HandlerFunctions.http("http://localhost:8081"))
                .route(RequestPredicates.path("/api/generate/**"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }
}
