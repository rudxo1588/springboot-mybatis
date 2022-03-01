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
	
	private final FaqImgService faqImgService;
	
	/**
	 * faqImg select collcetion이용하여 이미지리스트까지 가져오기
	 * @return
	 */
	public List<FaqVo> getList(FaqVo faqVo) {
		return faqMapper.getList(faqVo);
	}
	
	/**
	 * faq 등록하기
	 * @return
	 */
	@Transactional
	public int add(FaqVo faqVo) {
		int result = faqMapper.insert(faqVo);
		
		if (result > 0) {
			faqImgService.addList(faqVo.getFaqSeq(), faqVo.getFaqImgList());
		}
		
		return result;
	}
	
	
	/**
	 * faq 삭제
	 * @return
	 */
	@Transactional
	public void deleteList(String[] faqSeq) {
		for(int i = 0; i < faqSeq.length; i++) {
			faqMapper.delete(faqSeq[i]);
		}
	}
	
	/**
	 * faq 상세페이지로 이동하기
	 * @return
	 */
	public FaqVo getDetail(int faqSeq) {
		return faqMapper.getDetail(faqSeq);
	}
	
	/**
	 * faq 수정하기
	 * @return
	 */
	@Transactional
	public int modify(FaqVo faqVo) {
		int result = faqMapper.update(faqVo);
		
		if(result > 0) {
			faqImgService.modify(faqVo.getFaqSeq(), faqVo.getFaqImgList());
		}
		return result;
	}
	
}
