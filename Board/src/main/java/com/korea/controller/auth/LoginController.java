package com.korea.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class LoginController implements SubController{
	
	MemberService service = MemberService.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("LoginController");
		// 파라미터(아이디 패스워드) 받기
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		try {
		// 입력 값 검증
		if(email==null || pwd==null) {
			resp.sendRedirect("/");
		}
		
		// 서비스 실행(데이터베이스에 아이디, 패스워드 있는지)
		
		// MemberService -> dao -> id가 있는지 db로 확인 -> 해당 id의 pw를 꺼내오기(일치여부)
		MemberDTO dto = service.MemberSearch(email);
		if(dto!=null) { // pw 일치 여부
//			if(pwd.equals(dto.getPwd())){
				if(service.passwordEncoder.checkpw(pwd, dto.getPwd())) {
					
				//패스워드 일치
				//세션에 특정옵션 부여(ex 계정의 grade 저장)
				HttpSession session = req.getSession();
				session.setAttribute("grade", dto.getGrade());
				session.setAttribute("grade", dto.getGrade());
				session.setMaxInactiveInterval(60*5);
				//View로 이동
				resp.sendRedirect("/main.jsp");
			}else{
				//패스워드 불일치
				req.setAttribute("MSG", "패스워드가 일치하지 않습니多.");
				req.getRequestDispatcher("/").forward(req, resp);
			}
		}else{
			// 아이디 조회 실패 해당 아이디가 없습니다
			req.setAttribute("MSG", "일치하는 아이디가 없습니多.");
			req.getRequestDispatcher("/").forward(req, resp);
		}
		
		// View로 이동
		
		// req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req,resp);
			
			resp.sendRedirect("/main.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
