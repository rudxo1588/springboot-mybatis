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
/**
 * lombok 사용시 @RequiredArgsConstructor 릉 사용하면 
 * @Autowired를 사용하지 않아도 final or @NonNull 로 의존성 주입이 가능하다.
 */
@Service
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
	public int insertFaq(FaqVo faqVo) {
		return faqMapper.insertFaq(faqVo);
	}
	
	/**
	 * faq 삭제
	 * @return
	 */
	@Transactional
	public void deleteFaq(String[] faqSeq) {
		for(int i = 0; i < faqSeq.length; i++) {
			faqMapper.deleteFaqByFaqSeq(faqSeq[i]);
		}
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
	public int updateFaq(FaqVo faqVo) {
		return faqMapper.updateFaq(faqVo);
	}
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	@Transactional
	public void deleteFaqImgByImgSeq(int imgSeq) {
		faqMapper.deleteFaqImgByImgSeq(imgSeq);
	}
	
	/**
	 * faqImg 등록하기
	 * @return
	 */
	@Transactional
	public void insertFaqImg(FaqImgVo faqImgVo) {
		if(faqImgVo.getFaqImg() != null && "".equals(faqImgVo.getFaqImg())) {
			String[] imgList = faqImgVo.getFaqImg().split(",");
			if(imgList != null && imgList.length > 0) {
				faqImgVo.setFaqSeq(maxFaqSeq());
				for(int i = 0; i<imgList.length; i++) {
					faqImgVo.setFaqImg(imgList[i]);
					faqMapper.insertFaqImg(faqImgVo);
				}
			}
		}
	}
	
	/**
	 * Max faq_seq 값 구하기
	 * @return
	 */
	public int maxFaqSeq() {
		return faqMapper.maxFaqSeq();
	}
	
	
	/**
	 * 수정시 기존 faqImg 전부 삭제 후 인서트
	 * @return
	 */
	@Transactional
	public void updateFaqImg(FaqImgVo faqImgVo) {
		int faqSeq = faqImgVo.getFaqSeq();
		
		faqMapper.deleteFaqImgByFaqSeq(faqSeq);
		String[] faqImgList = faqImgVo.getFaqImg().split(",");;
		
		if(faqImgList != null && faqImgList.length > 0) {
			for(int i = 0; i < faqImgList.length; i++) {
				faqImgVo.setFaqImg(faqImgList[i]);
				faqMapper.insertFaqImg(faqImgVo);
			}
		}
	}
	
}
