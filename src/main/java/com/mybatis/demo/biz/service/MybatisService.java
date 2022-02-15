package com.mybatis.demo.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.demo.biz.domain.FaqVo;
import com.mybatis.demo.biz.mapper.MybatisMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MybatisService {

	private final MybatisMapper mybatisMapper;
	
	public List<FaqVo> selectList() {
		return mybatisMapper.selectList();
	}
}
