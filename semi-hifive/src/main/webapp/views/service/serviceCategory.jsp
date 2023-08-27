<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/serviceCategory.css" />
<div class="ServiceCenterCategory">
	<h1>고객센터</h1>
	<ul>
		<li><label onclick="location.href='<%=request.getContextPath()%>/service/boardList.do?notice=Y'">공지사항</label></li>
		<li><label onclick="location.href='<%=request.getContextPath()%>/service/boardList.do?notice=N'">자주하는
				질문</label></li>
		<li><label onclick="location.href='<%=request.getContextPath()%>/service/inquiryList.do'">1:1 문의하기</label></li>
		<li>
		<label onclick="location.href='<%=request.getContextPath()%>/service/reportList.do'">
		신고하기</label></li>
	</ul>
</div>