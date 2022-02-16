package com.mybatis.demo.biz2.mapper;

import java.util.List;

import com.mybatis.demo.biz2.domain.BoardVo;

public interface BoardMapper {

	public List<BoardVo> selectBoardList();
}
