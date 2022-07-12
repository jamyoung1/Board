package com.korea.service;



import org.mindrot.bcrypt.BCrypt;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;

public class MemberService {
	
	MemberDAO dao = MemberDAO.getInstance();
	// 비밀번호 암호화
	public BCrypt passwordEncoder = new BCrypt();
	
	
	// 싱글 톤 패턴
		private static MemberService instance=null;
		public static MemberService getInstance() {
			if(instance==null)
				instance = new MemberService();
			return instance;
		}
		
		private MemberService() {}
		
		public boolean MemberInsert(MemberDTO dto) {
			// 패스워드 암호화
			String pwd = passwordEncoder.hashpw(dto.getPwd(), passwordEncoder.gensalt());
			System.out.println("PWD(EN) : " + pwd);
			dto.setPwd(pwd);
			return dao.insert(dto);
		}
		public MemberDTO MemberSearch(String email) {
			return dao.Select(email);
		}
		public boolean MemberUpdate(MemberDTO dto) {
			return dao.Update(dto);
		}
}

// 싱글톤 패턴 구현
// MemberDAO 객체를 생성해서 MemberDAO 메서드 사용

// 패스워드 암호화

/* BCrypt 라이브러리 다운로드 후 lib에 추가
 * LoginController - 패스워드가 일치하는지 체크할 때 BCrypt를 사용해서 체크할 수 있도록 수정
 * Service 수정 - insert 할 때 패스워드 암호화를 해서 저장할 수 있도록 수정
 * 암호화가 되어서 저장 된 것을 sqldeveloper에서 확인
 * 
 *  
 *  */
