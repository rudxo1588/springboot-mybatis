package com.mybatis.demo.biz.mapper;

import java.util.List;

import com.mybatis.demo.biz.domain.FaqVo;

public interface FaqMapper {

	public List<FaqVo> selectList();
}
