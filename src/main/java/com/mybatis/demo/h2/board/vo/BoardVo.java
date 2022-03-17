package com.mybatis.demo.h2.board.vo;

import lombok.Data;

@Data
public class BoardVo {
	
	private int boardSeq;
	
	private String boardTitle;
	
	private String boardContent;
	
	private String boardCreatedt;
}
