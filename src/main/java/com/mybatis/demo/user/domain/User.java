package com.mybatis.demo.user.domain;

import lombok.Data;

@Data
public class User {

	private String id;
	private String password;
	private String name;
	private String phone;
	private String email;

}
