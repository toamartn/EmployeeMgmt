package com.mss.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

// @ComponentScan({"com.mss.demo.service"})
// @EnableJpaRepositories(basePackages = "com.mss.demo.dao")
// @EntityScan(basePackages = {"com.mss.demo.entities"})

public class EmpApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpApplication.class, args);
	}
	
	@Override
	public void run(String... strings) throws Exception {			// Similar to init method in servlet
		System.out.println("run method called");
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.mss.demo.controller")).paths(PathSelectors.regex("/.*"))
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo aipInfo = new ApiInfo("Spring Boot REST API", "Spring Boot REST API for Employee Mgmt", "1.0",
				"Termsof Service", new Contact("Amaranath", "https://github.com/", "akondarajula@miraclesoft.com"),
				"Apache License Version 2.0", "https://www/apache.org/licenses/LICENSE-2.0");
		return aipInfo;
	}
}
