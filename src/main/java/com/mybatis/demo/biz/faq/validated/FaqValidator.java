package com.mybatis.demo.biz.faq.validated;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mybatis.demo.biz.faq.vo.Faq;

@Repository
public class FaqValidator implements Validator{

	/*
	 *  동적으로 필수값을 변경해줘야 할때
	 *  해당 추상화 validator를 사용하여 필수값을 변경해 줄 수 있다.
	 */
	// 해당 객체에만 이벤트(유효성검사) 를 진행하겠다.
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Faq.class.equals(clazz);
	}

	// 유효성을 검증하여 메세지를 던진다.
	@Override
	public void validate(Object target, Errors errors) {
		
		Faq faq = (Faq)target;
		System.out.println(faq.getFaqType());
		if("T".equals(faq.getFaqType())) {
			if(faq.getFaqTitle() == null || "".equals(faq.getFaqTitle())) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faqTitle", "notempty", "제목은 빈값일 수 없습니다.");
			}
		} else if("C".equals(faq.getFaqType())) {
			if(faq.getFaqContent() == null || "".equals(faq.getFaqContent())) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faqContent", "notempty", "내용은 빈값일 수 없습니다.");
			}
		} else if("".equals(faq.getFaqType()) || faq.getFaqType() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faqType", "필수값을 선택해 주세요.");
		}
		
		 
	}

}
