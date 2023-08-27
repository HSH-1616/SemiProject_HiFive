<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.semi.member.model.vo.Member" %>
<%@ page import="com.semi.productpage.model.vo.ShopPage" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage/myPageCategory.css" />
<%
 	Member m=(Member)session.getAttribute("loginMember"); // 로그인멤버 세션
%>
<section>
	<!-- 왼쪽 카테고리 section -->
	<div class="mypageCategory">
	<div>
			<button class="goToStoreBtn">
				<div class="goToStoreDiv">
					<ion-icon name="storefront-outline" class="goToStoreIcon"></ion-icon>
					<p class="goToStore">내 상점가기</p>
				</div>
			</button>
		</div>
		<div class="mypage">
			<h2>
				<a href="<%=request.getContextPath()%>/myPage/myPageMain.do?userId=<%=m.getUserId()%>">마이페이지</a>
			</h2>
		</div>
		<div class="myInfo">
			<h3 class="mypageMargin">나의 쇼핑정보</h3>
			<ul>
				<li class="mypageMargin"><a href="<%=request.getContextPath()%>/myPage/buyList.do?userId=<%=m.getUserId()%>" class="mypageAll">구매 내역</a></li>
				<li class="mypageMargin"><a href="<%=request.getContextPath()%>/myPage/sellList.do?userId=<%=m.getUserId()%>" class="mypageAll">판매 내역</a></li>
				<li class="mypageMargin"><a href="<%=request.getContextPath()%>/myPage/wishList.do?userId=<%=m.getUserId()%>" class="mypageAll">찜한 상품</a></li>
			</ul>
		</div>
		<div class="myprofileInfo">
			<h3 class="mypageMargin">내 정보</h3>
			<ul>
				<li class="mypageMargin"><a href="<%=request.getContextPath()%>/mypage/mypageUpdate.do?userId=<%=m.getUserId()%>" class="mypageAll">회원정보
						관리</a></li>
				<li class="mypageMargin"><a href="<%=request.getContextPath()%>/mypage/deleteUserCheck.do?userId=<%=m.getUserId()%>" class="mypageAll">회원탈퇴</a></li>
			</ul>
		</div>
		<div class="myInfo">
			<h3 class="mypageMargin">고객센터</h3>
			<ul>
				<li class="mypageMargin"><a href="<%=request.getContextPath()%>/service/inquiryList.do" class="mypageAll">1:1 문의하기</a></li>
				<li class="mypageMargin"><a href="<%=request.getContextPath()%>/service/boardList.do?notice=Y" class="mypageAll">공지사항</a></li>
				<li class="mypageMargin"><a href="<%=request.getContextPath()%>/service/boardList.do?notice=N" class="mypageAll">자주하는 질문</a></li>
			</ul>
		</div>
		
	</div>
	
	
	<script src="<%=request.getContextPath()%>/js/mypage/myPageCategory.js"></script>