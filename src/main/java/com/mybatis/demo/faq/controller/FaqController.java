package com.mybatis.demo.faq.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	/**
	 * faqList 페이지로 이동하기
	 * @return
	 */
	@RequestMapping("/faqList")
	public String faq(Model model) {
		model.addAttribute("faqList",faqService.getFaqAllList());
		return "faq/faqList";
	}
	
	/**
	 * faqImg select collcetion이용하여 리스트 가져오기
	 * @return
	 */
	@GetMapping("/getFaqAllList")
	@ResponseBody
	public List<FaqVo> getFaqAllList() {
		return faqService.getFaqAllList();
	}
	
	/**
	 * faqImg select collcetion이용하여 리스트 가져오기
	 * @return
	 */
	@GetMapping("/faqImgListBySelectCollection")
	@ResponseBody
	public List<FaqVo> faqImgListBySelectCollection(FaqImgVo param) {
		return faqService.faqImgListBySelectCollection(param);
	}
	
	/**
	 * faqImg join collcetion이용하여 리스트 가져오기
	 * @return
	 */
	@GetMapping("/faqImgListByJoinCollection")
	@ResponseBody
	public List<FaqVo> faqImgListByJoinCollection() {
		return faqService.faqImgListByJoinCollection();
	}
	
	/**
	 * faqImg select association이용하여 리스트 가져오기
	 * @return
	 */
	@RequestMapping("/faqImgListByAssciation")
	@ResponseBody
	public List<FaqImgVo> faqImgListByAssciation() {
		return faqService.faqImgListByAssciation();
	}
	
	/**
	 * faqImg join association이용하여 리스트 가져오기
	 * @return
	 */
	@RequestMapping("/faqImgListByJoinAssociation")
	@ResponseBody
	public List<FaqImgVo> faqImgListByJoinAssociation() {
		return faqService.faqImgListByJoinAssociation();
	}
	
	/**
	 * faq등록페이지로 이동
	 * @return
	 */
	@RequestMapping("/faqWrite")
	public String faqWrite() {
		return "faq/faqWrite";
	}
	
	/**
	 * faq 등록하기
	 * @return
	 */
	@RequestMapping("/faqInsert")
	@ResponseBody
	public ModelAndView faqInsert(FaqVo faqVo) {
		faqService.faqInsert(faqVo);
		ModelAndView model = new ModelAndView("faq/faqList");
		return model;
	}
	
	/**
	 * faq 삭제하기
	 * @return
	 */
	@RequestMapping("/faq/faqDel")
	@ResponseBody
	public ModelAndView faqDelete(@Param(value = "faqSeq")int faqSeq) {
		faqService.faqDelete(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqList");
		return mav;
	}
	
	/**
	 * faq 상세페이지로 이동하기
	 * @return
	 */
	@RequestMapping("/faq/faqDetail")
	public ModelAndView faqDetail(@Param(value = "faqSeq")int faqSeq) {
		FaqVo faqVo = faqService.faqDetail(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqDetail");
		mav.addObject("faqVo", faqVo);
		return mav;
	}
	
	/**
	 * faq 수정화면으로 이동하기
	 * @return
	 */
	@RequestMapping("/faq/faqUpdatePage")
	public ModelAndView faqUpdatePage(@Param(value = "faqSeq")int faqSeq) {
		FaqVo faqVo = faqService.faqDetail(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqUpdate");
		mav.addObject("faqVo", faqVo);
		return mav;
	}
	
	/**
	 * faq 수정하기
	 * @return
	 */
	@RequestMapping("/faq/faqUpdate")
	@ResponseBody
	public ModelAndView faqUpdate(FaqVo faqVo) {
		faqService.faqUpdate(faqVo);
		ModelAndView mav = new ModelAndView("faq/faqList");
		mav.addObject("faqList",faqService.getFaqAllList());
		return mav;
	}
	
}
