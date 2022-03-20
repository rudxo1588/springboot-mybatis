package com.mybatis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/script")
	public String scriptPage() {
		return "scriptPractice";
	}
}
