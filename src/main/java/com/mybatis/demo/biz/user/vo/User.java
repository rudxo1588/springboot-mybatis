package com.mybatis.demo.biz.user.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {

	@NotBlank(message = "id는 필수값 입니다.")
	private String id;

	@NotBlank(message = "password는 필수값 입니다.")
	private String password;

	@NotBlank(message = "name는 필수값 입니다.")
	private String name;
}
