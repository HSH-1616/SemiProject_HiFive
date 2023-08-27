package com.semi.common.filter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncryptoWrapper extends HttpServletRequestWrapper{
	
	public PasswordEncryptoWrapper(HttpServletRequest request) {
		super(request);
	}
	
	
	@Override
	public String getParameter(String key) {
		String oriVal=super.getParameter(key);
		if(key.equals("password")) {
			//암호화처리 로직을 작성
			System.out.println("원본 : "+oriVal);
			String encryptData=getSHA512(oriVal);
			System.out.println("암호화 값 : "+encryptData);
			return encryptData;
		}
		return oriVal;
	}
	
	
	private String getSHA512(String oriVal) {
		// 단방향 암호화처리하기
		// java에서 제공하는 클래스와 메소드를 이용한다
		// MessageDigest 클래스를 이용
		// java제공하는 암호화 알고리즘을 한다.
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance("SHA-512");
			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 생성된 MessageDigest클래스를 이용해서 원본값 암호화처리
		// 암호화처리는 byte단위로 쪼개서 처리를함
		byte[] oriValByte=oriVal.getBytes(); // 문자열 oriVal을 Byte로 쪼개서 Bytle배열로 만듬
		md.update(oriValByte);
		
		byte[] encryptData=md.digest();
		
		String encryptStrData = Base64.getEncoder().encodeToString(encryptData); // 암호화처리과정 
		
		return encryptStrData;
	}
	
}
