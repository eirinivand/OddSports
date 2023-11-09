package com.evandorou.oddsports.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.core.configuration.SpringDocWebMvcConfiguration;
import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({SpringDocConfiguration.class,
        SpringDocWebMvcConfiguration.class,
        SwaggerConfig.class,
        SwaggerUiConfigProperties.class,
        SwaggerUiOAuthProperties.class,
        JacksonAutoConfiguration.class
})
@Configuration
@OpenAPIDefinition(info = @Info(title = "OddSports API",
        description = "An awesome API", version = "v1"))
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("com.evandorou")
                .pathsToMatch("/api/**")
                .build();
    }

}
