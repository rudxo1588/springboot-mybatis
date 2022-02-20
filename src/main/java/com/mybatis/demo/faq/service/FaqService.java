package com.mybatis.demo.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybatis.demo.faq.domain.FaqImgVo;
import com.mybatis.demo.faq.domain.FaqVo;
import com.mybatis.demo.faq.mapper.FaqMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;


/**
 * @Service는 해당 클래스가 Service임을 나타냄 
 */
@Service
/**
 * lombok 사용시 @RequiredArgsConstructor 릉 사용하면 
 * @Autowired를 사용하지 않아도 final or @NonNull 로 의존성 주입이 가능하다.
 */
@RequiredArgsConstructor
public class FaqService {

	@NonNull
	private FaqMapper faqMapper;
	
	/**
	 * faqImg select collcetion이용하여 리스트 가져오기
	 * @return
	 */
	public List<FaqVo> faqImgListBySelectCollection(FaqImgVo param) {
		return faqMapper.faqImgListBySelectCollection(param);
	}
	
	/**
	 * faqImg select collcetion이용하여 리스트 가져오기
	 * @return
	 */
	public List<FaqVo> getFaqAllList() {
		return faqMapper.getFaqAllList();
	}
	
	/**
	 * faqImg join collcetion이용하여 전체리스트 가져오기
	 * @return
	 */
	public List<FaqVo> faqImgListByJoinCollection() {
		return faqMapper.faqImgListByJoinCollection();
	}
	
	/**
	 * faqImg select association이용하여 리스트 가져오기
	 * @return
	 */
	public List<FaqImgVo> faqImgListByAssciation() {
		return faqMapper.faqImgListByAssciation();
	}
	
	/**
	 * faqImg join association이용하여 리스트 가져오기
	 * @return
	 */
	public List<FaqImgVo> faqImgListByJoinAssociation() {
		return faqMapper.faqImgListByJoinAssociation();
	}
	
	/**
	 * faq 등록하기
	 * @return
	 */
	@Transactional
	public void faqInsert(FaqVo faqVo) {
		faqMapper.insertFaqWrite(faqVo);
	}
	
	/**
	 * faq 삭제하기
	 * @return
	 */
	@Transactional
	public void faqDelete(int faqSeq) {
		faqMapper.deleteFaq(faqSeq);
		faqMapper.deleteFaqImg(faqSeq);
	}
	
	/**
	 * faq 상세페이지로 이동하기
	 * @return
	 */
	public FaqVo faqDetail(int faqSeq) {
		return faqMapper.faqImgListBySelectCollection(faqSeq);
	}
	
	/**
	 * faq 수정하기
	 * @return
	 */
	@Transactional
	public void faqUpdate(FaqVo faqVo) {
		faqMapper.faqUpdate(faqVo);
	}
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	@Transactional
	public void faqImgDelete(int imgSeq) {
		faqMapper.deleteFaqImg2(imgSeq);
	}
	
}
