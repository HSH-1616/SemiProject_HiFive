<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.semi.main.model.vo.ProductElapsedTime, com.semi.mypage.model.vo.MemberWishList"%>
<%@ include file="/views/common/header.jsp"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<script>
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

   $.ajax({
      url: getContextPath() + "/main/mainPage.do",
      dataType: 'json',
      success: (data)=> {
         data.forEach((e, i) => {
        	 data[0].forEach((e, i) =>{
        		 $(".productTitle")[i].innerText = e.product.title;
                 $(".price")[i].innerText = e.product.price.toLocaleString() + "원";
                 $(".productImg img").eq(i).attr("src", getContextPath() + "/upload/productRegist/" + e.productFile.imageName);
                 $(".productLink").eq(i).attr("href", getContextPath() + "/productpage?no=" + e.product.productId);
                 $(".productTitle").eq(i).attr("href", getContextPath() + "/productpage?no=" + e.product.productId);
                 $(".wishCheck").eq(i).attr("id", e.product.productId);
                 if(e.product.productStatus == '미개봉'){
                       $(".statusBtn")[i].innerText = "NEW " + e.product.productStatus;
                 } else {
                    $(".statusBtn")[i].innerText = e.product.productStatus;
                 };
                 // 찜목록이 있을 때
            	 if(data[2]!=null){
    	        	 data[2].forEach((mywish, i) =>{
    	        		 $(".wishCheck").each((i,tag)=>{
    	        			if(tag.id==mywish.product.productId){
    	        				$(tag).prop("checked",true);
    	        			} 
    	        		 })
    	        	 });
            	 } 
        	 });
        	 data[1].forEach((e, i) =>{
        		 $(".newProductAll .productTitle")[i].innerText = e.product.title;
                 $(".newProductAll .price")[i].innerText = e.product.price.toLocaleString() + "원";
                 $(".newProductAll .productImg img").eq(i).attr("src", getContextPath() + "/upload/productRegist/" + e.productFile.imageName);
                 $(".newProductAll .productLink").eq(i).attr("href", getContextPath() + "/productpage?no=" + e.product.productId);
                 $(".newProductAll .productTitle").eq(i).attr("href", getContextPath() + "/productpage?no=" + e.product.productId);
                 $(".newProductAll .wishCheck").eq(i).attr("id", e.product.productId);
                 if(e.product.productStatus == '미개봉'){
                       $(".newProductAll .statusBtn")[i].innerText = "NEW " + e.product.productStatus;
                 } else {
                    $(".newProductAll .statusBtn")[i].innerText = e.product.productStatus;
                 };
              	// 찜목록이 있을 때
            	 if(data[2]!=null){
    	        	 data[2].forEach((mywish, i) =>{
    	        		 $(".wishCheck").each((i,tag)=>{
    	        			if(tag.id==mywish.product.productId){
    	        				$(tag).prop("checked",true);
    	        			} 
    	        		 })
    	        	 });
            	 } 
        	 });
        	 
            })
      },
      error: (r, m, e) => {
            console.log(r);
            console.log(m);
         }
   });
</script>
<div class="banner">
<!-- <div class="slideContainer">
		<div class="btnContainer">
			<ul class="slide">
				<li><img
					src="https://media.bunjang.co.kr/images/nocrop/1003399005_w2058.jpg"
					alt="" /></li>
				<li><img
					src="https://media.bunjang.co.kr/images/nocrop/1006416046_w1197.jpg"
					alt="" /></li>
				<li><img
					src="https://media.bunjang.co.kr/images/nocrop/1006977703_w1197.jpg"
					alt="" /></li>
				<li><img
					src="https://media.bunjang.co.kr/images/nocrop/1007175998_w1197.jpg"
					alt="" /></li>
			</ul>
			<button class="prevBtn btn"><</button>
			<button class="nextBtn btn">></button>
		</div>
	</div> -->
		 <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <button
              type="button"
              data-bs-target="#carouselExampleIndicators"
              data-bs-slide-to="0"
              class="active"
              aria-current="true"
              aria-label="Slide 1"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleIndicators"
              data-bs-slide-to="1"
              aria-label="Slide 2"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleIndicators"
              data-bs-slide-to="2"
              aria-label="Slide 3"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleIndicators"
              data-bs-slide-to="3"
              aria-label="Slide 4"
            ></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="4000">
              <img
                src="./images/common/banner1.gif"
                class="d-block w-100"
                alt="..."
              />
            </div>
            <div class="carousel-item" data-bs-interval="4000">
              <img
                src="./images/common/banner2.png"
                class="d-block w-100"
                alt="..."
              />
            </div>
            <div class="carousel-item" data-bs-interval="4000">
              <img
                src="https://media.bunjang.co.kr/images/nocrop/1006977703_w1197.jpg"
                class="d-block w-100"
                alt="..."
              />
            </div>
            <div class="carousel-item" data-bs-interval="4000">
              <img
                src="https://media.bunjang.co.kr/images/nocrop/1007175998_w1197.jpg"
                class="d-block w-100"
                alt="..."
              />
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
</div>
<section>
	<!-- 광고배너 -->
	

	<!-- 카테고리 -->
	<div class="mainCategoryDiv">
	<div class="cate1">
		<a href="<%=request.getContextPath()%>/categoryproductlist.do">
			<div class="categoryLink">
				<div class="categoryBgAll">
					<ion-icon class="cateIcon" name="star"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">전체</span>
				</div>
			</div>
		</a> <a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=패션의류">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="shirt"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">패션의류</span>
				</div>
			</div>
		</a> <a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=패션잡화">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="sparkles"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">패션잡화</span>
				</div>
			</div>
		</a> <a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=가전제품">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="construct"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">가전제품</span>
				</div>
			</div>
		</a> <a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=PC/모바일">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="desktop"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">PC/모바일</span>
				</div>
			</div>
		</a> 
		</div>
		<div class="cate2">
		<a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=가구/인테리어">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="bed"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">가구/인테리어</span>
				</div>
			</div>
		</a> <a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=리빙/생활">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="leaf"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">리빙/생활</span>
				</div>
			</div>
		</a> <a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=스포츠/레저">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="golf"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">스포츠/레저</span>
				</div>
			</div>
		</a> <a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=도서/음반/문구">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="library"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">도서/음반/문구</span>
				</div>
			</div>
		</a> <a
			href="<%=request.getContextPath()%>/headersearchcategory.do?categoryname=차량/오토바이">
			<div class="categoryLink">
				<div class="categoryBg">
					<ion-icon class="cateIcon" name="speedometer"></ion-icon>
				</div>
				<div class="spanDiv">
					<span class="categoryTitle">차량/오토바이</span>
				</div>
			</div>
		</a>
		</div>
	</div>
	<!-- 인기상품 -->
	<div class="popularProDiv">
		<div class="proTitleDiv">
			<h1 class="proTitle">인기상품</h1>
			<button class="moreBtn poMoreBtn">더보기</button>
		</div>
		<div class="productDiv">
			<%
			for (int i = 0; i < 8; i++) {
			%>
			<div class="productAll">
				<div class="product">
					<div class="productImg">
						<a class="productLink" href=""><img src="" alt=""></a>
						<%
						if (loginMember == null) {
						%>
						<label class="container"> <input id="" class="notLogin"
							type="checkbox">
							<div class="checkmark">
								<svg viewBox="0 0 256 256">
                                            <rect fill="none"
										height="256" width="256"></rect>
                                            <path
										d="M224.6,51.9a59.5,59.5,0,0,0-43-19.9,60.5,60.5,0,0,0-44,17.6L128,59.1l-7.5-7.4C97.2,28.3,59.2,26.3,35.9,47.4a59.9,59.9,0,0,0-2.3,87l83.1,83.1a15.9,15.9,0,0,0,22.6,0l81-81C243.7,113.2,245.6,75.2,224.6,51.9Z"
										stroke-width="20px" stroke="#FFF" fill="none"></path>
                                        </svg>
							</div>
						</label>
						<%
						} else {
						%>
						<label class="container"> <input id="" class="wishCheck"
							type="checkbox">
							<div class="checkmark">
								<svg viewBox="0 0 256 256">
                                            <rect fill="none"
										height="256" width="256"></rect>
                                            <path
										d="M224.6,51.9a59.5,59.5,0,0,0-43-19.9,60.5,60.5,0,0,0-44,17.6L128,59.1l-7.5-7.4C97.2,28.3,59.2,26.3,35.9,47.4a59.9,59.9,0,0,0-2.3,87l83.1,83.1a15.9,15.9,0,0,0,22.6,0l81-81C243.7,113.2,245.6,75.2,224.6,51.9Z"
										stroke-width="20px" stroke="#FFF" fill="none"></path>
                                        </svg>
							</div>
						</label>
						<%
						}
						%>
					</div>
					<div class="proContent">
						<h4 class="contentMargin">
							<a href="" class="aTag productTitle">상품명</a>
						</h4>
						<div class="PriceNStatus">
							<h3 class="price">000원</h3>
							<div class="statusBtnDiv">
								<span class="statusBtn">NEW 미개봉</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<hr color="#eeeeee" noshade
		style="margin-top: 16px; margin-bottom: 40px;" />
	<!-- 최신상품 -->
	<div class="popularProDiv">
		<div class="proTitleDiv">
			<h1 class="proTitle">최신상품</h1>
			<button class="moreBtn newMoreBtn">더보기</button>
		</div>
		<div class="productDiv">
			<%
			for (int i = 0; i < 8; i++) {
			%>
			<div class="productAll newProductAll">
				<div class="product">
					<div class="productImg">
						<a class="productLink" href=""><img src="" alt=""></a>
						<%
						if (loginMember == null) {
						%>
						<label class="container"> <input id="" class="notLogin"
							type="checkbox">
							<div class="checkmark">
								<svg viewBox="0 0 256 256">
                                            <rect fill="none"
										height="256" width="256"></rect>
                                            <path
										d="M224.6,51.9a59.5,59.5,0,0,0-43-19.9,60.5,60.5,0,0,0-44,17.6L128,59.1l-7.5-7.4C97.2,28.3,59.2,26.3,35.9,47.4a59.9,59.9,0,0,0-2.3,87l83.1,83.1a15.9,15.9,0,0,0,22.6,0l81-81C243.7,113.2,245.6,75.2,224.6,51.9Z"
										stroke-width="20px" stroke="#FFF" fill="none"></path>
                                        </svg>
							</div>
						</label>
						<%
						} else {
						%>
						<label class="container"> <input id="" class="wishCheck"
							type="checkbox">
							<div class="checkmark">
								<svg viewBox="0 0 256 256">
                                            <rect fill="none"
										height="256" width="256"></rect>
                                            <path
										d="M224.6,51.9a59.5,59.5,0,0,0-43-19.9,60.5,60.5,0,0,0-44,17.6L128,59.1l-7.5-7.4C97.2,28.3,59.2,26.3,35.9,47.4a59.9,59.9,0,0,0-2.3,87l83.1,83.1a15.9,15.9,0,0,0,22.6,0l81-81C243.7,113.2,245.6,75.2,224.6,51.9Z"
										stroke-width="20px" stroke="#FFF" fill="none"></path>
                                        </svg>
							</div>
						</label>
						<%
						}
						%>
					</div>
					<div class="proContent">
						<h4 class="contentMargin">
							<a href="" class="aTag productTitle">상품명</a>
						</h4>
						<div class="PriceNStatus">
							<h3 class="price">000원</h3>
							<div class="statusBtnDiv">
								<span class="statusBtn">NEW 미개봉</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>



</section>
<script
	src="<%=request.getContextPath()%>/js/productsearchchartpage/test.js"></script>
<script src="<%=request.getContextPath()%>/js/common/main.js"></script>
<%@ include file="/views/common/footer.jsp"%>