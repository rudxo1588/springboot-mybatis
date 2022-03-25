package com.mybatis.demo.biz.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/join")
	public String join() {
		return "/user/join";
	}

	@PostMapping("/signUp")
	@ResponseBody
	public Map<String, Object> signUp(User user) {

		Map<String, Object> map = new HashMap<>();

		int result = userService.signUp(user);

		String resultMsg = "";
		String resultCode = "";

		if(result > 0) {
			resultMsg = "가입되었습니다.";
			resultCode = "200";
		} else {
			resultMsg = "실패했습니다.";
			resultCode = "400";
		}

		map.put("resultMsg", resultMsg);
		map.put("resultCode", resultCode);

		return map;
	}
}
