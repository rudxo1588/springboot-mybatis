package com.mybatis.demo.common.vo;

import lombok.Data;

@Data
public class PagingVo {
	
	// 현재 페이지
	private int pageNum;
	// 페이지당 갯수
	private int pageSize;
	// 현재 페이지 수 
	private int size;
	// 다음 페이지
	private int nextPage;
	// 이전 페이지
	private int prePage;
	// 첫 페이지 여부
	private boolean isFstPage = false;
	//마지막 페이지 여부
	private boolean isLstPage = false;
	// 총 카운트
	private int allCnt;
	
}