package com.mymate.ApiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route("user_service",router->router.path("/user/**")
                        .filters(filter->filter.rewritePath("/user/v1/(?<segment>.*)", "/ecom_app/api/v1/${segment}"))
                        .uri("http://localhost:9506"))
                .route("product_service",router->router.path("/products/**")
                        .filters(filter->filter.rewritePath("/products/v1/(?<segment>.*)", "/ecom_app/api/v1/${segment}"))
                        .uri("http://localhost:9504"))
                .route("inventory_service",router->router.path("/inventory/**")
                        .filters(filter->filter.rewritePath("/inventory/v1/(?<segment>.*)", "/ecom_app/api/v1/${segment}"))
                        .uri("http://localhost:9503"))
                .build();
    }
}
