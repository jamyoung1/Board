<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의정보</title>

<%@include file="/resources/includes/link.jsp"%>
<link rel="stylesheet" href="resources/css/common.css">

</head>

<body>
   <%@page import="com.korea.dto.MemberDTO"%>

   <%
   MemberDTO dto = (MemberDTO) request.getAttribute("dto");
   %>
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

   <div class="contanier-md" id="wrapper"
      style="width: 80%; margin: 100px auto;">
      <!-- TopMenu -->
      <%@include file="/resources/includes/topmenu.jsp"%>
      <!-- Nav -->
      <%@include file="/resources/includes/nav.jsp"%>
      <!-- MainContents -->
      <div id="maincontents"
         style="border: 1px solid gray; margin-top: 15px;">
         <h3 align=center style="margin-top: 30px;">회원정보</h3>
         <form action = "/MemberUpdate.do" method = "post">
            <table class="table w-75 table-striped" style="margin: 100px auto; ">
               <tr>
                  <td>PW : <input type ="password" name = pwd></td>
               </tr>
               <tr>
                  <td><input type = submit value = "확인" class= "btn btn-primary"></td>
               </tr>
            </table>
            <input type ="hidden" name = "flag" value = "true">
            <input type ="hidden" name = "addr1" value = <%=request.getParameter("addr1") %>>
            <input type ="hidden" name = "addr2" value = <%=request.getParameter("addr2") %>>
            <input type ="hidden" name = "newpwd" value = <%=request.getParameter("pwd") %>>
         </form>
      </div>
      <!-- Footer -->
   </div>
</body>
</html>
