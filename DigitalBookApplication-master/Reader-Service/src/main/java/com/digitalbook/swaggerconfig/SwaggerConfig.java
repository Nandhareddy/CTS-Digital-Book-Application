package com.digitalbook.swaggerconfig;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket bookApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.digitalbook.controler"))
				.paths(regex("/api/v1/digitalbook/reader/.*"))
				.build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Reader Service REST API for Digital Book Application",
                "Reader Service",
                "1.0",
                "Terms of service",
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", null);
        return apiInfo;
    }

}
