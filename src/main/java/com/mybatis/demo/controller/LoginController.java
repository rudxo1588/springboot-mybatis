package com.mybatis.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybatis.demo.biz.tools.Sha256Tools;
import com.mybatis.demo.biz.user.service.UserService;
import com.mybatis.demo.biz.user.vo.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;
	
	@RequestMapping("/")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user, HttpSession session) throws Exception {
		Sha256Tools sha256 = new Sha256Tools();
		User vo = new User();
		vo = userService.login(user);
		
		if(vo != null) {
			if(sha256.encryptSHA256(user.getPassword()).equals(vo.getPassword())) {
				
				session.setAttribute("user", vo);
			} else {
				throw new Exception("아이디 또는 비밀번호가 맞지 않습니다");
			}
		} else {
			throw new Exception("아이디 또는 비밀번호가 맞지 않습니다");
		}
		
		return "/faq/faqList";
	}
}
