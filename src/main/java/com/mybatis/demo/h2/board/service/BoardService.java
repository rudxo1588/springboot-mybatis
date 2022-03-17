package com.mybatis.demo.h2.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.demo.h2.board.mapper.BoardMapper;
import com.mybatis.demo.h2.board.vo.BoardVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	public List<BoardVo> getBoardList() {
		return boardMapper.selectBoardList();
	}
}
