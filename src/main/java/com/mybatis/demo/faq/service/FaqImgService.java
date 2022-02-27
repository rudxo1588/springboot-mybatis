package com.mybatis.demo.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybatis.demo.faq.domain.FaqImgVo;
import com.mybatis.demo.faq.mapper.FaqImgMapper;

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
public class FaqImgService {

	private final FaqImgMapper faqImgMapper;
	
	
	public int insertFaqImg(FaqImgVo vo) {
		System.out.println(vo);
		return faqImgMapper.insert(vo);
	}
	
	public int addList(int faqSeq, List<FaqImgVo> list) {
		int result = 0;
		if (list != null && list.size() > 0) {
			for (FaqImgVo vo : list) {
				vo.setFaqSeq(faqSeq);
				result += this.insertFaqImg(vo);
			}
		}
		return result;
	}
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	@Transactional
	public void delete(int imgSeq) {
		faqImgMapper.delete(imgSeq);
	}
	
	/**
	 * faqImg 삭제하기
	 * @return
	 */
	@Transactional
	public int deleteByFaqSeq(int faqSeq) {
		return faqImgMapper.deleteByFaqSeq(faqSeq);
	}
	
	/**
	 * faqImg 수정시 전부 삭제 후 재 인서트
	 * @param faqSeq
	 * @param list
	 */
	@Transactional
	public void modify(int faqSeq, List<FaqImgVo> list) {
		int result = this.deleteByFaqSeq(faqSeq);
		
		if(result > 0) {
			if (list != null && list.size() > 0) {
				for (FaqImgVo vo : list) {
					vo.setFaqSeq(faqSeq);
					result += this.insertFaqImg(vo);
				}
			}
		}
	}
}
