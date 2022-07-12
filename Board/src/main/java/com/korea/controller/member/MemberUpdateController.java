package com.korea.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class MemberUpdateController implements SubController{
	
	MemberService service = MemberService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 파라미터 받기
		
		// 입력값 확인
		
		// 서비스 실행
		
		// View
		
		String flag = req.getParameter("flag");
		
		try {
			if(flag==null) {       // myinfo.jsp 에서 요청 받음
				req.getRequestDispatcher("/WEB-INF/member/password.jsp").forward(req, resp);
			}else {                // password.jsp 에서 요청 받음
				// 정보를 받아서 패스워드 일치 여부 , 정보 수정
				// 패스워드가 일치하면 정보 수정 처리 
				String pwd = req.getParameter("pwd");
				// getSession에는 LoginController에서 저장한 grade, email이 저장 되어 있다.
				HttpSession session = req.getSession();
				String email = (String)session.getAttribute("email");
				MemberDTO dto = service.MemberSearch(email);
				
				if(service.passwordEncoder.checkpw(pwd, dto.getPwd())) {
					// 패스워드가 일치한 경우
					// -> 수정된 값 파라미터 받기
					String addr1 = req.getParameter("addr1");
					String addr2 = req.getParameter("addr2");
					
					String newpwd = req.getParameter("newpwd");
					
					newpwd = service.passwordEncoder.hashpw(newpwd,
							service.passwordEncoder.gensalt());
					
					dto.setPwd(newpwd);
					
					
					
					dto.setAddr1(addr1);
					dto.setAddr2(addr2);
					
					// -> dto 단위로 담아서 service.Update(MemberDTO dto) 전달
					service.MemberUpdate(dto);
					req.setAttribute("dto", dto);
					req.getRequestDispatcher("/WEB-INF/member/myinfo.jsp").forward(req, resp);
					return ;
					
				}else {
					// 패스워드가 불일치 한 경우
					req.setAttribute("MSG", "패스워드가 일치하지 않습니다.");
					req.getRequestDispatcher("/WEB-INF/member/password.jsp").forward(req, resp);
					return ;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
