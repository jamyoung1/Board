<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>

<%@include file="/resources/includes/link.jsp"%>
<link rel="stylesheet" href="../resources/css/common.css">

</head>
<body>
   <div class="contanier-md" id="wrapper"
      style="width: 80%; margin: 100px auto;">
      <!-- TopMenu -->
      <%@include file="/resources/includes/topmenu.jsp"%>
      <!-- Nav -->
      <%@include file="/resources/includes/nav.jsp"%>
      
      <!-- MainContents -->
      <div id="maincontents" style="margin-top: 15px;">
         <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
           <ol class="breadcrumb">
             <li class="breadcrumb-item"><a href="#">Home</a></li>
             <li class="breadcrumb-item active" aria-current="page">board</li>
           </ol>
         </nav>

         <h1>자유 게시판</h1>
         <%
            // list 구성사용 되는 변수들
            int totalcount = 0; // 전체 게시물 수
            int numPerPage = 10; // 페이지당 표시할 게시물 수
            
            int totalPage = 0;    // 전체페이지수 
            int nowPage = 1;     // 현재 페이지 번호
            int start = 1;       // 게시물 읽을때 사용되는 시작 값
            int end = 10;       // 게시물 읽을떄 사용 되는 끝 값
            
            int pagePerBlock = 15; // 블럭당 표시할 페이지 수
            int totalBlock = 0; //전체 페이징 블럭 수
            int nowBlock = 1;   //현재 페이징 블럭 수
         %>
         <%
         /*    if(request.getParameter("nowPage") != null){
               nowPage = Integer.parseInt(request.getParameter("nowPage"));
            } */
            
            if(request.getAttribute("nowPage") != null){
               nowPage = Integer.parseInt((String)request.getAttribute("nowPage"));
            }
         
         
         %>
         <%
            totalcount = (int)request.getAttribute("tcnt");       //전체 게시물 수 받기
            totalPage = (int)Math.ceil(totalcount /  numPerPage);   //전체 페이지 수 계산
                     //
            //전체 블럭수
            totalBlock = (int)Math.ceil((double)totalPage / pagePerBlock);
            //현재 블럭 숫자 계산
            nowBlock = (int)Math.ceil((double)nowPage / pagePerBlock);
            
         %>
         
         <script>
            /* 페이징 처리 함수 번호를 받아 해당 페이지를 표시*/
            function paging(pageNum){
               form = document.readFrm;
               form.nowPage.value = pageNum;
               let numPerPage = <%=numPerPage %>
               form.start.value = (pageNum*numPerPage) - numPerPage + 1;
               form.end.value = (pageNum*numPerPage);
               form.action="/Board/list.do";
               form.submit();
            }
            
            /* 블럭 처리 함수 - 이전/ 이후 버튼 누를떄 이전블럭/다음블럭 이동 */
            function block(value){
               form = document.readFrm;
               
               StartPage = <%=pagePerBlock%> *(value -1) + 1
               //이전 이후로 이동처리 될때 처음 표시될 페이지 번호
               numPerPage = <%=numPerPage %>
               
               
               form.nowPage.value = StartPage;
               form.start.value=(StartPage*numPerPage)-numPerPage+1;
               form.end.value = (StartPage * numPerPage);
               form.action = "/Board/list.do";
               form.submit();
            }
            
         </script>
         
         <!-- 페이징 처리 폼 -->
         <form name = "readFrm" method = "get">
            <input type = "hidden" name = "no">       <!-- 게시물 번호 -->
            <input type = "hidden" name = "start">      <!-- DB로부터 읽을 시작 번호 -->
            <input type = "hidden" name = "end">      <!-- DB로부터 읽을 끝 번호 -->
            <input type = "hidden" name = "nowPage">   <!-- 현재 페이지 번호 -->
         </form>     
         
         <table class="table"> 
            <tr>
               <td><span style = "color: hotpink;"><%=nowPage %></span> / <%=totalPage %> Page</td>
               <td style = "border:0px; text-align: right;">
                  <button class ="btn btn-secondary">처음으로</button>
                  <button class ="btn btn-secondary">글쓰기</button>
               </td>
            </tr>
         </table>
         <table class="table">
            <tr>
               <th>NO</th>
               <th>TITLE</th>
               <th>WRITER</th>
               <th>REGDATE</th>
               <th>COUNT</th>
            </tr>
            <%@page import = "java.util.*, com.korea.dto.*" %>
            <%
               ArrayList<BoardDTO> list =(ArrayList<BoardDTO>)request.getAttribute("list");
               
               for(int i = 0; i < list.size(); i++){
               
            %>
            <tr>
               <td><%=list.get(i).getNo() %></td>
               <td><%=list.get(i).getTitle() %></td>
               <td><%=list.get(i).getWriter() %></td>
               <td><%=list.get(i).getRegdate() %></td>
               <td><%=list.get(i).getCount() %></td>
            </tr>
            <%
               }
            %>
            <tr>
               <!-- 페이지네이션 -->
               <td colspan = 5 style = "border-bottom: 0px;">
                  <nav aria-label="Page navigation example">
                    <ul class="pagination">
                       <!-- 이전으로 -->
                       <%
                          if(nowBlock > 1){
                             
                          
                       %>
                      <li class="page-item">
                        <a class="page-link" href="javascript:block('<%=nowBlock-1 %>')" aria-label="Previous">
                          <span aria-hidden="true">&laquo;</span>
                        </a>
                      </li>
                      <%
                            }
                      %>
                      
                      <%
                         int pageStart = (nowBlock-1) * pagePerBlock + 1;
                         int pageEnd = ((pageStart + pagePerBlock-1) <= totalPage) ? (pageStart+pagePerBlock) : (totalPage+1);
                         for(; pageStart < pageEnd; pageStart++){
                      %>
                      
                      <!-- 페이지 번호 -->
                      <li class="page-item"><a class="page-link" href="javascript:paging('<%=pageStart %>')"><%=pageStart %></a></li>
                      <%
                         }
                      %>
                     <!-- 다음으로 -->
                     <% 
                        if(totalBlock > nowBlock){
                     
                     %>
                      <li class="page-item">
                        <a class="page-link" href="javascript:block('<%=nowBlock+1 %>')"  aria-label="Next">
                          <span aria-hidden="true">&raquo;</span>
                        </a>
                      </li>
                      <%
                        }
                      %>
                    </ul>
                  </nav>
               </td>
            </tr>
         </table>
         <a href="/Board/post.do">글쓰기</a>
      </div>
      <!-- Footer -->
   </div>
</body>
</html>