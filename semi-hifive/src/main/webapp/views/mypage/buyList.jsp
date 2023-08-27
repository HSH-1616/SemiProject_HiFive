<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.semi.mypage.model.vo.ProductList,java.util.Date,java.text.SimpleDateFormat, java.text.DecimalFormat" %>
<%@ page import="com.semi.mypage.model.vo.ReviewTrade" %>
<%@ include file="/views/common/header.jsp"%>
<%@ include file="/views/mypage/myPageCategory.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage/buyList.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css" />
<%
	List<ProductList> product = (List)request.getAttribute("buyProduct");
	List<ReviewTrade> reviews = (List)request.getAttribute("reviews");
%>
<!-- 오른쪽 구매내역 section -->
    <div class="mypageMain">
      <!-- 구매내역 헤더 div -->
      <div class="mypageMainHeader">
        <h3>구매 내역</h3>
        <div class="sortContainer">
          <a href="<%=request.getContextPath() %>/myPage/buyList.do?userId=<%=m.getUserId() %>" class="aTag sort" id="sortDesc">최신 순</a>
          <p style="font-size: 12px;">|</p>
          <a href="<%=request.getContextPath() %>/sortAsc.do?userId=<%=m.getUserId() %>" class="aTag sort" id="sortAsc">오래된 순</a>
        </div>
      </div>
      <!-- 구매내역 목록 div -->
      <div class="productAll">
      <%if(!product.isEmpty()) {
             for(ProductList p : product){
        %>
        <div class="product">
          <a href="<%=request.getContextPath() %>/productpage?no=<%=p.getProduct().getProductId()%>"><img src="<%=request.getContextPath() %>/upload/productRegist/<%=p.getProductfile().getImageName() %>" alt=""></a>
          <div class="proContent">
            <h5 class="grayFont">
            <a href="<%=request.getContextPath() %>/headersearchcategory.do?categoryname=<=p.getCategory().getCategoryName() %>
            " class="aTag grayFont"><%=p.getCategory().getCategoryName() %></a> &rsaquo;
            <a href="<%=request.getContextPath() %>/searchheadersubcategory.do?subcategroyname=<%=p.getProduct().getSubCategoryName() %>"
                class="aTag grayFont"><%=p.getProduct().getSubCategoryName() %></a></h5>
            <h4 class="contentMargin"><a href="" class="aTag productTitle"><%=p.getProduct().getTitle() %></a></h4>
            <h3><%=new DecimalFormat("###,###").format(p.getProduct().getPrice()) %>원</h3>
          </div>
          <div class="buyDate">
            <p><%=new SimpleDateFormat("yyyy.MM.dd").format(new Date(p.getTrade().getSellDate().getTime())) %></p>
          	<button id="<%=p.getProduct().getProductId() %>" class="reviewBtn delMem openBtn <%=p.getReview().getReviewId()!=0?"hasReview":""%>" <%=p.getReview().getReviewId()!=0?"disabled":""%>>후기 남기기</button>
          </div>
        </div>
        <hr color="#eeeeee" noshade style="margin-top: 23px; margin-bottom: 23px;" />
       <%}
        } else { %>
        <span>구매한 상품이 없습니다.</span>
        <%} %>
      </div>
      <!-- 페이지 버튼 -->
	     <div class="pageBar">
	     	<ul class="page">
	         <%=request.getAttribute("pageBar") %>
	         </ul>
	    </div>
    </div>
    
    <!-- 후기 모달창 -->
    <%for(ProductList p : product){ %>
    <div class="modal hidden">
        <div class="bg"></div>
	        <div class="modal_delMem">
		    <div class="rating">
		        <div class="trashIcon">
		            <ion-icon name="chatbox-ellipses-outline" style="font-size: 32px; color: white;"></ion-icon>
		        </div>
		        <div class="reviewTitle">
		            <span class="sellerIdSpan"><%=p.getMember().getNickName() %></span><span class="reviewTitleSpan">님과의</span>
		            <p class="reviewTitleSpan">거래 후기를 남겨주세요!
		            </p>
		        </div>
		            <div class="radioBtn">
		                <div class="btnSpanDiv">
		                    <label for="super-happy" class="lb">
		                        <input type="radio" name="rating" class="super-happy" id="super-happy" value="super-happy">
		                        <svg class="svg" viewBox="0 0 24 24">
		                            <path
		                                d="M12,17.5C14.33,17.5 16.3,16.04 17.11,14H6.89C7.69,16.04 9.67,17.5 12,17.5M8.5,11A1.5,1.5 0 0,0 10,9.5A1.5,1.5 0 0,0 8.5,8A1.5,1.5 0 0,0 7,9.5A1.5,1.5 0 0,0 8.5,11M15.5,11A1.5,1.5 0 0,0 17,9.5A1.5,1.5 0 0,0 15.5,8A1.5,1.5 0 0,0 14,9.5A1.5,1.5 0 0,0 15.5,11M12,20A8,8 0 0,1 4,12A8,8 0 0,1 12,4A8,8 0 0,1 20,12A8,8 0 0,1 12,20M12,2C6.47,2 2,6.5 2,12A10,10 0 0,0 12,22A10,10 0 0,0 22,12A10,10 0 0,0 12,2Z">
		                            </path>
		                        </svg>
		                    </label>
		                    <p>좋아요!</p>
		                </div>
		                <div class="btnSpanDiv">
		                    <label for="neutral" class="lb">
		                        <input type="radio" name="rating" class="neutral" id="neutral" value="neutral">
		                        <svg class="svg" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
		                            version="1.1" width="100%" height="100%" viewBox="0 0 24 24">
		                            <path
		                                d="M8.5,11A1.5,1.5 0 0,1 7,9.5A1.5,1.5 0 0,1 8.5,8A1.5,1.5 0 0,1 10,9.5A1.5,1.5 0 0,1 8.5,11M15.5,11A1.5,1.5 0 0,1 14,9.5A1.5,1.5 0 0,1 15.5,8A1.5,1.5 0 0,1 17,9.5A1.5,1.5 0 0,1 15.5,11M12,20A8,8 0 0,0 20,12A8,8 0 0,0 12,4A8,8 0 0,0 4,12A8,8 0 0,0 12,20M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22C6.47,22 2,17.5 2,12A10,10 0 0,1 12,2M9,14H15A1,1 0 0,1 16,15A1,1 0 0,1 15,16H9A1,1 0 0,1 8,15A1,1 0 0,1 9,14Z">
		                            </path>
		                        </svg>
		                    </label>
		                    <p>보통이에요.</p>
		                </div>
		                <div class="btnSpanDiv">
		                    <label for="super-sad" class="lb">
		                        <input type="radio" name="rating" class="super-sad" id="super-sad" value="super-sad">
		                        <svg class="svg" viewBox="0 0 24 24">
		                            <path
		                                d="M12,2C6.47,2 2,6.47 2,12C2,17.53 6.47,22 12,22A10,10 0 0,0 22,12C22,6.47 17.5,2 12,2M12,20A8,8 0 0,1 4,12A8,8 0 0,1 12,4A8,8 0 0,1 20,12A8,8 0 0,1 12,20M16.18,7.76L15.12,8.82L14.06,7.76L13,8.82L14.06,9.88L13,10.94L14.06,12L15.12,10.94L16.18,12L17.24,10.94L16.18,9.88L17.24,8.82L16.18,7.76M7.82,12L8.88,10.94L9.94,12L11,10.94L9.94,9.88L11,8.82L9.94,7.76L8.88,8.82L7.82,7.76L6.76,8.82L7.82,9.88L6.76,10.94L7.82,12M12,14C9.67,14 7.69,15.46 6.89,17.5H17.11C16.31,15.46 14.33,14 12,14Z">
		                            </path>
		                        </svg>
		                    </label>
		                    <p>싫어요!</p>
		                </div>
		            </div>
		            <input type="text" placeholder="후기메세지를 작성해주세요.(최대 30자)" class="inputCss reviewMsg">
		            <div class="submitBtn">
		                <button class="okBtn">확인</button>
		                <button class="noBtn closeBtn">취소</button>
		            </div>
		    </div>
	    </div>
    </div>
    <%} %>
</section>
<script src="<%=request.getContextPath()%>/js/mypage/buyList.js"></script>
<%@ include file="/views/common/footer.jsp"%>