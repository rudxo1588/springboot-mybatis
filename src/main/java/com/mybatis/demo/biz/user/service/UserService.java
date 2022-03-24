package com.mybatis.demo.biz.user.service;

import org.springframework.stereotype.Service;

import com.mybatis.demo.biz.tools.Sha256Tools;
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

	public int signUp(User user) {
		Sha256Tools sha256 = new Sha256Tools();
		user.setPassword(sha256.encryptSHA256(user.getPassword()));

		return userMapper.insert(user);
	}
}
