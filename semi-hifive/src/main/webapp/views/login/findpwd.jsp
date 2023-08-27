<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/findname.css" />

<section class="login">
	<form action="<%=request.getContextPath()%>/findPwd.do" id="findpassfrm">
		<h2>비밀번호 찾기</h2>
		<ul>
			<li><input type="text" placeholder="아이디" name="id" title="아이디입력"></li>
			<li><input type="text" placeholder="이름" name="name" title="이름입력"></li>
			<li><input type="email" placeholder="이메일" name="email" title="이메일입력"></li>
			<!-- <li><input type="submit" value="비밀번호찾기"></li> -->
			<li><button>비밀번호 찾기</button></li>
			
		</ul>

		<div>
			<ul>
				<li><a href="<%=request.getContextPath()%>/loginView.do">로그인</a></li>
				<li><a href="<%=request.getContextPath()%>/findIdView.do">아이디찾기</a></li>
				<li><a href="<%=request.getContextPath()%>/enrollMember.do">회원가입</a></li>
			</ul>
		</div>
	</form> 
</section>

<%@ include file="/views/common/footer.jsp"%>