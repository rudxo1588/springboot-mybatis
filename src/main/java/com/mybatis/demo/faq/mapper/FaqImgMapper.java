package com.mybatis.demo.faq.mapper;

import com.mybatis.demo.faq.domain.FaqImg;

/**
 * MySqlDataSourceConfig에서 매퍼스캔을 사용했기때문애 따로 @Mapper 어노테이션이 필요하지 않음.
 */
public interface FaqImgMapper {

	/**
	 * faqImg 등록하기
	 * @return
	 */
	int insert(FaqImg vo);
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	void delete(int imgSeq);
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	int deleteByFaqSeq(int faqSeq);
}
