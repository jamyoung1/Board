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
				session.setAttribute("email", dto.getEmail());
				session.setAttribute("grade", dto.getGrade());
				session.setMaxInactiveInterval(60*5);
				//View로 이동
				resp.sendRedirect("/main.jsp");
			}else{
				//패스워드 불일치
				req.setAttribute("MSG", "패스워드가 일치하지 않습니多.");
				req.getRequestDispatcher("/").forward(req, resp);
				return;
			}
		}else{
			// 아이디 조회 실패 해당 아이디가 없습니다
			req.setAttribute("MSG", "일치하는 아이디가 없습니多.");
			req.getRequestDispatcher("/").forward(req, resp);
			return;
		}
		
		// View로 이동
		
		// req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req,resp);
			
			resp.sendRedirect("/main.jsp");
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/* 
 * 	다른 컨트롤러들과 마찬가지로 SubController를 상속받아서 execute() 재정의
 *  LoginController가 하는일
 *  1 파라미터 받기 - id,pw를 받아온다
 *  2 입력값 검증 - id와 pw가 입력되지 않았다면 최상위 경로("/" 로 설정하는데, 결국 "/Login.jsp"로 redirect 되도록 설정
 *  3 Service 실행 - 서비스의 MemberSearch(email) 메서드를 실행한다. 
 *                 실행하면 리턴값이 dto. id,pw가 일치해서 로그인 성공할 시 메인페이지로 이동
 *  4 패스워드가 불일치 하거나, 아이디 조회에 실패하면 로그인페이지로 이동한다.
 *    로그인 페이지로 이동할 때 forward로 이동하며 request 객체에 MSG를 담아서 간다.
 *  5 View로 이동 - 로그인 프로세스가 정상적으로 수행되면 메인페이지로 이동. redirect로 넘어간다.                
 * */
