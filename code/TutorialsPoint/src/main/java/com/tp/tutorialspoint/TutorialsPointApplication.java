package com.tp.tutorialspoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TutorialsPointApplication extends SpringBootServletInitializer implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(TutorialsPointApplication.class);

	public static void main(String[] args) {
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");

		SpringApplication.run(TutorialsPointApplication.class, args);
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
