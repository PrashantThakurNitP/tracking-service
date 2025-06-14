package com.logistic.tracking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI trackingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tracking Number Generator API")
                        .description("REST API for generating unique parcel tracking numbers")
                        .version("1.0.0"));
    }
}
