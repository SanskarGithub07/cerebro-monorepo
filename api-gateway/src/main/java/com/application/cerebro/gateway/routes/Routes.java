package com.application.cerebro.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> youtubeIngestionService(){
        return GatewayRouterFunctions.route("youtube_ingestion_service")
                .route(RequestPredicates.path("/api/generate"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> youtubeIngestionServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("youtube_ingestion_service_swagger")
                .route(RequestPredicates.path("/aggregate/youtube-ingestion-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(setPath("/api-docs"))
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> llmProcessorServiceRoute(){
        return GatewayRouterFunctions.route("llm_processor_service")
                .route(RequestPredicates.path("/api/**"), HandlerFunctions.http("http://localhost:8081"))
                .route(RequestPredicates.path("/api/generate/**"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> llmProcessorServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("llm_processor_service_swagger")
                .route(RequestPredicates.path("/aggregate/llm-processor-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(setPath("/api-docs"))
                .build();
    }
}
