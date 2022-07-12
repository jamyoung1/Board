package com.korea.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.filter.authfilter;

public class LogoutController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Logout");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		try {	
			// filter 로그아웃 시 flag를 false 설정 -> 재접속 시
			// 한 번은 session으로부터 grade 꺼내지 않는다.
			
			req.setAttribute("MSG","로그아웃 완");
			req.getRequestDispatcher("/").forward(req, resp);
			resp.sendRedirect("/?MSG=로그아웃처리완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/* 세션을 invaludate를 통해 제거를 한다.
 * req 객체에 로그아웃완료 MSG를 담아서 forward 방식으로 최초 페이지로 넘어간다.
 * 
 * */
