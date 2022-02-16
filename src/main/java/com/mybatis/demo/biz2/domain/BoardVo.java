package com.mybatis.demo.biz2.domain;

import lombok.Data;

@Data
public class BoardVo {
	
	private int boardSeq;
	
	private String boardTitle;
	
	private String boardContent;
	
	private String boardCreatedt;
}
