package com.mybatis.demo.biz.faq.vo;

import java.util.List;

import lombok.Data;

@Data
public class FaqImg {

	private int imgSeq;
	
	private int faqSeq;
	
	private String faqImg;
	
	private String delYn;
}
