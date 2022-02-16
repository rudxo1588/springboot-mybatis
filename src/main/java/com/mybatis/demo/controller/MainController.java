package com.mybatis.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.demo.biz.domain.FaqVo;
import com.mybatis.demo.biz.service.FaqService;
import com.mybatis.demo.biz2.domain.BoardVo;
import com.mybatis.demo.biz2.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final FaqService FaqService;
	
	private final BoardService boardService;
	
	@RequestMapping("/faq")
	public String faq() {
		return "faq/faq";
	}
	
	@RequestMapping("/selectList")
	@ResponseBody
	public List<FaqVo> selectList() {
		return FaqService.selectList();
	}
	
	@RequestMapping("/board")
	public String board() {
		return "board/board";
	}
	
	@RequestMapping("/boardList")
	@ResponseBody
	public List<BoardVo> getBoardList() {
		return boardService.getBoardList();
	}
}
