package com.mybatis.demo.common.vo;

import lombok.Data;

@Data
public class PagingVo {
	
	// 현재 페이지
	private int currentPageNo;
	// 페이지당 갯수
	private int pageSize;
	// 화면당 출력 수
	private int recordsPerPage;
	// 총 카운트
	private int totalCnt;
	
	public PagingVo() {
		this.recordsPerPage = 10;
	}
	
	public int getStartPage() {
		return (currentPageNo - 1) * recordsPerPage;
	}

}