<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- BootStrap  -->
<%@include file="/resources/includes/link.jsp"%>
<link rel="stylesheet" href="resources/css/common.css">
<!-- BootStrap icon -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
</head>
<body>
	<%@page import="com.korea.dto.MemberDTO"%>
	%>
	<%
	MemberDTO dto = (MemberDTO) request.getAttribute("dto");
	%>



	<div class="container-md" id=wrapper
		style="width: 80%; margin: 100px auto;">
		<!-- TopMenu -->
		<%@include file="/resources/includes/topmenu.jsp"%>
		<!-- NAV -->
		<%@include file="/resources/includes/nav.jsp"%>

		<!-- MainContents -->
		<form action="/MemberUpdate.do" method="post">
			<table class="table w-75 table-striped" style="margin: 100px auto;">
				<caption>회원 정보</caption>
				<tr>
					<td>Email</td>
					<td><input name=email value=<%=dto.getEmail()%> disabled></td>
				</tr>
				<tr>
					<td>Addr1</td>
					<td><input name=addr1 value=<%=dto.getAddr1()%>></td>
				</tr>
				<tr>
					<td>Addr2</td>
					<td><input name=addr2 value=<%=dto.getAddr2()%>></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input name=pwd type=password value=<%=dto.getPwd()%>></td>
				</tr>
				<tr>
					<td>
						<input type=submit value="정보수정" class="btn btn-primary w-50"> 
					</td>
					<td>
						<a class="btn btn-secondary w-50">메인이동</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>