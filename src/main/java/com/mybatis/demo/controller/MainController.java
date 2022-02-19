package com.mybatis.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/faqImgListBySelectCollection")
	@ResponseBody
	public List<FaqVo> faqImgListBySelectCollection(FaqImgVo param) {
		return faqService.faqImgListBySelectCollection(param);
	}
	
	@GetMapping("/faqImgListByJoinCollection")
	@ResponseBody
	public List<FaqVo> faqImgListByJoinCollection() {
		return faqService.faqImgListByJoinCollection();
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
	
	@RequestMapping("/faqImgListByAssciation")
	@ResponseBody
	public List<FaqImgVo> faqImgListByAssciation() {
		return faqService.faqImgListByAssciation();
	}
	
	@RequestMapping("/faqImgListByJoinAssociation")
	@ResponseBody
	public List<FaqImgVo> faqImgListByJoinAssociation() {
		return faqService.faqImgListByJoinAssociation();
	}
	
	@RequestMapping("/getAcFaqImgList")
	@ResponseBody
	public List<FaqImgVo> getAcFaqImgList() {
		return faqService.getAcFaqImgList();
	}
	
	@RequestMapping("/faqWrite")
	public String faqWrite() {
		return "faq/faqWrite";
	}
	
	@RequestMapping("/faqInsert")
	@ResponseBody
	public ModelAndView faqInsert(FaqVo faqVo) {
		faqService.faqInsert(faqVo);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++= "+faqVo);
		ModelAndView model = new ModelAndView("faq/faq");
		return model;
	}
	
}
