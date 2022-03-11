package com.mybatis.demo.user.service;

import org.springframework.stereotype.Service;

import com.mybatis.demo.user.mapper.UserMapper;
import com.mybatis.demo.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	public int signUp(User user) {
		return userMapper.insert(user);
	}
}
