package com.korea.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class authfilter implements Filter {
	
	// Key : URL , Value = Grade 
	// 권한이 저장이 되어 있어야 페이지의 권한과 유저의 권한을 비교할 수 있다.
	Map<String,Integer> pageGradeMap = new HashMap();
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 페이지 권한 저장
		pageGradeMap.put("/Board/list.do", 0); // 모두 이용 가능
		pageGradeMap.put("/Board/post.do", 1); // 일반 계정 권한 이상
		
		pageGradeMap.put("/Notice/list.do", 0); // 모두 이용 가능
		pageGradeMap.put("/Notice/post.do", 2); // 관리자 등급에서만 사용 가능
	}


	@Override
	// 계속 실행 되는 함수
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
	//Request 전 처리	
	System.out.println("====== Filter 처리(Request 전) ======");
	req.setCharacterEncoding("utf-8");
	resp.setContentType("text/html; charset=utf-8");
	
	// Session으로부터 Grade 추출. LoginController에서 grade가 session에 저장 되어있다. -> session에 있는 grade의 값을 먼저 꺼낸다.
	// servlet request -> Http Request 다운캐스팅
	HttpServletRequest request = (HttpServletRequest)req;
	
	// 세션을 통해 grade 가져오기
	HttpSession session = request.getSession();
	// Integer로 Grade 추출
    int usergrade=0;
    if(session.getAttribute("grade")!=null) //필터를 먼저 거치고 컨트롤러로 가기 떄문에 session 값 확인 할 수 없다.null(!=이기 때문에 grade에 값이 들어 있을 경우. 처음 접속이 아닐 경우)
    	usergrade=(Integer)session.getAttribute("grade");
    System.out.println("UserGrade : " + usergrade);
	// -> LoginController의 session을 꺼내야 하는데 Filter는 바로 실행이 되기 때문에 LoginController를 거치지 않는다.(Session에 값이 들어있지 않아서 오류 발생)
	
	// URL에 대응되는 Grade 확인
	String URL = request.getRequestURI();
	System.out.println("Filter's URL : " + URL);
	
	// init으로 저장된 권한 번호 비교
	int pagegrade=0;
	if(pageGradeMap.get(URL)!=null) // map에 등록되어있는 URL이라면 
		pagegrade = pageGradeMap.get(URL); 
	System.out.println("PageGrade : " + pagegrade);
	
	// guest 계쩡이 1이상의 page로 접근 불가
	// user - page 권한 설정
	if(usergrade==0 && pagegrade>=1)
		throw new ServletException("로그인이 필요한 페이지");
	
	// admin 계정
	// admin이 아닌 계정의 권한 제한
	if(usergrade<2 && pagegrade==2)
		throw new ServletException("페이지에 접근 할 권한이 없습니다.");

	
	
	// filter 먼저 실행이 되고 LoginController 진입
	chain.doFilter(req, resp);
	//Response 전 처리
	System.out.println("====== Filter 처리(Response 전) ======");
	}
}
