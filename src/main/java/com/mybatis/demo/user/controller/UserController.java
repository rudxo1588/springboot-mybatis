package com.mybatis.demo.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mybatis.demo.user.service.UserService;
import com.mybatis.demo.user.vo.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// 초기화면 진입시 로그인페이지로
	@RequestMapping("/")
	public String login() {
		return "user/login";
	}
	
	// 회원가입 페이지
	@RequestMapping("/user/join")
	public String join() {
		return "user/join";
	}
	
	// 회원가입
	@PostMapping("signUp")
	public ResponseEntity<Object> signUp(@RequestBody User user, BindingResult bindResult) {
		Map<String, Object> returnMsg = new HashMap<>();
		String msg = "";
		
		if(bindResult.hasErrors()) {
			msg = bindResult.getAllErrors().get(0).getDefaultMessage();
			returnMsg.put("reason", msg);
			
			return new ResponseEntity<>(returnMsg, HttpStatus.BAD_REQUEST);
		} else {
			
			userService.signUp(user);
		}
		
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
