package com.mybatis.demo.biz.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybatis.demo.biz.faq.mapper.FaqImgMapper;
import com.mybatis.demo.biz.faq.vo.FaqImg;

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

	/**
	 * faqImg 등록
	 * @return
	 */
	@Transactional(rollbackFor = {Exception.class})
	public int add(FaqImg vo) {
		return faqImgMapper.insert(vo);
	}

	/**
	 * faqImg size만큼 등록 메소드 조회
	 * @return
	 */
	public int addList(int faqSeq, List<FaqImg> list) {
		int result = 0;
		if (list != null && list.size() > 0) {
			for (FaqImg vo : list) {
				vo.setFaqSeq(faqSeq);
				result += this.add(vo);
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
	 * faqImg faqSeq로 삭제하기
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
	@Transactional(rollbackFor = Exception.class)
	public void modifyList(int faqSeq, List<FaqImg> list) {
		this.deleteByFaqSeq(faqSeq);
		if (list != null && list.size() > 0) {
			for (FaqImg vo : list) {
				vo.setFaqSeq(faqSeq);
				this.add(vo);
			}
		}
	}

}
