package com.korea.dto;

public class MemberDTO {
	private String email;
	private String pwd;
	private String addr1;
	private String addr2;
	private int grade;      // 권한 (일봔, 관리자, 익명 계정) 일반 : 1    관리자 : 2    익명 : 0
	
	public MemberDTO() {
		grade=1;
	}
	// grade는 권한 별 페이지 접근 제한을 위한 변수
	// grade=1 -> 생성자가 만들어지면 기본적으로 일반으로 권한을 부여하기 위함
	
	public MemberDTO(String email, String pwd, String addr1, String addr2, int grade) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [email=" + email + ", pwd=" + pwd + ", addr1=" + addr1 + ", addr2=" + addr2 + ", grade="
				+ grade + "]";
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
