<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경하기</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>

</head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage/updatePassword.css" />
<form action="<%=request.getContextPath()%>/mypage/updatePasswordEnd.do" method="post" onsubmit="return fn_pwdCheck();">
	<div class="pwdUpdatefrm">
		<h2 class="pwdTitle">비밀번호 변경</h2>
		<div class="pwdUpdateDiv">
			<input name="oriPwd" type="password" class="inputCss oriPwd" placeholder="현재 비밀번호">
		</div>
		<div class="pwdUpdateDiv">
			<input name="newPwd" type="password" class="inputCss newPwd" placeholder="새 비밀번호">
		</div>
		<div class="pwdUpdateDiv">
			<input name="newPwdCheck" type="password" class="inputCss newPwdCheck" placeholder="새 비밀번호 확인">
		</div>
		<div class="eyeIcons">
			<ion-icon name="eye-off" class="eyeIcon"></ion-icon>
		</div>
		<div class="pwdSpanDiv">
			<span class="pwdSpan">*영대소문자, 특수기호, 숫자 포함 8~16글자</span>
		</div>
		<div class="pwdButton">
			<input type="hidden" name="userId" value="<%=request.getAttribute("userId")%>">
			<input type="submit" class="checkDB" value="비밀번호변경">
			<input type="reset" class="checkDB resetBtn" value="취소">
		</div>
	</div>
</form>
<script src="<%=request.getContextPath()%>/js/mypage/updatePassword.js"></script>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>