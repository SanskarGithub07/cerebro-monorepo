package com.application.cerebro.processor.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI llmProcessorServiceAPI(){
        return new OpenAPI().info(new Info()
                .title("LLM Processor Service API")
                .description("This is the REST API for the LLM Processor Service.")
                .version("v0.0.1")
                .license(new License().name("MIT License")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the LLM Processor Service Wiki Documentation")
                        .url("http://llm-processor-service-dummy-url.com/docs")
                );
    }
}
