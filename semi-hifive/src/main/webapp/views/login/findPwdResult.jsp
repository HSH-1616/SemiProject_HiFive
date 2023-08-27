<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/findPwdResult.css"/>


<section>
	<input type="hidden" id="hiddenEmail" name="email" value="<%=(String) request.getAttribute("userEmail")%>">
	<div class="findPwdClass1">
		<input type="text" id="findPwdAuth" placeholder="인증번호 입력"
			autocomplete="off">
		<button type="button" id="findPwdAuthButton">인증</button>
	</div>
	<span id="findPwdAuthId"></span>

	<div class="findPwdClass2">
		<input type="text" id="newPassword" name="pass" placeholder="새로운 패스워드를 입력하세요"
			autocomplete="off"> <input type="text"
			id="newPasswordCheck" placeholder="새로운 패스워드를 다시 입력하세요"
			autocomplete="off">
	</div>
	<button id="newPasswordButton">변경</button>
</section>


<script src="<%=request.getContextPath()%>/js/member/findPwd.js"></script>
<%@ include file="/views/common/footer.jsp"%>
