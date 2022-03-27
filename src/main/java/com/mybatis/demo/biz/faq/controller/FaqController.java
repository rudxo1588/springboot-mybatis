package com.mybatis.demo.biz.faq.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.demo.biz.faq.service.FaqImgService;
import com.mybatis.demo.biz.faq.service.FaqService;
import com.mybatis.demo.biz.faq.validated.FaqValidator;
import com.mybatis.demo.biz.faq.vo.Faq;
import com.mybatis.demo.biz.faq.vo.FaqImg;
import com.mybatis.demo.common.vo.PagingVo;

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
	
	@Autowired
	private FaqValidator faqValidator;


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
	public String addPage(Model model, HttpSession session) {
		model.addAttribute("test", "내용 1");
		model.addAttribute("id", session.getAttribute("id"));
		return "faq/faqWrite";
	}
	
	/**
	 * file 등록페이지로 이동
	 * @return
	 */
	@GetMapping("/addByFile")
	public String file() {
		return "faq/file";
	}

	/**
	 * faq 등록하기(@Valid 체크해서 유효성검사)
	 * @return
	 */
	@PostMapping("/add")
	public void add(@RequestBody @Valid Faq faq) {
		faqService.add(faq);
	}
	
	
	// required = true면 해당 requestParam의 value값이 없다면 error
	@PostMapping("/addByMultiPart")
	@ResponseBody
	public String addByMultiPart(@RequestParam(value = "file" , required = false) MultipartFile file, Faq faq) throws Exception {
		System.out.println(file.getOriginalFilename());
		System.out.println(faq.getFaqTitle());
		
		if(!file.isEmpty()) {
			String fileNm = file.getOriginalFilename().replaceAll(" ", "");
			String regex = "^([\\S]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";
			
			if(!fileNm.matches(regex)) {
				throw new Exception("이미지만 등록해주세요");
			}
			
			int idx = fileNm.lastIndexOf(".");
			String extNm = fileNm.substring(idx);
			
			UUID uuid = UUID.randomUUID();	// 파일명이 겹치지 않도록 난수생성하여 파일명 만들어준다.
			String saveFIleNm = uuid + extNm;	
			
			
			File f = new File("C://upload");
			if(!f.exists()) f.mkdirs(); // 폴더가 없다면 폴더 생성
			
			// File.separator / , \ 둘중 하나를 생성
			File filepath = new File("c://upload" + File.separator + saveFIleNm);
			
			file.transferTo(filepath);
		}
		
		return "success";
	}
	
	/**
	 * faq 등록하기(Validator 체크해서 유효성검사)
	 * @return
	 */
	@PostMapping("/addByValidator")
	public ResponseEntity<Object> addByValidator(@RequestBody Faq faq, BindingResult bindResult) {
		faqValidator.validate(faq, bindResult);
		
		Map<String, Object> returnMsg = new HashMap<>();
		String msg = "";
		if(bindResult.hasErrors()) {
			msg = bindResult.getAllErrors().get(0).getDefaultMessage();
			returnMsg.put("reason", msg);
			
			return new ResponseEntity<>(returnMsg, HttpStatus.BAD_REQUEST);
		} else {
			
			int result = faqService.add(faq);
			if(result > 0) {
				msg = "등록되었습니다";
				returnMsg.put("reason", msg);
			} else {
				msg = "등록 실패";
				returnMsg.put("reason", msg);
			}
			
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
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
	@ResponseBody
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

