package com.mybatis.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.demo.biz.domain.FaqImgVo;
import com.mybatis.demo.biz.domain.FaqVo;
import com.mybatis.demo.biz.service.FaqService;
import com.mybatis.demo.biz2.domain.BoardVo;
import com.mybatis.demo.biz2.service.BoardService;

import lombok.RequiredArgsConstructor;


/**
* @Controller는 해당 클래스가 Controller임을 나타냄 
*/

@Controller
@RequiredArgsConstructor
public class MainController {

	private final FaqService faqService;
	
	private final BoardService boardService;
	
	@RequestMapping("/faq")
	public String faq() {
		return "faq/faq";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "board/board";
	}
	
	@GetMapping("/faqImgList")
	@ResponseBody
	public List<FaqVo> selectFaqImgList() {
		return faqService.selectFaqImgList();
	}
	
	@GetMapping("/faqImgList2")
	@ResponseBody
	public List<FaqVo> selectFaqImgList2() {
		return faqService.selectFaqImgList2();
	}
	
	@GetMapping("/faqDetail")
	@ResponseBody
	public List<FaqVo> selectList(FaqVo faqVo) {
		return faqService.selectList();
	}
	
	@RequestMapping("/boardList")
	@ResponseBody
	public List<BoardVo> getBoardList() {
		return boardService.getBoardList();
	}
	
	@RequestMapping("/getFaqOneImg")
	@ResponseBody
	public List<FaqImgVo> getFaqOneImg() {
		return faqService.getFaqOneImg();
	}
	
	@RequestMapping("/getAcFaqImgList")
	@ResponseBody
	public List<FaqImgVo> getAcFaqImgList() {
		return faqService.getAcFaqImgList();
	}
	
}
