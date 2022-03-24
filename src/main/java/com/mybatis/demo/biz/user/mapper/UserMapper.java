package com.mybatis.demo.biz.user.mapper;

import com.mybatis.demo.biz.user.vo.User;

public interface UserMapper {

	User login(User user);

	int insert(User user);
}
