package com.mybatis.demo.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.demo.biz.domain.FaqVo;
import com.mybatis.demo.biz.mapper.FaqMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {

	private final FaqMapper FaqMapper;
	
	public List<FaqVo> selectList() {
		return FaqMapper.selectList();
	}
}
