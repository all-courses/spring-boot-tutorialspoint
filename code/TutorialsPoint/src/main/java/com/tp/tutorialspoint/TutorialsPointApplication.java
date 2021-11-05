package com.tp.tutorialspoint;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TutorialsPointApplication extends SpringBootServletInitializer implements ApplicationRunner{

	public static void main(String[] args) {
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
