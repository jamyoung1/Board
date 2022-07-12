package com.korea.test;

import java.util.List;

import org.junit.Test;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class DaoTest {

//	@Test
//	public void test() {
////		MemberDTO dto = new MemberDTO();
////		dto.setEmail("example@example.com");
////		dto.setPwd("1234");
////		dto.setAddr1("대구광역시 달서구 성서로 393 로얄팰리스");
////		dto.setAddr2("6층 601호");
////		
////		MemberDAO dao = MemberDAO.getInstance();
////		boolean result = dao.insert(dto);
////		if(result)
////			System.out.println("INSERT 성공");
////		else
////			System.out.println("INSERT 실패");
////	}
	
//	@Test
//	public void Test2() {
//		// MemberDAO's select(email)
//		MemberDAO dao = MemberDAO.getInstance();
//		MemberDTO dto = dao.Select("min1hhh1600@naver.com");
//		System.out.println("결과 : " + dto.toString());
//	}
//
//	
//	@Test
//	public void Test3() {
//		MemberDTO dto = new MemberDTO();
//		dto.setEmail("test@naver.com");
//		dto.setPwd("1234");
//		dto.setAddr1("대구");
//		dto.setAddr2("성서");
//		
//		MemberDAO dao = MemberDAO.getInstance();
//		dao.Update(dto);
//		
//		
//	}
	
//	@Test
//	public void Test4() {
//		
//		MemberDTO dto = new MemberDTO();
//		dto.setEmail("admin@admin.com");
//		dto.setPwd("1234");
//		dto.setAddr1("");
//		dto.setAddr2("");
//		dto.setGrade(2);
//		// 패스워드 암호화 (MemberService를 불러와야 한다)
//		MemberService service = MemberService.getInstance();
//		service.MemberInsert(dto);
//		
//		// MemberDAO dao = MemberDAO.getInstance();
//
//		service.MemberInsert(dto);
//		
//		dto.setEmail("guest@guest.com");
//		dto.setPwd("1234");
//		dto.setAddr1("");
//		dto.setAddr2("");
//		dto.setGrade(0);
//		
//		service.MemberInsert(dto);
//		
//	}
//	@Test
//	public void Test4() {
//		BoardDAO dao = BoardDAO.getInstance();
//		List<BoardDTO> list = dao.Select(5, 20);
//		//list.forEach(dto -> System.out.println(dto))
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//	}
	
	@Test
	public void Test5() {
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.getTotalCount();
		System.out.println("게시물 건 수 " + result);
	}
}
