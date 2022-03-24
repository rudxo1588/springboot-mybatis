package com.mybatis.demo.biz.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.demo.biz.user.service.UserService;
import com.mybatis.demo.biz.user.vo.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/signUp")
	@ResponseBody
	public Map<String, Object> signUp(User user) {

		Map<String, Object> map = new HashMap<>();

		userService.signUp(user);

		return map;
	}
}
