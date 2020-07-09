package com.stackroute.userservice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Zaithoon M
 * SwaggerConfig - for enabling swagger documentation
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.userservice.controller")).build();
		
	}
	
	private ApiInfo apiInfo() {
		ApiInfo api = new ApiInfo("Spring Boot REST API", "Spring Boot REST API for News App", "1.0",
				"Terms of service", new Contact("Zaithoon M", "9656889617", "zaithoonmohd@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return api;
	}
	

}
