package org.acsool.controller;

import org.acsool.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	public TestService testService;
	
	@RequestMapping(value = "")
	public String main() {
		logger.info("TestController : "  + testService.test());
		return "hello world!";
	}
}
