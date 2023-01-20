package edu.spring.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@GetMapping("/")
	@ResponseBody
	public String HelloAction() {
		return "Hello World!";
	}
	
	
	@GetMapping("/msg/{content}")
	@ResponseBody
	public String MsgAction(@PathVariable("content") String message) {
		return "Message: " + message;
	}
	
	@GetMapping("/view")
	public String ViewAction() {
		return "viewName";
	}
}
