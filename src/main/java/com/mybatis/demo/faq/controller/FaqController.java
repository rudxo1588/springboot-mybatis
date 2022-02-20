package com.mybatis.demo.faq.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.demo.faq.domain.FaqImgVo;
import com.mybatis.demo.faq.domain.FaqVo;
import com.mybatis.demo.faq.service.FaqService;

import lombok.RequiredArgsConstructor;


/**
* @Controller는 해당 클래스가 Controller임을 나타냄 
*/

@Controller
@RequiredArgsConstructor
public class FaqController {

	private final FaqService faqService;
	
	@RequestMapping("/faqList")
	public ModelAndView faq() {
		ModelAndView mav = new ModelAndView("faq/faqList");
		mav.addObject("faqList",faqService.getFaqAllList());
		return mav;
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
	
	@GetMapping("/getFaqAllList")
	@ResponseBody
	public List<FaqVo> getFaqAllList() {
		return faqService.getFaqAllList();
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
		ModelAndView model = new ModelAndView("faq/faqList");
		return model;
	}
	
	@RequestMapping("/faq/faqDel")
	@ResponseBody
	public ModelAndView faqDelete(@Param(value = "faqSeq")int faqSeq) {
		faqService.faqDelete(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqList");
		return mav;
	}
	
	@RequestMapping("/faq/faqDetail")
	public ModelAndView faqDetail(@Param(value = "faqSeq")int faqSeq) {
		FaqVo faqVo = faqService.faqDetail(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqDetail");
		mav.addObject("faqVo", faqVo);
		return mav;
	}
	
}
