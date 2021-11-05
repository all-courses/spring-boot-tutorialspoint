package com.tp.tutorialspoint.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.tutorialspoint.TutorialsPointApplication;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(TutorialsPointApplication.class);
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Value("${developerBy}")
	private String developerBy;
	
	@Value("${developYear:1996}")
	private String developeYear;
	
	@RequestMapping(method = RequestMethod.GET, path = "/" )
	@ResponseBody
	public String welcomePage() {
		logger.info("Welcome page controller called ...");
		
		return "Welcome to Spring Boot !!!</br> By "+developerBy+" @ "+appName+"-"+developeYear;
	}
}
