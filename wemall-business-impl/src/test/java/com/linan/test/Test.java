package com.linan.test;

import com.wemall.jwt.service.impl.JwtToken;

public class Test {
	public static void main(String[] args) {
		try {
			String token = JwtToken.createToken();
			System.out.println(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@org.junit.Test
	public void haha() {
		try {
			String token = JwtToken.createToken();
			System.out.println(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@org.junit.Test
	public void vertify() {
		String a ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmciOiLku4rml6XlpLTmnaEiLCJuYW1lIjoiZnJlZW1hbm9uZyIsImV4cCI6MTUyODc4NTYxNywiaWF0IjoxNTI4Nzg1NTU3LCJhZ2UiOiIyOCJ9.6LqkZTdJgzjnRYiPDyydY9LfqV94f1cMsjISWSoOeyk";
		try {
			JwtToken.verifyToken(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
