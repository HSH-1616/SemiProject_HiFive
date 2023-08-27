<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page
	import="com.semi.shop.model.vo.ShopPageAll,com.semi.shop.model.vo.ProductList
	,com.semi.shop.model.vo.ShopReview,java.util.List"%>
<%
List<ShopPageAll> s=(List) request.getAttribute("shop");
List<ProductList> p=(List) request.getAttribute("product");
List<ShopReview> r=(List) request.getAttribute("review");
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/shoppage/shopPage.css" />
<section>
		<input type="hidden" name="userId" value="<%=s.get(0).getMember().getUserId()%>">
      <div id="shopProfile">
        <div
          id="shopProfileLeft"
          style="background-image: url(<%=request.getContextPath()%>/upload/profileImg/<%=s.get(0).getMember().getProfileImg()%>)"
        >
          <div id="blurImg"></div>
          <div id="userProfile">
            <img src="<%=request.getContextPath()%>/upload/profileImg/<%=s.get(0).getMember().getProfileImg()%>" alt="" />
            <p><%=s.get(0).getMember().getNickName() %></p>
            <b>상품 <%=s.get(0).getProductCount() %> </b>
            <b> 매너온도 <%=s.get(0).getMember().getTemperature() %>℃</b>
          </div>
        </div>
        <div id="shopProfileRight">
          <p><%=s.get(0).getMember().getNickName() %></p>
          <div id="shopDetail">
            <ion-icon name="storefront-outline" class="storeIcon"></ion-icon>
            <span>상점개설일</span>
            <b><%=s.get(0).getMember().getEnrollDate() %></b>
            <ion-icon name="bag-handle"></ion-icon>
            <span>상품수</span>
            <b><%=s.get(0).getProductCount() %></b>
            <ion-icon name="thermometer-outline"></ion-icon>
            <span>매너온도</span>
            <b><%=s.get(0).getMember().getTemperature() %>℃</b>
            <ion-icon name="server-outline"></ion-icon>
            <span>거래횟수</span>
            <b><%=s.get(0).getTradeCount() %></b>
          </div>
          <div id="shopIntro">
            <pre>
<%=s.get(0).getShopPage().getIntroduce() %>
            </pre>
          </div>
        </div>
      </div>
      <div id="shopMain">
        <div id="shopBtn">
          <div class="radio-input">
            <label>
              <input
                type="radio"
                id="value-1"
                name="value-radio"
                value="value-1"
                checked
              />
              <span>상품 <%=s.get(0).getProductCount() %></span>
            </label>
            <label>
              <input
                type="radio"
                id="value-2"
                name="value-radio"
                value="value-2"
              />
              <span>상점후기 <%=s.get(0).getReviewCount() %></span>
            </label>

            <span class="selection"></span>
          </div>
        </div>
        <hr width="1280px" color="#eeeeee" noshade />
        <div id="shopProductContainer">
          <div id="selectCategory2">
            <div id="categoryName2">
              <h4>전체 (<span><%=s.get(0).getProductCount() %></span>)</h4>
            </div>
            <div id="categoryFunction">
              <span id="rsoon">최신순</span>
              <span id="psoon">인기도순</span>
              <span id="hsoon">최고가순</span>
              <span id="lsoon">최저가순</span>
            </div>
          </div>
          <div id="productImgContainer">
    <%if(p.size()!=0) {
        	for(ProductList pl:p){
        	%>
            <div id="pimgWraper">
              <div class="con-like">
                <input title="like" type="checkbox" class="like" />
                <div class="checkmark">
                  <svg
                    viewBox="0 0 24 24"
                    class="outline"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z"
                    ></path>
                  </svg>
                  <svg
                    viewBox="0 0 24 24"
                    class="filled"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"
                    ></path>
                  </svg>
                  <svg
                    class="celebrate"
                    width="100"
                    height="100"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <polygon points="10,10 20,20" class="poly"></polygon>
                    <polygon points="10,50 20,50" class="poly"></polygon>
                    <polygon points="20,80 30,70" class="poly"></polygon>
                    <polygon points="90,10 80,20" class="poly"></polygon>
                    <polygon points="90,50 80,50" class="poly"></polygon>
                    <polygon points="80,80 70,70" class="poly"></polygon>
                  </svg>
                </div>
              </div>
              <a href="<%=request.getContextPath()%>/productpage?no=<%=pl.getProduct().getProductId()%>"
                ><img
                  src="<%=request.getContextPath()%>/upload/productRegist/<%=pl.getProductFile().getImageName()%>"
                  alt=""
                />
                <p id="productName"><%=pl.getProduct().getTitle() %></p>
                <%String price=Integer.toString(pl.getProduct().getPrice());
						  price=price.replaceAll("\\B(?=(\\d{3})+(?!\\d))",",");%>		
                <p id="productPrice"><%=price%>원 <span><%=pl.getProduct().getRegistTime()%></span></p>
                <p id="productPlace">
                  <ion-icon name="location-outline"></ion-icon>
                  <%=pl.getProduct().getAreaName()%>
                </p>
              </a>
            </div>
            <%}}%>
            
          </div>
        </div>
        <div id="reviewContainer">
          <div id="reviewCount">
            <h4>상점후기 <span>(<%=s.get(0).getReviewCount() %>)</span></h4>
          </div>
          <%if(r.size()!=0){
          		for(ShopReview sr:r){
          %>
          <div class="shopReview">
            <div class="srProfile">
              <a href="<%=request.getContextPath()%>/shop?id=<%=sr.getMember().getUserId()%>">
                <img src="<%=request.getContextPath()%>/upload/profileImg/<%=sr.getMember().getProfileImg() %>" alt="" />
              </a>
              <a href="<%=request.getContextPath()%>/shop?id=<%=sr.getMember().getUserId()%>" class="srUser"><p><%=sr.getMember().getNickName() %></p></a>
            </div>
            <p class="sr"><%=sr.getReview().getReviewMsg() %></p>
            <span class="time"><%=sr.getReview().getReviewDate() %></span>
            <hr color="#eeeeee" noshade />
          </div>
          <%}}else{ %>
          <div id="noRecent">
			<h2>아직 후기가 없어요...</h2>  
			<img src="images/common/hifiveCharacter.png">        
          </div>
          <%} %>
        </div>
      </div>
    </section>
<script
	src="<%=request.getContextPath()%>/js/shoppage/shopPage.js?v=<%=System.currentTimeMillis()%>"></script>
<%@ include file="/views/common/footer.jsp"%>