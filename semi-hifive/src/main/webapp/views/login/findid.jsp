<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/findname.css"/>


<section class="login">
	<form action="<%=request.getContextPath()%>/findId.do" id="findidfrm">
		<h2>아이디 찾기</h2>
		<ul>
			<li><input type="text" placeholder="이름" title="이름입력" id="nameId" name="name"></li>
			<li><input type="email" placeholder="이메일" title="이메일입력" id="emailId" name="email"></li>
			<!-- <li><input type="submit" value="아이디찾기"></li> -->
			<li><button >아이디찾기</button></li>
		</ul>

		<div id="findIdId">
			<ul>
				<li><a href="<%=request.getContextPath()%>/loginView.do">로그인</a></li>
				<li><a href="<%=request.getContextPath()%>/findPwdView.do">비밀번호찾기</a></li>
				<li><a href="<%=request.getContextPath()%>/enrollMember.do">회원가입</a></li>
			</ul>
		</div>
	</form>
</section>


<%@ include file="/views/common/footer.jsp"%>