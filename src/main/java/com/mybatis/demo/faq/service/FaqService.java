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
	
	public List<FaqVo> selectList() {
		return faqMapper.selectList();
	}
	
	public List<FaqVo> faqImgListBySelectCollection(FaqImgVo param) {
		return faqMapper.faqImgListBySelectCollection(param);
	}
	
	public List<FaqVo> getFaqAllList() {
		return faqMapper.getFaqAllList();
	}
	
	public List<FaqVo> faqImgListByJoinCollection() {
		return faqMapper.faqImgListByJoinCollection();
	}
	
	public List<FaqImgVo> faqImgListByAssciation() {
		return faqMapper.faqImgListByAssciation();
	}
	
	public List<FaqImgVo> getAcFaqImgList() {
		return faqMapper.getAcFaqImgList();
	}
	
	public List<FaqImgVo> faqImgListByJoinAssociation() {
		return faqMapper.faqImgListByJoinAssociation();
	}
	
	@Transactional
	public void faqInsert(FaqVo faqVo) {
		faqMapper.insertFaqWrite(faqVo);
	}
	
	@Transactional
	public void faqDelete(int faqSeq) {
		faqMapper.deleteFaq(faqSeq);
		faqMapper.deleteFaqImg(faqSeq);
	}
	
	public FaqVo faqDetail(int faqSeq) {
		return faqMapper.faqImgListBySelectCollection(faqSeq);
	}
	
}
