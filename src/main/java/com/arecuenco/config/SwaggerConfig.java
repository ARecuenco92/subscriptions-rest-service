package com.arecuenco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
	@Bean
	public Docket subscriptionsApi() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.groupName("Subscription Api")
				.select()          
				.apis(RequestHandlerSelectors.any())              
				.paths(PathSelectors.regex("/subscription"))                          
				.build();                                           
	}

	@Bean
	public Docket eventApi() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.groupName("Event Api")
				.select()                                  
				.apis(RequestHandlerSelectors.any())              
				.paths(PathSelectors.regex("/event"))                          
				.build();                                           
	}

	@Bean
	public Docket emailApi() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.groupName("Email Api")
				.select()                                  
				.apis(RequestHandlerSelectors.any())              
				.paths(PathSelectors.regex("/email"))                          
				.build();                                           
	}
}
