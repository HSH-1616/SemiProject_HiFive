<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ include file="/views/mypage/myPageCategory.jsp"%>
<%@ page import="com.semi.mypage.model.vo.MemberShopPage" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage/myPageUpdate.css" />
<%
	MemberShopPage infoMS = (MemberShopPage) request.getAttribute("infoMemberShopPage");
%>
<!-- 오른쪽 회원정보관리 section -->
<div class="mypageMain">
	<!-- 회원정보관리 헤더 div -->
	<div class="mypageMainHeader">
		<h3>회원정보 관리</h3>
	</div>
	<!-- 회원정보관리 div -->
	<div class="updateMember">
	<form action="<%=request.getContextPath() %>/member/updateEndMember.do" id="updateUserFrm" method="post" enctype="multipart/form-data" onsubmit="return updateMember();">
		<div class="infoMem">
			<img
				src="<%=request.getContextPath()%>/upload/profileImg/<%=infoMS.getMember().getProfileImg()%>"
				alt="" style="width: 170px; height: 170px;" class="profileImg">
			<div class="nickNprofileImg">
				<div class="nicknameCon">
					<h1 class="nickname"><%=infoMS.getMember().getNickName()%></h1>
				</div>
				<div class="profileButton">
					<button type="button" class="profileImgUpdate">이미지 변경</button>
					<button type="button" class="profileImgDel">삭제</button>
					<input type="file" accept=".jpg, .png" class="profileImgInput" name="uploadProfile">
				</div>
			</div>
		</div>
		<!-- 프로필 수정 input -->
		<hr width="1050px" color="#313030" noshade style="margin-top: 35px;" />
			<div class="profileUPdateInput">
				<div class="profilePart">
					<h4>이메일</h4>
				</div>
				<input type="text" placeholder="<%=infoMS.getMember().getEmail()%>"
					class="inputCssReadOnly" disabled>
			</div>

			<hr width="1050px" color="#eee" />
			<div class="profileUPdateInput">
				<div class="profilePart">
					<h4>아이디</h4>
				</div>
				<input type="text" placeholder="<%=infoMS.getMember().getUserId()%>"
					class="inputCssReadOnly idUpdateInput"
					value="<%=infoMS.getMember().getUserId()%>" name="myPageUserId" readonly>
			</div>
			<hr width="1050px" color="#eee" />
			<div class="profileUPdateInput">
				<div class="profilePart">
					<h4>닉네임</h4>
				</div>
				<input id="nicknameInput" name="myPageNickname" type="text"
					placeholder="<%=infoMS.getMember().getNickName()%>" value="<%=infoMS.getMember().getNickName()%>"
					class="inputCss">
				<span class="nicknameDuplSpan"></span>
			</div>
			<hr width="1050px" color="#eee" />
			<div class="profileUPdateInput">
				<div class="profilePart">
					<h4>상점소개글</h4>
				</div>
				<textarea type="text" placeholder="<%=infoMS.getShopPage().getIntroduce()%>"
					id="myPageIntroduce" class="inputText" name="myPageIntroduce" rows="3"><%=infoMS.getShopPage().getIntroduce()%></textarea>
				<span class="introduceSpan"></span>
			</div>
			<hr width="1050px" color="#313030" noshade
				style="margin-bottom: 20px;" />
			<div class="profileButton btnEndMargin">
				<div class="infoBtn">
					<button type="submit" class="profileUpdate">정보 수정</button>
					<button type="reset" class="resetUpdate">취소</button>
				</div>
				<button type="button" class="checkDB" onclick="fn_updatePassword();">비밀번호 변경</button>
			</div>
		</form>

	</div>
</div>
</section>
<script src="<%=request.getContextPath()%>/js/mypage/myPageUpdate.js"></script>
<%@ include file="/views/common/footer.jsp"%>