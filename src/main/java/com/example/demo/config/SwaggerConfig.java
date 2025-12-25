package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {   // ⚠️ method name MUST be api()
        return new OpenAPI()
                .info(new Info()
                        .title("Hostel Roommate Compatibility Matcher API")
                        .description("Swagger documentation for Hostel Roommate Compatibility Matcher")
                        .version("1.0.0")
                )
                .servers(List.of(
                        // 👉 base URL for Swagger
                        new Server().url("https://9180.32procr.amypo.ai/")
                ));
    }
}
