package com.yourvoice.lb.apigw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Value("${read_server}")
    private String readServerUrl;

    @Value("${write_server}")
    private String writeServerUrl;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route requests based on paths
                .route("read_route", r -> r.path("/read/**")
                        .uri(readServerUrl))  // Redirect to read server

                .route("write_route", r -> r.path("/write/**")
                        .uri(writeServerUrl)) // Redirect to write server
                .build();
    }
}
