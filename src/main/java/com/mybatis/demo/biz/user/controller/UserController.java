package com.mybatis.demo.biz.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	/*
	 * join
	 * 회원가입 페이지 이동
	 * return /user/join
	 */
	@GetMapping("/join")
	public String join() {
		return "/user/join";
	}

	/*
	 * signUp
	 * 회원가입
	 * parameter : User
	 * return : map
	 */
	@PostMapping("/signUp")
	@ResponseBody
	public Map<String, Object> signUp(@RequestBody @Valid User user) {

		Map<String, Object> map = new HashMap<>();
		int result = 0;
		// id 중복체크
		int duplicate = userService.getUser(user);
		if(duplicate <= 0) {
			result = userService.signUp(user);
		}

		String resultMsg = "";
		String resultCode = "";

		if(result > 0) {
			resultMsg = "가입되었습니다.";
			resultCode = "200";
		} else {
			resultMsg = "실패했습니다.";
			resultCode = "400";
		}

		if(duplicate > 0) {
			resultMsg = "이미 가입된 ID입니다.";
			resultCode = "400";
		}

		map.put("resultMsg", resultMsg);
		map.put("resultCode", resultCode);

		return map;
	}
}
