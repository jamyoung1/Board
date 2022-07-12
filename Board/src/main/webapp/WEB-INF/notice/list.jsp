<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- BootStrap  -->
<%@include file="/resources/includes/link.jsp" %>
<link rel="stylesheet" href="resources/css/common.css">
<!-- BootStrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
</head>
<body>
	<div class="container-md" id=wrapper style="width: 80%; margin:100px auto;">
	<!-- TopMenu -->
	<%@include file="/resources/includes/topmenu.jsp" %>
	<!-- NAV -->
	<%@include file="/resources/includes/nav.jsp" %>
	
	<!-- MainContents -->
	<div id=maincontents style="border: 3px solid pink; margin-top:15px;">
		<h1> 공지사항 </h1>
		
		<a href="/Notice/post.do">글쓰기</a>
	</div>	
	<!-- Footer -->
	</div>
</body>
</html>