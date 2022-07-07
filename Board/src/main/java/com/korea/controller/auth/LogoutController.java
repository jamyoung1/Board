package com.korea.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;

public class LogoutController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Logout");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		try {	
			req.setAttribute("MSG","로그아웃 완");
			req.getRequestDispatcher("/").forward(req, resp);
			resp.sendRedirect("/?MSG=로그아웃처리완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
