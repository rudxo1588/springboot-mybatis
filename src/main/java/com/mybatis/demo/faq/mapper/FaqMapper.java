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
	List<FaqVo> faqImgListBySelectCollection(FaqImgVo param);
	
	List<FaqVo> faqImgListByJoinCollection();
	
	List<FaqVo> getFaqAllList();
	
	List<FaqImgVo> faqImgListByAssciation();
	
	List<FaqImgVo> faqImgListByJoinAssociation();
	
	void insertFaqWrite(FaqVo faqVo);
	
	void deleteFaq(int faqSeq);
	
	void deleteFaqImg(int faqSeq);
	
	FaqVo faqImgListBySelectCollection(int faqSeq);
	
	void faqUpdate(FaqVo faqVo);
}
