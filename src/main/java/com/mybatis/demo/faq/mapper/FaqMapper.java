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
	 * faqImg select collcetion이용하여 리스트 가져오기
	 * @return
	 */
	List<FaqVo> faqImgListBySelectCollection(FaqImgVo param);
	
	/**
	 * faqImg join collcetion이용하여 리스트 가져오기
	 * @return
	 */
	List<FaqVo> faqImgListByJoinCollection();
	
	/**
	 * faq, faqImg select collcetion이용하여 전체리스트 가져오기
	 * @return
	 */
	List<FaqVo> getFaqAllList(FaqVo faqVo);
	
	/**
	 * faqImg select association이용하여 리스트 가져오기
	 * @return
	 */
	List<FaqImgVo> faqImgListByAssciation();
	
	/**
	 * faqImg join association이용하여 리스트 가져오기
	 * @return
	 */
	List<FaqImgVo> faqImgListByJoinAssociation();
	
	/**
	 * faq 등록하기
	 * @return
	 */
	int insertFaq(FaqVo faqVo);
	
	/**
	 * faq 삭제하기
	 * @return
	 */
	void deleteFaqByFaqSeq(String faqSeq);
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	void deleteFaqImgByFaqSeq(int faqSeq);
	
	/**
	 * faq 상세페이지로 이동하기
	 * @return
	 */
	FaqVo faqImgListBySelectCollection(int faqSeq);
	
	/**
	 * faq 수정하기
	 * @return
	 */
	int updateFaq(FaqVo faqVo);
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	void deleteFaqImgByImgSeq(int imgSeq);
	
	/**
	 * faqImg 등록하기
	 * @return
	 */
	void insertFaqImg(FaqImgVo faqImgVo);
	
	/**
	 * Max faq_seq구하기
	 * @return int
	 */
	int maxFaqSeq();
	
	
}
