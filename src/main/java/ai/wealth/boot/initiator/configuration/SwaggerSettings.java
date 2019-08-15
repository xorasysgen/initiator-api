package ai.wealth.boot.initiator.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerSettings {
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/wealth-api/*"))
				.apis(RequestHandlerSelectors.basePackage("ai.wealth.boot.initiator")).build()
				.apiInfo(apiInformation());
	}

	private ApiInfo apiInformation() {
		return new ApiInfo("initiator-api",
				"AI Project in Capital Market and Stock Market powered by  Spring Boot",
				"2.0",
				"Open Source",
				new Contact("Sushil Kumar Bhaskar", "https://init-api.heroku.com", "xorasysgen@yahoo.com"),
				"Api licence", "https://init-api.heroku.com", Collections.emptyList());

	}
}
