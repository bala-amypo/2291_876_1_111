
// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.servers.Server;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI api() {

//         // Define JWT Security Scheme
//         SecurityScheme securityScheme = new SecurityScheme()
//                 .name("Authorization")
//                 .type(SecurityScheme.Type.HTTP)
//                 .scheme("bearer")
//                 .bearerFormat("JWT")
//                 .in(SecurityScheme.In.HEADER);

//         // Apply security globally
//         SecurityRequirement securityRequirement = new SecurityRequirement()
//                 .addList("Bearer Authentication");

//         return new OpenAPI()
//                 .info(new Info()
//                         .title("Hostel Roommate Compatibility Matcher API")
//                         .description("API documentation for Hostel Roommate Compatibility system")
//                         .version("1.0"))
//                 .servers(List.of(
//                         new Server()
//                                 .url("https://9021.32procr.amypo.ai/")
//                                 .description("Production Server")
//                 ))
//                 .addSecurityItem(securityRequirement)
//                 .schemaRequirement("Bearer Authentication", securityScheme);
//     }
// }
package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "BearerAuth";

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hostel Roommate Compatibility Matcher API")
                        .version("1.0"))
                .servers(List.of(
                        new Server().url("")
                ))
                .addSecurityItem(
                        new SecurityRequirement().addList(SECURITY_SChttps://9180.32procr.amypo.ai/HEME_NAME)
                )
                .components(
                        new Components().addSecuritySchemes(
                                SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
