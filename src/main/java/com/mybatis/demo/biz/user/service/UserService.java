package com.mybatis.demo.biz.user.service;

import org.springframework.stereotype.Service;

import com.mybatis.demo.biz.user.mapper.UserMapper;
import com.mybatis.demo.biz.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	
	public User login(User user) {
		return userMapper.login(user);
	}
}
