package com.mybatis.demo.h2.board.mapper;

import java.util.List;

import com.mybatis.demo.h2.board.vo.BoardVo;

public interface BoardMapper {

	public List<BoardVo> selectBoardList();
}
