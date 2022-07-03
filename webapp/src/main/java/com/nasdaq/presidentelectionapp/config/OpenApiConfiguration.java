package com.nasdaq.presidentelectionapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

   @Bean
   public OpenAPI customOpenAPI(@Value("${build.version}") String appVersion) {
      return new OpenAPI().info(new Info()
            .title("President election app API")
            .version(appVersion)
            .description("President election application, where users can vote, and see current voting results"));
   }

}
