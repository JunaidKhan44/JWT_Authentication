/**
 * 
 */
package com.jwt.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.models.Path;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hp
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				//for filtering only user defind data smart programming
				//.apis(RequestHandlerSelectors.basePackage("org.springframework.boot"))).build();
				.paths(PathSelectors.any())
				.build();
	}
	

	private ApiInfo getInfo(){
		return new ApiInfo("Jwt Backend", "project with junaif", "1.0", "terms & service"
				, new Contact("junaid","http:/login","qz@gmail.com"), 
				"licence","ddd", Collections.emptyList());
		
//by smart programming
//		return ApiInfoBuilder().title("Main Controller")
//				.description("this is api document controller")
//				.version("v.1.1").build();
	}
}
