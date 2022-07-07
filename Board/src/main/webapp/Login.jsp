<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="resources/includes/link.jsp" %>
<link rel="stylesheet" href="resources/css/common.css">
</head>
<body>

<%
	String MSG = (String)request.getAttribute("MSG");

	if(MSG!=null){
		%>
			<script>
			alert("<%=MSG%>");
			</script>
		<%
	}

%>
	<div class="ontainer-md" style="width:500px;height:500px;margin:200px auto;">
	
	<form action="/Login.do" method="post">
		<div class="row m-3" id=Logo>
			<div class="col" style="text-align: center;">
				<i class="bi bi-person" style="font-size:7rem;"></i>			
			</div>
		</div>
		
		<div class="row m-3">
			<input type="email" name=email class="form-control" placeholder="아이디를 입력하세요">
		</div>
		
		<div class="row m-3">
			<input type="password" name=pwd class="form-control" placeholder="패스워드를 입력하세요">
		</div>
		
		<div class="row m-3">
			<input type="submit" value="로그인" class="btn btn-primary">
		</div>
		
		<div class="row m-3">	
			<a href="/MemberJoin.do" class="btn btn-primary">회원가입</a>									
		</div>
		</form>			
	</div>
	
	<!-- 
		노출되는 페이지
		회원가입을 클릭하면 /MemberJoin.do 로 연결 되도록 설정해서 회원가입 창으로 넘어갈 수 있도록 한다.
	
	 -->
</body>
</html>