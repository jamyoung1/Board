package com.korea.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class MemberJoinController implements SubController{
	
	private MemberService service = MemberService.getInstance();
	
	
	

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("====MEMBERJOIN CONTROLLER====");
		
		String flag = req.getParameter("flag");
		
		// Login.jsp -> 회원가입 버튼 누름
			try {
				if(flag==null) {
					req.getRequestDispatcher("/WEB-INF/member/join.jsp").forward(req, resp);
				}else {
					// 1 파라미터 받기
					String email = req.getParameter("email");
					String pwd   = req.getParameter("pwd");
					String addr1 = req.getParameter("addr1");
					String addr2 = req.getParameter("addr2");
					System.out.println(email+","+pwd+","+addr1+","+addr2+",");
					
					// 2 입력값 검증
					
					// 3 서비스 실행(DB)
					MemberDTO dto = new MemberDTO();
					dto.setEmail(email);
					dto.setPwd(pwd);
					dto.setAddr1(addr1);
					dto.setAddr2(addr2);
					boolean result = service.MemberInsert(dto);
					
					// 4 View 이동
					resp.sendRedirect("/");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		try{
			// 페이지를 이동 시키는 메서드
			req.getRequestDispatcher("/WEB-INF/member/join.jsp").forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*  parameter들을 받아준다.
	 *  interface SubController를 상속받아서 interface의 메서드까지 입력
	 *  forward로 join.jsp로 보내주면 MemberJoinController를 실행 시키자마자 join.jsp 파일이 실행된다.
	 *  flag 값이 들어오지 않았다면 로그인에서 바로 회원가입 버튼을 눌러 진행한 것으로, parameter를 받을 필요 X. get 방식으로 새로 request 한다.
	 *  flag 값이 들어왔다면(true) parameter를 받고 redirect를 한다. forward를 하면 URL 경로가 그대로 유지된다.
	 * 
	 *  */
	
	
}
