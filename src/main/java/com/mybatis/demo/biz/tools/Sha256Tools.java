package com.mybatis.demo.biz.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Tools {

	public String encryptSHA256(String str) {
		String sha = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte[] byteData = sh.digest();
			StringBuilder sb = new StringBuilder();
			for (byte byteDatum : byteData) {
				sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("암호화 에러-NoSuchAlgorithmException");
			sha = null;
		}
		return sha;
	}

}
