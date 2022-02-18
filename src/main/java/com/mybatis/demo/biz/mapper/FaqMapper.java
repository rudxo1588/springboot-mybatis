package com.mybatis.demo.biz.mapper;

import java.util.List;

import com.mybatis.demo.biz.domain.FaqImgVo;
import com.mybatis.demo.biz.domain.FaqVo;

/**
 * MySqlDataSourceConfig에서 매퍼스캔을 사용했기때문애 따로 @Mapper 어노테이션이 필요하지 않음.
 */
public interface FaqMapper {

	/**
	 * interface파일에서는 기본 public으로 생성되기 때문에 선언을 하지 않아도 됨
	 * class파일은 protected가 기본
	 */
	List<FaqVo> selectList();
	
	List<FaqVo> faqImgListBySelectCollection(FaqImgVo param);
	
	List<FaqVo> faqImgListByJoinCollection();
	
	List<FaqImgVo> faqImgListByAssciation();
	
	List<FaqImgVo> getAcFaqImgList();
	
	List<FaqImgVo> faqImgListByJoinAssociation();
}
