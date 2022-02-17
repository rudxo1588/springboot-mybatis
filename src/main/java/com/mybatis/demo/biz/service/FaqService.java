package com.mybatis.demo.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.demo.biz.domain.FaqImgVo;
import com.mybatis.demo.biz.domain.FaqVo;
import com.mybatis.demo.biz.mapper.FaqMapper;

import lombok.RequiredArgsConstructor;


/**
 * @Service는 해당 클래스가 Service임을 나타냄 
 */
@Service
/**
 * lombok 사용시 @RequiredArgsConstructor 릉 사용하면 
 * @Autowired를 사용하지 않아도 final로 의존성 주입이 가능하다.
 */
@RequiredArgsConstructor
public class FaqService {

	private final FaqMapper faqMapper;
	
	public List<FaqVo> selectList() {
		return faqMapper.selectList();
	}
	
	public List<FaqVo> selectFaqImgList() {
		return faqMapper.selectFaqList();
	}
	
	public List<FaqVo> selectFaqImgList2() {
		return faqMapper.selectFaqList2();
	}
	
	public List<FaqImgVo> getFaqOneImg() {
		return faqMapper.getFaqOneImg();
	}
	
	public List<FaqImgVo> getAcFaqImgList() {
		return faqMapper.getAcFaqImgList();
	}
	
}
