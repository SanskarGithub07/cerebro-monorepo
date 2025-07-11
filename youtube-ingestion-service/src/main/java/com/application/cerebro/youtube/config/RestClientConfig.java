package com.application.cerebro.youtube.config;

import com.application.cerebro.youtube.client.PythonTranscriptClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${flask.server.url}")
    private String flaskServerUrl;

    @Bean
    public PythonTranscriptClient pythonTranscriptClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(flaskServerUrl)
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(PythonTranscriptClient.class);
    }
}
