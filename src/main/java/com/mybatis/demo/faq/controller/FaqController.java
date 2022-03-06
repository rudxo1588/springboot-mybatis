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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.demo.common.vo.PagingVo;
import com.mybatis.demo.faq.domain.Faq;
import com.mybatis.demo.faq.domain.FaqImg;
import com.mybatis.demo.faq.service.FaqImgService;
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
	
	private final FaqImgService faqImgService;


	/**
	 * faqList 페이지로 이동하기
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(Faq faq, PagingVo pagingVo) {
		ModelAndView mv = new ModelAndView("/faq/faqList");
		mv.addObject("list" , faqService.getList(faq));
		return mv;
	}

	/**
	 * faqList 불러오기
	 * @return
	 */
	@PostMapping("/getList")
	@ResponseBody
	public List<Faq> getList(Faq faq, PagingVo pagingVo) {
		return faqService.getList(faq);
	}

	/**
	 * faq등록페이지로 이동
	 * @return
	 */
	@GetMapping("/add")
	public String addPage() {
		return "faq/faqWrite";
	}

	/**
	 * faq 등록하기
	 * @return
	 */
	@PostMapping("/add")
	public void add(@RequestBody @Valid Faq faq) {
		faqService.add(faq);
	}

	/**
	 * faq 삭제하기
	 * @return
	 */
	@PostMapping("deleteList")
	@ResponseBody
	public void deleteList(@Param(value = "faqSeq")String[] faqSeq) {
		faqService.deleteList(faqSeq);
	}

	/**
	 * faq 상세페이지로 이동하기
	 * @return
	 */
	@GetMapping("/getDetail")
	public ModelAndView getDetail(@Param(value = "faqSeq")int faqSeq) {
		Faq faq = faqService.getDetail(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqDetail");
		mav.addObject("faq", faq);
		return mav;
	}

	/**
	 * faq 수정화면으로 이동하기
	 * @return
	 */
	@GetMapping("/edit")
	public ModelAndView edit(@Param(value = "faqSeq")int faqSeq) {
		Faq faq = faqService.getDetail(faqSeq);
		ModelAndView mav = new ModelAndView("faq/faqUpdate");
		mav.addObject("faqInfo", faq);
		return mav;
	}

	/**
	 * faq 수정하기
	 * @return
	 */
	@PostMapping("/modify")
	public void modify(@RequestBody @Valid Faq faq) {
		faqService.modify(faq);
	}
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	@PostMapping("/deleteImg")
	@ResponseBody
	public void deleteImg(FaqImg faqImgVo) {
		faqImgService.delete(faqImgVo.getImgSeq());
	}
	
}

