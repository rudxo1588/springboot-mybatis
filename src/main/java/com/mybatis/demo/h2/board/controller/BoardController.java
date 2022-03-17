package com.mybatis.demo.h2.board.controller;

import org.springframework.stereotype.Controller;

import com.mybatis.demo.h2.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
}
