package com.tp.tutorialspoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class TutorialsPointApplication extends SpringBootServletInitializer implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(TutorialsPointApplication.class);

	public static void main(String[] args) {
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");

		SpringApplication.run(TutorialsPointApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/products/")
				.allowedMethods("POST")
				.allowedOrigins("http://localhost:8080");
			}
		};
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TutorialsPointApplication.class);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Execute the code after the Spring Boot application is started");
	}
}
