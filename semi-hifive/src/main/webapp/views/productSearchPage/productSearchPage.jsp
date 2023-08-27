<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@4.3.0/dist/chart.umd.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/searchpage/searchPage.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/default.css" />
<!--  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->

<script type="module"
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<%@ page
	import="com.semi.product.model.vo.Product,com.semi.shop.model.vo.ProductList,com.semi.search.model.vo.SearchCount,java.util.List"%>
<%
String content = (String) request.getAttribute("content");
List<ProductList> searchList = (List) request.getAttribute("searchList");
SearchCount searchCount = (SearchCount) request.getAttribute("searchCount");
%>
<section>
		<div id="productContainer">
			<div id="selectCategory2">
				<div id="categoryName2">
					<h4>
						<span><%=content%></span>의 검색결과 <span id="seachCount"> <%
 if (searchCount != null) {
 %> <%=searchCount.getCount()%>개 <%
 } else {
 %> 0개 <%
 }
 %>
						</span>
					</h4>
				</div>
				<div id="categoryFunction">
					<input type="hidden" name="content" value="<%=content%>"> 
						<span id="rsoon" value="r">최신순</span> 
						<span id="psoon" value="p">인기도순</span> 
						<span id="hsoon" value="h">최고가순</span>
						<span id="lsoon" value="l">최저가순</span>
				</div>
			</div>

			<%
			if (searchList.size() > 0) {
			%>
			<div id="productImgContainer">
				<%
				for (ProductList sl : searchList) {
				%>
				<div class="pimgWraper">
					<div class="con-like">
						<input title="like" type="checkbox" class="like" />
						<div class="checkmark">
							<svg viewBox="0 0 24 24" class="outline"
								xmlns="http://www.w3.org/2000/svg">
                    <path
									d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z"></path>
                  </svg>
							<svg viewBox="0 0 24 24" class="filled"
								xmlns="http://www.w3.org/2000/svg">
                    <path
									d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"></path>
                  </svg>
							<svg class="celebrate" width="100" height="100"
								xmlns="http://www.w3.org/2000/svg">
                    <polygon points="10,10 20,20" class="poly"></polygon>
                    <polygon points="10,50 20,50" class="poly"></polygon>
                    <polygon points="20,80 30,70" class="poly"></polygon>
                    <polygon points="90,10 80,20" class="poly"></polygon>
                    <polygon points="90,50 80,50" class="poly"></polygon>
                    <polygon points="80,80 70,70" class="poly"></polygon>
                  </svg>
						</div>
					</div>

					<a
						href="<%=request.getContextPath()%>/productpage?no=<%=sl.getProduct().getProductId()%>">
						 <img
						src="<%=request.getContextPath()%>/upload/productRegist/<%=sl.getProductFile().getImageName()%>"
						alt="" />

						<p id="productName"><%=sl.getProduct().getTitle()%></p>
						<%String price=Integer.toString(sl.getProduct().getPrice());
						  price=price.replaceAll("\\B(?=(\\d{3})+(?!\\d))",",");%>										
						<p id="productPrice"><%=price%>원<span><%=sl.getProduct().getRegistTime()%></span>
						</p>
						<p id="productPlace">
							<i class="bi bi-geo-alt"></i>
							<%=sl.getProduct().getAreaName()%></p>
					</a>
				</div>
				<%
				}
				%>
			</div>

			<%
			} else {
			%>
			<div id="noProductContainer">
				<h2>
					<span>"<%=content%>"
					</span> 인 상품이 없습니다
				</h2>
				<img
					src="<%=request.getContextPath()%>/images/common/hifiveCharacter.png">
			</div>
			<%
			}
			%>			
		</div>

	</div>

	<script></script>
	<script src="<%=request.getContextPath()%>/js/searchpage/searchPage.js"></script>
</section>


<%@ include file="/views/common/footer.jsp"%>