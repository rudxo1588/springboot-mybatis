package com.mybatis.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.demo.biz.domain.FaqVo;
import com.mybatis.demo.biz.service.MybatisService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MybatisService mybatisService;
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/selectList")
	@ResponseBody
	public List<FaqVo> selectList() {
		return mybatisService.selectList();
	}
	
}
