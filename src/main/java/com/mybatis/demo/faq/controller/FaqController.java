package com.mybatis.demo.faq.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	 * faqList 페이지로 이동하기
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/faq/faqList");
		return mv;
	}
	
	/**
	 * faqList 불러오기
	 * @return
	 */
	@PostMapping("/getList")
	public ModelAndView getList(FaqVo faqVo) {
		ModelAndView mv = new ModelAndView("/faq/faqListAsync");
		mv.addObject("faqList", faqService.getList(faqVo));
		return mv;
	}
	
	/**
	 * faq등록페이지로 이동
	 * @return
	 */
	@GetMapping("/add")
	public String faqWrite() {
		return "faq/faqWrite";
	}
	
	/**
	 * faq 등록하기
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<List<String>> add(@RequestBody @Valid FaqVo faqVo, BindingResult bindingResult) {
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
			faqService.insertFaq(faqVo);
			
			errorList.add("등록되었습니다.");
			errorList.add("S");
			return ResponseEntity.ok().body(errorList);
		}
	}
	
	/**
	 * faq 삭제하기
	 * @return
	 */
	@PostMapping("deleteList")
	public ModelAndView deleteList(@Param(value = "faqSeq")String[] faqSeq) {
		faqService.deleteList(faqSeq);
		ModelAndView mv = new ModelAndView("/faq/faqList");
		return mv;
	}
	
	/**
	 * faq 상세페이지로 이동하기
	 * @return
	 */
	@GetMapping("/getDetail")  
	public ModelAndView getDetail(@Param(value = "faqSeq")int faqSeq) {
		FaqVo faqVo = faqService.faqDetail(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqDetail");
		mav.addObject("faqVo", faqVo);
		return mav;
	}
	
	/**
	 * faq 수정화면으로 이동하기
	 * @return
	 */
	@GetMapping("/edit")
	public ModelAndView edit(@Param(value = "faqSeq")int faqSeq) {
		FaqVo faqVo = faqService.faqDetail(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqUpdate");
		mav.addObject("faqVo", faqVo);
		return mav;
	}
	
	/**
	 * faq 수정하기
	 * @return
	 */
	@PostMapping("/modify")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<List> modify(@Valid FaqVo faqVo,BindingResult bindingResult , FaqImgVo faqImgVo) {
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
		
		faqService.modify(faqVo);
		
		errorList.add("수정되었습니다");
		errorList.add("S");
		return ResponseEntity.ok().body(errorList);
	}
}

