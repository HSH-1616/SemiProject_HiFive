<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/findname.css" />


<div class="loginSection">
	<section class="login">
		<%
		if (loginMember == null) { // 로그인된상태에서 직접 login.view 화면으로 갔을때 로그인페이지가 아닌 메인화면으로 이동되는 예외처리
		%> 
		<form id="loginfrm" action="<%=request.getContextPath()%>/login.do"
				method="post" onsubmit="return fn_validation()"> 
			<h2>로그인</h2>
			<ul>
				<li><input type="text" id="loginUserId" name="userId" placeholder="아이디" title="아이디입력" value="<%=saveId != null ? saveId : ""%>"></li>
				<li><input type="password" name="password" placeholder="비밀번호" title="비밀번호입력"></li>
				<li><input type="checkbox" name="saveId" id="saveId" <%=saveId!=null?"checked":""%>>
				<label for="saveId">아이디 저장</label></li>
				<!-- <li><input type="submit" value="로그인"></li> -->
				<li><button>로그인</button></li>

			</ul>
		
		<div>
			<ul>
				<li><a href="<%=request.getContextPath()%>/enrollMember.do">회원가입</a></li>
				<li><a href="<%=request.getContextPath()%>/findIdView.do">아이디찾기</a></li>
				<li><a href="<%=request.getContextPath()%>/findPwdView.do">비밀번호찾기</a></li>
			</ul>
		</div>
	</form> 
 		<%
		} else { 
		%>

		<script>location.href = "<%=request.getContextPath()%>" </script>
		
		<%
		} 	
		%> 
	</section>
</div>


<script>
			const fn_validation=()=>{
				const userId=$("#loginUserId").val();
				if(userId.length<4){
					alert("아이디는 4글자 이상입니다.");
					$("#userId").val("");  // 문구띄어주고 아이디 빈칸으로바꾸고
					$("#userId").focus(); // 아이디부분에 포커스줌
			
					return false; // false면 값이 넘어가지 않음
				}
			}
</script>

<%@ include file="/views/common/footer.jsp"%>

