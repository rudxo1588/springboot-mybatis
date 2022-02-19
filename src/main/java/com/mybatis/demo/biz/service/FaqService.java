package com.mybatis.demo.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mybatis.demo.biz.domain.FaqImgVo;
import com.mybatis.demo.biz.domain.FaqVo;
import com.mybatis.demo.biz.mapper.FaqMapper;

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
	
	public void faqInsert(FaqVo faqVo) {
		faqMapper.insertFaqWrite(faqVo);
	}
	
}
