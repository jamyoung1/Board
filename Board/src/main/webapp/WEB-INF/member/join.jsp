<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/resources/includes/link.jsp" %>
<link rel="stylesheet" href="resources/css/common.css">
</head>
<body>
	<div class="container-md" style="width:45%;margin:100px auto;" style="text-align:center;">
	<h1 class="mb-4">회원가입</h1>
		<form action="/MemberJoin.do" method="post">
			<input type="email" name=email placeholder="example@example.com" class="form-control m-2">
			<input type="password" name=pwd placeholder="Enter Password" class="form-control m-2">
			<input name=addr1 placeholder="Enter Address1" class="form-control m-2">
			<input name=addr2 placeholder="Enter Address2" class="form-control m-2">
			<input type=submit value=가입하기 class="btn btn-primary w-100 m-2">
			<input type=reset value=RESET class="btn btn-primary w-100 m-2">
			<a href="/" class="btn btn-primary w-100 m-2">이전으로</a>
			<input type=hidden name=flag value=true >
 		</form>
	</div>
	
	<!-- 
		1 노출을 원하지 않기 때문에 회원가입 페이지를 WEB-INF 하위에 위치 시킴
		2 parameter들의 이름을 각각 설정해주고, parameter들은 MemberJoinController에서 불러올 수 있다.
		3 회원가입 페이지에 접속하는 것과 회원가입 페이지에서 submit 하는 것이 같은 작업으로 본류되어 있는 것을 방지하기 위해
		  input hidden 
		4 로그인에서 접속하면 flag는 null이고, 회원가입 페이지에서 submit하면 flag는 true
	 -->
</body>
</html>