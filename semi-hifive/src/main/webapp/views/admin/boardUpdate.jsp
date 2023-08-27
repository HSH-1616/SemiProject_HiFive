<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.semi.sc.model.dto.Board"%>


<%
	Board b = (Board)request.getAttribute("Board");
%>


<style>
.updateBoard{
    position: relative;
 	width: 80%;
	float: right;
	height: 100vh;
}
</style>

<section class="updateBoard">
		   <form action="<%=request.getContextPath()%>/updateBoardEnd.do">
			   <h3>회원수정</h3>
	           <input type="text" placeholder="공지사항번호" id=updateBoardNo name="BoardNo" value=<%=b.getBoardNo()%> readonly>    
	           <input type="text" placeholder="작성자" id="updateBoardWriter" name="BoardWriter" value=<%=b.getBoardWriter()%> readonly>
	                
			   <input type="text" placeholder="제목" id="updateBoardTitle" name="BoardTitle" value=<%=b.getBoardTitle() %>>
	           <input type="text" placeholder="내용" id="updateBoardContent" name="BoardContent" value=<%=b.getBoardContent() %>>
	           
			   <input type="date" placeholder="날짜" id="updateBoardDate" name="BoardDate" value=<%=b.getBoardDate()%>>
			   <input type="text"  placeholder="카테고리" id="updateBoardCategory" name="BoardCategory" value=<%=b.getBoardCategory()%>>
				
	           <input type="submit" class="boardSubmit" value="공지사항수정">
           </form>
</section>