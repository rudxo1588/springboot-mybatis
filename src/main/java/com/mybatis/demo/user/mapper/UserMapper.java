package com.mybatis.demo.user.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.mybatis.demo.user.vo.User;

@Mapper
public interface UserMapper {

	int insert(User user);
	
	Optional<User> selectById(String id);
}
