<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.semi.member.model.vo.Member"%>


<%
	Member m = (Member)request.getAttribute("Member");
%>


<style>
.updateUser{
    position: relative;
 	width: 80%;
	float: right;
	height: 100vh;
}
</style>

<section class="updateUser">
		   <form action="<%=request.getContextPath()%>/updateMemberEnd.do">
			   <h3>회원수정</h3>
	           <input type="email" placeholder="이메일 주소" id=updateEmail name="email" value=<%=m.getEmail()%> readonly>    
	           <input type="text" placeholder="아이디" id="updateUserId" name="userId" value=<%=m.getUserId()%> readonly>
	                
			   <input type="text" placeholder="이름" id="updateUserName" name="userName" value=<%=m.getUserName() %>>
	           <input type="text" placeholder="닉네임" id="updateNickName" name="nickName" value=<%=m.getNickName() %>>
	           
	           <input type="number" placeholder="신고누적수" id="updateDeclareCount" name="declareCount" value=<%=m.getDeclareCount()%>>
			   <input type="date" placeholder="날짜" id="updateEnrollDate" name="enrollDate" value=<%=m.getEnrollDate()%>>
			   <input type="number"  placeholder="온도" id="updateTemp" name="temp" value=<%=m.getTemperature()%>>
				
	           <input type="submit" class="memberSubmit" value="회원수정">
           </form>
</section>

<script>

	
	
</script>