package com.mybatis.demo.biz2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.demo.biz2.domain.BoardVo;
import com.mybatis.demo.biz2.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	public List<BoardVo> getBoardList() {
		return boardMapper.selectBoardList();
	}
}
