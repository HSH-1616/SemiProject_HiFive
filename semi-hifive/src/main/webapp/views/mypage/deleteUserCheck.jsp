<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ include file="/views/mypage/myPageCategory.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage/deleteUserCheck.css" />

<!-- 오른쪽 탈퇴확인 section -->
        <div class="mypageMain">
            <!-- 탈퇴확인 헤더 div -->
            <div class="mypageMainHeader">
                <h3>회원탈퇴</h3>
            </div>
            <!-- 패스워드 입력 div -->
            <div class="pwdAll">
                <h3>비밀번호를 입력해주세요.</h3>
                <form class="form card" method="post" action="<%=request.getContextPath()%>/mypage/deleteUser.do" onsubmit="return checkPwd();">
                    <div class="field">
                        <input class="input" name="user_password" type="password" placeholder="비밀번호 입력" id="password">
                        <input type="hidden" name="userId" value="<%=request.getParameter("userId")%>">
                    </div>
                <div class="pwdButton">
                    <button type="submit" class="pwdUpdate">확인</button>
                    <button type="reset" onclick="clearInput();">취소</button>
                </div>
                </form>
            </div>
        </div>
</section>
<script src="<%=request.getContextPath()%>/js/mypage/deleteUserCheck.js"></script>
<%@ include file="/views/common/footer.jsp"%>