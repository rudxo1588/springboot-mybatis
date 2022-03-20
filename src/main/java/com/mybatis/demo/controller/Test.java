package com.mybatis.demo.controller;

import com.mybatis.demo.biz.tools.Sha256Tools;

public class Test {
	public static void main(String[] args) {
		
		Sha256Tools sha256 = new Sha256Tools();
		
		
		System.out.println(sha256.encryptSHA256("1234"));
	}

}
