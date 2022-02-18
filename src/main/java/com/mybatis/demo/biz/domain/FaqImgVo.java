package com.mybatis.demo.biz.domain;

import lombok.Data;

@Data
public class FaqImgVo {

	private int imgSeq;
	
	private int faqSeq;
	
	private String faqImg;
	
	private String delYn;
	
	private FaqVo faqVo;
	
}
