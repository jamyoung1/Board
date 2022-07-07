package com.korea.test;

import org.junit.Test;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;

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
	
	@Test
	public void Test2() {
		// MemberDAO's select(email)
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.Select("min1hhh1600@naver.com");
		System.out.println("결과 : " + dto.toString());
	}

}
