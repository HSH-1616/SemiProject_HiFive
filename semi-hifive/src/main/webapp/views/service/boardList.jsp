<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.semi.sc.model.dto.Board" %>
<%@ include file="/views/common/header.jsp" %>
<!-- 게시글 스타일 적용 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/boardList.css">
<%
	List<Board> boardList=(List)request.getAttribute("boardList"); //게시판 리스트
	char notice=((String)request.getAttribute("noticeYN")).charAt(0);
%>
<section>
	<%@ include file="/views/service/serviceCategory.jsp" %>
	<div class="ServiceCenter">
      <h2 class="ServicetHead"><%=notice=='Y'?"공지사항":"자주하는 질문" %>
      	<% if(loginMember!=null&&loginMember.getAuth().equals("M")){ %>
	      <button class="contentBtn" onclick="location.href='<%=request.getContextPath()%>/service/boardInsert.do'">글 작성</button>
	      <% } %>
      </h2>
      <% if(notice=='N'){ %>
      	<div class="QACategory">
      		<button class="QABtn">전체</button>
          <button class="QABtn">회원정보</button>
          <button class="QABtn">구매</button>
          <button class="QABtn">판매</button>
          <button class="QABtn">기타</button>
        </div>
      <% } %>
      <div class="boardContainer">
        <table>
       <%if(boardList!=null){ %>
	        <% for(Board b:boardList){ %>
		        <% if(notice=='Y'){ %>
					<tr onclick="location.href='<%=request.getContextPath()%>/service/boardContent.do?boardNo=<%=b.getBoardNo() %>'">
						<td><%=b.getBoardNo() %></td>
						<td class="noticeTitle"><%=b.getBoardTitle() %></td>
						<td><%=b.getBoardDate() %></td>
					</tr>
					<tr class="tableLine">
						<td colspan="3"></td>
					</tr>
					<% }else{ %>
					<tr onclick="location.href='<%=request.getContextPath()%>/service/boardContent.do?boardNo=<%=b.getBoardNo() %>'">
						<td><%=b.getBoardNo() %></td>
						<td class="QATitle">[<%=b.getBoardCategory() %>]<%=b.getBoardTitle() %></td>
					</tr>
					<tr class="tableLine">
						<td colspan="3"></td>
					</tr>
				<% }%>
			<% } %>
		<% }else{ %>
		<tr><td>조회된 데이터가 없습니다.</td></tr>
		<% } %>
        </table>
        <div class="pageBar">
	     	<ul class="page">
	         <%=request.getAttribute("pageBar") %>
	         </ul>
	    </div>
      </div>
    </div>
</section>
<script>
	$(".QABtn").click(e=>{
		const category=$(e.target).text();
		$(location).attr('href',"<%=request.getContextPath()%>/service/boardListCategory.do?data="+category);
	});
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css">
<%@ include file="/views/common/footer.jsp" %>