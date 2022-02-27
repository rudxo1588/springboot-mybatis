package com.mybatis.demo.faq.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/faq")
public class FaqController {

	private final FaqService faqService;
	
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
	 * faqList 페이지로 이동하기
	 * @return
	 */
	@GetMapping("/faqList")
	public ModelAndView faqPage() {
		ModelAndView mv = new ModelAndView("/faq/faqList");
		return mv;
	}
	
	/**
	 * faqList 불러오기
	 * @return
	 */
	@PostMapping("/getFaqList")
	public ModelAndView faqListAsync(FaqVo faqVo) {
		ModelAndView mv = new ModelAndView("/faq/faqListAsync");
		mv.addObject("faqList", faqService.getFaqAllList(faqVo));
		return mv;
	}
	
	/**
	 * faq등록페이지로 이동
	 * @return
	 */
	@GetMapping("/faqWrite")
	public String faqWrite() {
		return "faq/faqWrite";
	}
	
	/**
	 * faq 등록하기
	 * @return
	 */
	@PostMapping("/insertFaq")
	public ResponseEntity<List<String>> insertFaq(@Valid FaqVo faqVo, BindingResult bindingResult, FaqImgVo faqImgVo) {
		String errorMsg = "";
		List<String> errorList = new ArrayList<String>();
		if(bindingResult.hasErrors()) {	// 객체에 선언해준 NotNull에 의해 값이 null이면 true를 반환
			List<ObjectError> objectError = bindingResult.getAllErrors();
			
			for (ObjectError error : objectError) 
				errorMsg = error.getDefaultMessage();
				
			errorList.add(errorMsg);
			errorList.add("E");
			return ResponseEntity.ok().body(errorList);
		} else {
			int result = faqService.insertFaq(faqVo);
			
			if(result > 0) {
				faqService.insertFaqImg(faqImgVo);
			}
			
			errorList.add("등록되었습니다.");
			errorList.add("S");
			return ResponseEntity.ok().body(errorList);
		}
	}
	
	/**
	 * faq 삭제하기
	 * @return
	 */
	@PostMapping("deleteFaq")
	public ModelAndView faqDelete(@Param(value = "faqSeq")String[] faqSeq) {
		faqService.deleteFaq(faqSeq);
		ModelAndView mv = new ModelAndView("/faq/faqList");
		return mv;
	}
	
	/**
	 * faq 상세페이지로 이동하기
	 * @return
	 */
	@GetMapping("/faqDetail")  
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
	@GetMapping("/updateFaqPage")
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
	@PostMapping("/updateFaq")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<List> updateFaq(@Valid FaqVo faqVo,BindingResult bindingResult , FaqImgVo faqImgVo) {
		String errorMsg = "";
		List errorList = new ArrayList();
		if(bindingResult.hasErrors()) {
			List<ObjectError> objectError = bindingResult.getAllErrors();
			
			for(ObjectError error : objectError)
				errorMsg = error.getDefaultMessage();
			
			errorList.add(errorMsg);
			errorList.add("E");
			return ResponseEntity.ok().body(errorList); 
		}
		
		int result = faqService.updateFaq(faqVo);
		if(result > 0) {
			faqService.updateFaqImg(faqImgVo);
		}
		errorList.add("수정되었습니다");
		errorList.add("S");
		return ResponseEntity.ok().body(errorList);
	}
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	@GetMapping("/deleteFaqImg")
	public ModelAndView deleteFaqImg(FaqImgVo faqImgVo) {
		System.out.println(faqImgVo);
		faqService.deleteFaqImgByImgSeq(faqImgVo.getImgSeq());
		ModelAndView mv = new ModelAndView("/faq/faqDetail?faqSeq="+faqImgVo.getFaqSeq());
		
		return mv;
	}
	
	public int maxFaqSeq() {
		return faqService.maxFaqSeq();
	}
	
}

