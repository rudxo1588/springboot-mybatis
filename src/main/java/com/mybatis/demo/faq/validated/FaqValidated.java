package com.mybatis.demo.faq.validated;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mybatis.demo.faq.domain.Faq;

public class FaqValidated implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Faq faq = (Faq)target;
		if(faq.getFaqTitle() == null || "".equals(faq.getFaqTitle())) {
			errors.rejectValue("faqTitle", "빈값 불가능");
		}
//		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faqTitle", "notempty", "제목은 빈값일 수 없습니다.");
		 
	}

}
