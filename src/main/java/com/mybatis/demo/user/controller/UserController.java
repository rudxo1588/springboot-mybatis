package com.mybatis.demo.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mybatis.demo.user.domain.User;

@Controller
public class UserController {


	@RequestMapping("/")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/user/join")
	public String join() {
		return "user/join";
	}
	
//	@PostMapping("login")
//	public ResponseEntity<Object> loginCheck(User user) {
//		
//	}
}
