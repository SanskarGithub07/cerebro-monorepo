package com.application.cerebro.youtube.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI youtubeIngestionServiceAPI(){
        return new OpenAPI().info(new Info()
                        .title("Youtube Ingestion Service API")
                        .description("This is the REST API for the Youtube Ingestion Service.")
                        .version("v0.0.1")
                        .license(new License().name("MIT License")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Youtube Ingestion Service Wiki Documentation")
                        .url("http://youtube-ingestion-service-dummy-url.com/docs")
                );
    }
}
