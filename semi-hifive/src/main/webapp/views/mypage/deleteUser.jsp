<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ include file="/views/mypage/myPageCategory.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage/deleteUser.css" />

<!-- 오른쪽 회원탈퇴 section -->
<div class="mypageMain">
	<!-- 회원탈퇴 헤더 div -->
	<div class="mypageMainHeader">
		<h3>회원탈퇴</h3>
	</div>
	<!-- 탈퇴 설명 div -->
	<div class="delMemberAll">
		<h3>잠깐! 탈퇴하기 전에 아래 내용을 확인해주세요.</h3>
		<div class="delMember">
			<div class="delMemberBg"></div>
			<div class="delMemContent">
				<h3>
					처음부터 다시 가입해야 해요 <img src="<%=request.getContextPath() %>/images/mypage/sadIcon.png" alt=""
						style="width: 16px; height: 16px; position: relative; top: 2px;">
				</h3>
				<p>
					탈퇴 회원의 정보는 바로 삭제됩니다.<br> 탈퇴 시 회원가입부터 다시 진행됩니다.
				</p>
			</div>
		</div>
		<div class="delMember">
			<div class="delMemberBg"></div>
			<div class="delMemContent">
				<h3>
					등록된 상품이 모두 삭제되어요 <img src="<%=request.getContextPath() %>/images/mypage/sadIcon2.png" alt=""
						style="width: 16px; height: 16px; position: relative; top: 2px;">
				</h3>
				<p>
					탈퇴 시 모든 판매 등록 상품이 삭제됩니다.<br> 더이상 판매가 불가능합니다.
				</p>
			</div>
		</div>
		<div class="delMemButton">
			<button class="delMem openBtn delBtn" id="popup_open_btn">탈퇴</button>
			<button onclick="location.replace('<%=request.getContextPath() %>/mypage/deleteUserCheck.do?userId=<%=loginMember.getUserId() %>')" class="delBtn">취소</button>
		</div>
	</div>
</div>

<div class="modal hidden">
	<div class="bg"></div>
	<div class="modal_delMem">
		<div class="trashIcon">
			<ion-icon name="trash-outline" style="font-size: 32px; color: white;"></ion-icon>
		</div>
		<h3>정말 탈퇴하시겠습니까?</h3>
		<p>
			탈퇴 시, 계정은 삭제되며<br> 복구되지 않습니다.
		</p>
		<div class="modal_button">
			<button onclick="location.replace('<%=request.getContextPath() %>/mypage/deleteUserEnd.do?userId=<%=loginMember.getUserId() %>')" class="delBtn">탈퇴</button>
			<button class="delBtn closeBtn">취소</button>
		</div>
	</div>
</div>
</section>
<script src="<%=request.getContextPath()%>/js/mypage/deleteUser.js"></script>
<%@ include file="/views/common/footer.jsp"%>