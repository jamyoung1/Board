package com.korea.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	
//  SubController interface
//	회원가입 컨트롤러(서브)
//  인증 컨트롤러(서브)
//  게시판 컨트롤러(서브)

public interface SubController {	
	void execute(HttpServletRequest req, HttpServletResponse resp);
		

	/*
	 * SubController는 인터페이스
	 * 모든 컨트롤러가 외부 request와 response 객체를 받기 위해
	 * interface 설정
	 *  
	 *  
	 *  */
	
	
	
}
