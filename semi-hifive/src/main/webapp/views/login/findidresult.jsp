<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/member/findIdResult.css" />

<section class="findIdResult">
	<h1>아이디 조회 결과</h1>
	<div class="findIdResultDiv">
		<div class="findIdFlex">
			<%-- <img src="<%=request.getContextPath()%>/images/login/hifiveimg.png" class="hifiveImg" height="200px" width="200px"> --%>
			<h1 class="findIdValue"><%=request.getAttribute("userId")%></h1>
		</div>
	</div>
	
	<div class="nextButton">
		<button onclick="location.href='<%=request.getContextPath()%>/loginView.do'">로그인</button>
		<button onclick="location.href='<%=request.getContextPath()%>/findPwdView.do'">비밀번호찾기</button>
	</div>
</section>


<script src="<%=request.getContextPath()%>/js/member/findIdResult.js"></script>
<%@ include file="/views/common/footer.jsp"%>