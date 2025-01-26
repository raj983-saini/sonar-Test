package com.example.LoginTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOrigin("*"); // Allow all origins (or restrict as necessary)
        cors.addAllowedMethod("*"); // Allow all HTTP methods
        cors.addAllowedHeader("*"); // Allow all headers
        return cors;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(request -> corsConfiguration());
    }
}
