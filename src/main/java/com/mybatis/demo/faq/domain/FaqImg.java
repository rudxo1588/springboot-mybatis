package com.mybatis.demo.faq.domain;

import java.util.List;

import lombok.Data;

@Data
public class FaqImg {

	private int imgSeq;
	
	private int faqSeq;
	
	private String faqImg;
	
	private String delYn;
}
