<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ include file="/views/mypage/myPageCategory.jsp"%>
<%@ page import="com.semi.mypage.model.vo.MemberWishList,java.util.Date,java.text.SimpleDateFormat, java.text.DecimalFormat" %>
<%
	List<MemberWishList> list = (List)request.getAttribute("wishList");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage/wishList.css" />
<!-- 오른쪽 찜목록 section -->
<div class="mypageMain">
	<!-- 찜목록 헤더 div -->
	<div class="mypageMainHeader">
		<h3>찜한 상품</h3>
	</div>

	<!-- 찜목록 div -->
	<div class="productContainer">
		<%if(!list.isEmpty()) {
			for(MemberWishList mw : list){ %>
		<div class="productAll">
			<div class="product">
				<div class="productImg">
					<a href="<%=request.getContextPath() %>/productpage?no=<%=mw.getProduct().getProductId()%>"><img src="<%=request.getContextPath() %>/upload/productRegist/<%=mw.getProductfile().getImageName() %>" alt=""></a>
					<label class="container"> <input id="<%=mw.getProduct().getProductId() %>" class="wishCheck" checked="checked" type="checkbox">
						<div class="checkmark">
							<svg viewBox="0 0 256 256">
								<rect fill="none" height="256" width="256"></rect>
                                <path d="M224.6,51.9a59.5,59.5,0,0,0-43-19.9,60.5,60.5,0,0,0-44,17.6L128,59.1l-7.5-7.4C97.2,28.3,59.2,26.3,35.9,47.4a59.9,59.9,0,0,0-2.3,87l83.1,83.1a15.9,15.9,0,0,0,22.6,0l81-81C243.7,113.2,245.6,75.2,224.6,51.9Z"
									stroke-width="20px" stroke="#FFF" fill="none"></path>
                            </svg>
						</div>
					</label>
				</div>
				<div class="proContent">
					<div class="buyDate">
						<h5 class="grayFont">
							<a href="<%=request.getContextPath() %>/headersearchcategory.do?categoryname=<%=mw.getCategory().getCategoryName() %>
							" class="aTag grayFont"><%=mw.getCategory().getCategoryName() %></a> &rsaquo;
							<a href="<%=request.getContextPath() %>/searchheadersubcategory.do?subcategroyname=<%=mw.getProduct().getSubCategoryName() %>" class="aTag grayFont"><%=mw.getSubCategory().getSubcategoryName() %></a>
						</h5>
						<p><%=new SimpleDateFormat("yyyy.MM.dd").format(mw.getProduct().getRegistTime())%></p>
					</div>
					<h4 class="contentMargin">
						<a href="<%=request.getContextPath() %>/productpage?no=<%=mw.getProduct().getProductId()%>" class="aTag productTitle"><%=mw.getProduct().getTitle() %></a>
					</h4>
					<div class="PriceNStatus">
						<h3><%=new DecimalFormat("###,###").format(mw.getProduct().getPrice()) %>원</h3>
						<div class="statusBtnDiv">
							<span class="statusBtn"><%=mw.getProduct().getSellStatus() %></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%}
        } else { %>
        <span>찜한 상품이 없습니다.</span>
        <%} %>
	</div>
	<!-- 페이지 버튼 -->
	    <div class="pageBar">
	     	<ul class="page">
	         <%=request.getAttribute("pageBar") %>
	         </ul>
	    </div>
</div>
</section>
<script src="<%=request.getContextPath()%>/js/mypage/wishList.js"></script>
<%@ include file="/views/common/footer.jsp"%>