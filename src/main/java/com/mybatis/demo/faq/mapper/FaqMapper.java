package com.mybatis.demo.faq.mapper;

import java.util.List;

import com.mybatis.demo.faq.domain.FaqImgVo;
import com.mybatis.demo.faq.domain.FaqVo;

/**
 * MySqlDataSourceConfig에서 매퍼스캔을 사용했기때문애 따로 @Mapper 어노테이션이 필요하지 않음.
 */
public interface FaqMapper {

	/**
	 * interface파일에서는 기본 public으로 생성되기 때문에 선언을 하지 않아도 됨
	 * class파일은 protected가 기본
	 */
	
	/**
	 * faq 리스트
	 * @return
	 */
	List<FaqVo> getList(FaqVo faqVo);
	
	/**
	 * faq 등록하기
	 * @return
	 */
	int insert(FaqVo faqVo);
	
	/**
	 * faq 삭제하기
	 * @return
	 */
	void delete(String faqSeq);
	
	/**
	 * faq 상세페이지로 이동하기
	 * @return
	 */
	FaqVo getDetail(int faqSeq);
	
	/**
	 * faq 수정하기
	 * @return
	 */
	int update(FaqVo faqVo);
	
}
