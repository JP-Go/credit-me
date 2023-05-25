package app.creditme.config

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

  @Bean
  fun publicApi(): GroupedOpenApi? {
    return GroupedOpenApi.builder()
        .group("creditme-public")
        .pathsToMatch("/api/v1/customer/**", "/api/v1/credit/**")
        .build()
  }
}
