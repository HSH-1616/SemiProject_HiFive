<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.semi.mypage.model.vo.MemberComment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래자 선택하기</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>

</head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage/sellListBuyerChoice.css" />
<%
	List<MemberComment> mc = (List)request.getAttribute("comments");
	String productId = (String)request.getAttribute("productId");
	String userId = (String)request.getAttribute("userId");
%>
	<div class="selectBuyer">
        <div class="bagIcon">
            <ion-icon name="bag-handle-outline" style="font-size: 32px; color: white;"></ion-icon>
        </div>
        <div class="reviewTitle">
            <span class="reviewTitleSpan">어느 분과 거래하셨나요?</span>
        </div>
        <div class="listCon" style="overflow-x:hidden; width:340px; height:200px;">
        <%if(!mc.isEmpty()) {
        	for(MemberComment m : mc){%>
        	<div id="<%=m.getMember().getUserId() %>" class="commentList">
                <img src="<%=request.getContextPath() %>/upload/profileImg/<%=m.getMember().getProfileImg() %>" alt="" class="profileImg">
                <p><%=m.getMember().getNickName() %></p>
            </div>
            <%}
        } else {%>
        	<span class="noSpan">조회된 댓글이 없습니다.</span>
        <%} %>
        </div>
        <div class="submitBtn">
            <button type="submit" id="<%=productId %>" class="okBtn">확인</button>
            <button type="reset" class="noBtn closeBtn">취소</button>
        </div>
    </div>
<script src="<%=request.getContextPath()%>/js/mypage/sellListBuyerChoice.js"></script>    
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>