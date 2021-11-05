package com.tp.tutorialspoint.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@Value("${spring.application.name}")
	private String appName;
	
	@Value("${developerBy}")
	private String developerBy;
	
	@Value("${developYear:1996}")
	private String developeYear;
	
	@RequestMapping(method = RequestMethod.GET, path = "/" )
	@ResponseBody
	public String welcomePage() {
		return "Welcome to Spring Boot !!!</br> By "+developerBy+" @ "+appName+"-"+developeYear;
	}
}
