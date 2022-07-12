package com.korea.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.auth.LoginController;
import com.korea.controller.auth.LogoutController;
import com.korea.controller.board.BoardListController;
import com.korea.controller.board.BoardPostController;
import com.korea.controller.member.MemberInfoController;
import com.korea.controller.member.MemberJoinController;
import com.korea.controller.member.MemberUpdateController;
import com.korea.controller.notice.NoticeListController;
import com.korea.controller.notice.NoticePostController;

public class FrontController extends HttpServlet {
	// url : SubController 객체
	HashMap <String,SubController> list = null;
	
	@Override
	public void init() throws ServletException {
		list = new HashMap();
		// 회원 관련
		list.put("/MemberJoin.do", new MemberJoinController());
		list.put("/MemberInfo.do", new MemberInfoController());
		list.put("/MemberUpdate.do", new MemberUpdateController());
		
		// 인증 관련
		list.put("/Login.do", new LoginController());
		list.put("/Logout.do", new LogoutController());
		// 게시판 관련
		list.put("/Board/list.do", new BoardListController());
		list.put("/Board/post.do", new BoardPostController());
		
		//공지사항
		list.put("/Notice/list.do", new NoticeListController());
		list.put("/Notice/post.do", new NoticePostController());
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String url = req.getRequestURI();
		System.out.println("URL : " + url);
		SubController sub = list.get(url);
		if(sub!=null)
			sub.execute(req, resp);
		
		  /*  service () URL 주소 확인 후 SubController의 execute 실행
		   : req.getRequestURL() -> 각 SubController url주소 확인 
		   : list.get(url) -> SubController의 execute 로직 실행 */
	}
}

/*	1 FrontController가 모든 url 요청을 받도록 설정
 *  2 상속을 받아서 FrontController를 HttpServlet으로 만들어준다.
 *  3 http service(), init() 메서드를 오버라이딩, init() 메서드를 최상단에 위치
 *  4 url 주소, SubController 객체를 list에 저장 
 *  5 MemberJoin.do url과 MemberJoinController 위치정보가 리스트에 들어간다.
 *  6 MemberJoin.do url에 해당되는 컨트롤러를 꺼내오기 위해 이런 작업을 한다.
 *  7 외부에서 요청 할 때 마다 service() 메서드 실행 
 *  8 SubController url(key)로 SubController를 받아와서 execute 메서드 실행
 *  9 submit을 누를 떄 마다(=URL 경로로 접속 할 때마다) parameter값이 달라지므로 새로 인코딩을 설정.
 *  
 *  회원은 뷰를 보고 컨트롤러에서 서비스를 요청한다 dao로 흘러간다 .
 *  
 *  
 *  BoardServer-config/web.xml
 *  서블릿을 등록하고 url mapping 
 *  확장자명이 do인 모든 요청을 FrontController로 처리
 *   */
