package com.wisely.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/user/home")
	public String user(){
		
		return "user home";
	}
	
	@GetMapping("/user/help")
	public String help(){
		throw new IllegalArgumentException("故意抛出的异常");
	}
	
}
