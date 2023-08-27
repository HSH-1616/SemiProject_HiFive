<%@page import="com.semi.productlist.model.vo.ProductCategoryTimeList"%>
<%@page import="com.semi.category.model.vo.Category"%>
<%@page import="com.semi.category.model.vo.CategorySubCategory"%>
<%@ page import="com.semi.member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ProductCategoryTimeList> pricelist = (List)request.getAttribute("pricelist");
%>
<%
	List<CategorySubCategory> categorylist = (List)request.getAttribute("categorylist");
%>
<%
	List<Category> selectcategory = (List)request.getAttribute("category");
%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/productsearchchartpage.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
    <!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@4.3.0/dist/chart.umd.min.js"></script> -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>   

<%@ include file="/views/common/header.jsp" %>
<section>
      <div id="wraperContainer">
        <div id="leftCategory">
        <!-- <div id="filter">
			<div id="filterMain">
                <span id="pddfilter"><b>필터</b></span>
			</div>
			<div class="plusFilter">
			</div>	        
        </div> -->
          <div id="pdcContainer">
            <div class="pdcCategoryMain">
              <span
                id="categoryMain"
                data-toggle="collapse"
                href="#pddCategory"
                aria-expanded="false"
                aria-controls="pddCategory"
              >
                <b>카테고리</b>
                <i class="bi bi-caret-down-fill"></i>
              </span>
               
              <div class="collapse" id="pddCategory">
              
                <div id="pddContainer">
               	
                  <ul>
                    <div class="pdcCategory">
                    <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("A")){%>
                      <span onclick="category_btn('<%=c.getCategoryName()%>')"><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory1"
                        aria-expanded="false"
                        aria-controls="pddCategory1"
                      ></i>
						
                      <div class="collapse" id="pddCategory1">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("A")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    
                   <div class="pdcCategory">
                      <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("B")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory2"
                        aria-expanded="false"
                        aria-controls="pddCategory2"
                      ></i>

                      <div class="collapse" id="pddCategory2">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("B")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    <div class="pdcCategory">
                       <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("C")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory3"
                        aria-expanded="false"
                        aria-controls="pddCategory3"
                      ></i>

                      <div class="collapse" id="pddCategory3">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("C")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    <div class="pdcCategory">
                       <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("D")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory4"
                        aria-expanded="false"
                        aria-controls="pddCategory4"
                      ></i>

                      <div class="collapse" id="pddCategory4">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("D")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    <div class="pdcCategory">
                       <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("E")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory5"
                        aria-expanded="false"
                        aria-controls="pddCategory5"
                      ></i>

                      <div class="collapse" id="pddCategory5">
                       <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("E")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    <div class="pdcCategory">
                       <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("F")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory6"
                        aria-expanded="false"
                        aria-controls="pddCategory6"
                      ></i>

                      <div class="collapse" id="pddCategory6">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("F")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    <div class="pdcCategory">
                       <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("G")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory7"
                        aria-expanded="false"
                        aria-controls="pddCategory7"
                      ></i>

                      <div class="collapse" id="pddCategory7">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("G")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    <div class="pdcCategory">
                       <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("H")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory8"
                        aria-expanded="false"
                        aria-controls="pddCategory8"
                      ></i>

                      <div class="collapse" id="pddCategory8">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("H")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    <div class="pdcCategory">
                      <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("I")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory9"
                        aria-expanded="false"
                        aria-controls="pddCategory9"
                      ></i>

                      <div class="collapse" id="pddCategory9">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("I")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                    <div class="pdcCategory">
                       <%for(Category c : selectcategory) {
                    	if(c.getCategoryId().equals("J")){%>
                      <span><%=c.getCategoryName()%></span>
                      <%} } %>
                      <i
                        class="fa fa-plus-square"
                        data-toggle="collapse"
                        href="#pddCategory10"
                        aria-expanded="false"
                        aria-controls="pddCategory5"
                      ></i>

                      <div class="collapse" id="pddCategory10">
                        <%for(CategorySubCategory sc : categorylist){
                        	if(sc.getCategory().getCategoryId().equals("J")) {%>
                        <ul>
                          <li><a href="<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname=<%=sc.getSubCategory().getSubcategoryName()%>"><%=sc.getSubCategory().getSubcategoryName()%></a></li>
                        </ul>
                        <%} }%>
                      </div>
                    </div>
                  </ul>
                </div>
              </div>
              <hr />
            </div>
          
          </div>
         
          <div id="productCondition">
            <span
              id="categoryMain"
              data-toggle="collapse"
              href="#prdCategory"
              aria-expanded="false"
              aria-controls="prdCategory"
            >
              <b>상품상태</b>
              <i class="bi bi-caret-down-fill"></i>
            </span>

            <div class="collapse" id="prdCategory">
              <form>
                <label class="statusbtn">
                  <input type="radio" name="radio" checked="" />
                  <span>전체</span>
                </label>
                <label class="statusbtn">
                  <input type="radio" name="radio" />
                  <span>미개봉</span>
                </label>
                <label class="statusbtn">
                  <input type="radio" name="radio" />
                  <span>사용감 있음</span>
                </label>
              </form>
            </div>
            <hr />
          </div>
          <div id="priceWraper">
            <span
              id="categoryMain"
              data-toggle="collapse"
              href="#priceCategory"
              aria-expanded="false"
              aria-controls="priceCategory"
            >
              <b>가격</b>
              <i class="bi bi-caret-down-fill"></i>
            </span>
            <div class="collapse" id="priceCategory">
              <div class="radio-buttons">
                <label class="radio-button">
                  <input
                    value="option0"
                    name="option"
                    type="radio"
                    checked=""
                  />
                  <div class="radio-circle"></div>
                  <span class="radio-label">전체</span>
                </label>
              </div>
              <div class="radio-buttons">
                <label class="radio-button">
                  <input value="option1" name="option" type="radio" />
                  <div class="radio-circle"></div>
                  <span class="radio-label">10만원 이하</span>
                </label>
                <label class="radio-button">
                  <input value="option2" name="option" type="radio" />
                  <div class="radio-circle"></div>
                  <span class="radio-label">10~30만원</span>
                </label>
                <label class="radio-button">
                  <input value="option3" name="option" type="radio" />
                  <div class="radio-circle"></div>
                  <span class="radio-label">30~50만원</span>
                </label>
                <label class="radio-button">
                  <input value="option3" name="option" type="radio" />
                  <div class="radio-circle"></div>
                  <span class="radio-label">50~100만원</span>
                </label>
                <label class="radio-button">
                  <input value="option3" name="option" type="radio" />
                  <div class="radio-circle"></div>
                  <span class="radio-label">100만원 이상</span>
                </label>
              </div>
              <div id="prcContainer">
                <b>직접입력</b>
                <div class="prcSearch">
                  <input
                    type="text"
                    name="text"
                    class="prcinput"
                    placeholder="최소값"
                    maxlength="10"
                    onkeyup="inputNumberFormat(this);"
                  />
                  <div class="highlight"></div>
                </div>
                <div class="prcSearch">
                  <input
                    type="text"
                    name="text"
                    class="prcinput"
                    placeholder="최대값"
                    maxlength="10"
                    onkeyup="inputNumberFormat(this);"
                  />
                  <div class="highlight"></div>
                </div>
                <button id="prcBtn">검색</button>
              </div>
            </div>
            <hr />
          </div>
          <div id="localWraper">
            <span
              id="categoryMain"
              data-toggle="collapse"
              href="#localCategory"
              aria-expanded="false"
              aria-controls="localCategory"
            >
              <b>지역</b>
              <i class="bi bi-caret-down-fill"></i>
            </span>
            <div class="collapse" id="localCategory">
              <select name="sido1" id="sido1"></select>
              <select name="gugun1" id="gugun1" class="gugun1"></select>
            </div>
          </div>
        </div>
      
        <div id="productContainer">
          <div id="selectCategory">
            <div id="categoryName">
              <h4>전체   <span><%=request.getAttribute("totalData")%></span></h4>
            </div>
            <div id="categoryFunction">
              <span id ="recently" onclick="handleRecentlyClick();">최신순</span>
              <span id ="popular" onclick="handleRecentlyClick();">인기도순</span>
              <span id ="desc" onclick="handleDescClick();">최고가순</span>
              <span id ="asc" onclick="handleAscClick();">최저가순</span>
            </div>
          </div>
          
	          <div id="productImgContainer">
	      		<%for(ProductCategoryTimeList p : pricelist){%>
	            <div id="pimgWraper" onclick="location.assign('<%=request.getContextPath()%>/productpage?no=<%=p.getProductCategoryList().getProductId()%>')">
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
	
	             
	                <div id="payBtn">
	                  <span>하마페이</span>
	                </div>
	                <img
	                  src="<%=request.getContextPath()%>/upload/productRegist/<%=p.getProductfile().getImageName()%>"
	                  alt=""
	                /><%=p.getProductfile().getImageName()%>
	               <p id="productName"><%=p.getProductCategoryList().getProductTitle()%></p>
                <p id="productPrice"><%=p.getProductCategoryList().getPrice()%>
                <span><%if(p.getElapsedTime() < 60){%>
               		<%=p.getElapsedTime()+ "초 전"%>
               	<%}else if(p.getElapsedTime() >= 60 && p.getElapsedTime() < 3600) {%>
               	<%=(p.getElapsedTime() / 60) + "분 전"%>
               	<%}else if(p.getElapsedTime() >= 3600 && p.getElapsedTime() < 86400) {%>
               		<%=(p.getElapsedTime() / 60) / 60  + "시간 전"%>
               	<%}else if(p.getElapsedTime() >= 86400 && p.getElapsedTime() < 2592000) {%>
               		<%=(p.getElapsedTime() / 60) / 60 / 24 + "일 전"%>
               	<%}else if(p.getElapsedTime() >= 2592000 && p.getElapsedTime() < 31536000) {%>
               		<%=(p.getElapsedTime() / 60) / 60 / 24 / 30 + "개월 전"%>
               	<%}else{%>
               		<%=(p.getElapsedTime() / 60) / 60 / 24 / 30 / 12 + "년 전"%>
               	<%} %></span></p>
                <p id="productPlace">
                  <i class="bi bi-geo-alt"></i>
                  <%=p.getProductCategoryList().getAreaName()%>
	                </p>
	            </div>
	          <%}%>
	          
	          </div>
	          <div class="pageBar">
		     	<ul class="page">
		         <%=request.getAttribute("pageBar") %>
		         </ul>
		   	 </div>	
          </div>
        </div>
</section>
<script>
//카테고리 클릭시 서블릿으로 이동
function category_btn(categoryname){
	window.location.href = "<%=request.getContextPath()%>/headersearchcategory.do?categoryname="+categoryname;
};
$("#prdCategory label").click(function() {
    var spanText = $(this).find("span").text();

    if (spanText === "전체") {
        window.location.href = "<%=request.getContextPath()%>/headercategories.do";
    } else if (spanText === "미개봉") {
        window.location.href = "<%=request.getContextPath()%>/statuslist?status=" + spanText;
    } else if (spanText === "사용감 있음") {
        window.location.href = "<%=request.getContextPath()%>/statuslist?status=" + spanText;
    }
});
//조건된 가격을 클릭할때 출력하는 함수
$(".radio-buttons .radio-button").click(function(e) {
	   
		var selectedOption = $(this).find("input").val();
		
		if(selectedOption === "option0"){
			window.location.href = "<%=request.getContextPath()%>/categoryproductlist.do"
		} else if (selectedOption === "option1") {
			selectedOption = "<= 100000";
			window.location.href = "<%=request.getContextPath()%>/pricesearch?price="+selectedOption;
		} else if (selectedOption === "option2") {
			selectedOption = "BETWEEN 100000 AND 300000";
			window.location.href = "<%=request.getContextPath()%>/pricesearch?price="+selectedOption;
		} else if (selectedOption === "option3") {
			selectedOption = "BETWEEN 300000 AND 500000";
			window.location.href = "<%=request.getContextPath()%>/pricesearch?price="+selectedOption;
		} else if (selectedOption === "option4") {
			selectedOption = "BETWEEN 500000 AND 1000000";
			window.location.href = "<%=request.getContextPath()%>/pricesearch?price="+selectedOption;
		} else if (selectedOption === "option5") {
			selectedOption = "+>= 1000000";
			window.location.href = "<%=request.getContextPath()%>/pricesearch?price="+selectedOption;
		}
	});	
//input태그에 검색한 가격
$("#prcBtn").click(function() {
	    var minPrice = $(".prcinput[name='text'][placeholder='최소값']").val();
	    var maxPrice = $(".prcinput[name='text'][placeholder='최대값']").val();
	    if (minPrice && maxPrice) {
	    	window.location.href = "<%=request.getContextPath()%>/pricesearch?price="+"BETWEEN " + minPrice + " AND " + maxPrice;
	    } 
 	});
//지역검색클릭
	$("#gugun1").change(function() {
	    var selectedLocation = $('#sido1').val() + $(this).val();
	    window.location.href = "<%=request.getContextPath()%>/areasearch?area="+selectedLocation;
	  });

</script>
<script src="<%=request.getContextPath()%>/js/productsearchchartpage/test.js"></script>
<%@ include file="/views/common/footer.jsp" %>
  <%--   <script>
    //ajax로 페이징 처리한 페이지 바 선택시 출력해주는 함수
    function changePage(pageNo) {
        $.ajax({
            url: "<%=request.getContextPath()%>/categoryproductlist.do",
            type: "GET",
            data: {
                'cPage': pageNo,
                'numPerpage': 32
            },
            dataType: "html",
            success: function(data) {
                $("section").html(data);
            }
        });
    } --%>
    <%-- //대표카테고리 클릭시 출력 ajax
            function searchProduct(Cid){
           		$.ajax({
           			url: "<%=request.getContextPath()%>/serachcategory.do",
           			dateType: 'html',
           			data:{'Cid':Cid},
           			success: function(data){
           				$("#productContainer").html(data); 
           			}
           		});
           	}; --%>
           <%-- 	// 서브카테고리 클릭시 출력 ajax
			function subsearchProduct(sub){
				$.ajax({
          			url: "<%=request.getContextPath()%>/subserachcategory.do",
          			dateType: 'html',
          			data:{'sub':sub},
          			success: function(data){
          				$("#productContainer").html(data);
          			}
          		});
          	} --%>
           
          	<%-- // 왼쪽 카테고리 밑에서 중복 조건 추가
   			
            $(document).ready(function() { 
            	let conditions = {};
   				let categoryName="";
   				let subcategoryname="";
   				 //상품 카테고리 태그 클릭시 출력하는 함수
   				$(".pdcCategory span").click(function() {
   					categoryName = $(this).text();
    				conditions['categoryname'] = "CATEGORY_NAME = '" + categoryName + "'";
    				
    				
    				console.log(conditions);
    				getselectproduct(conditions);
     				removeKeyFromProduct(conditions);
     				
    			});
   				// 서브카테고리
   				 $(".collapse ul li").click(function(){
   					 subcategoryname = $(this).text();
   				  	 var categoryName = $(this).closest(".pdcCategory").find("span").text();

   					 conditions['subcategoryname'] = "SUBCATEGORY_NAME = '" + subcategoryname +"'" ;
   					 
   				  
   					console.log(conditions);
   					getselectproduct(subcategoryname);
   					removeKeyFromProduct(conditions);
   				
    				 });
   				// 상품상태 태그 클릭시 출력하는 함수
   				$("#prdCategory label").click(function() {
    				var spanText = $(this).find("span").text();

    				if (spanText === "전체") {
      					conditions['status'] = "";
    				} else if(spanText === "미개봉"){
      					conditions['status'] = "SELL_STATUS = '" + spanText + "'";
    				} else if(spanText === "사용감 있음"){
    					conditions['status'] = "SELL_STATUS = '" + spanText + "'";
    				}
    				console.log(conditions);
    				getselectproduct(conditions);
     				removeKeyFromProduct(conditions);
   			});
        	   // 조건된 가격을 클릭할때 출력하는 함수
        	   $(".radio-buttons .radio-button").click(function(e) {
        		   
    				var selectedOption = $(this).find("input").val();
					
    				if(selectedOption === "option0"){
    					conditions['price']= "";
        	   		} else if (selectedOption === "option1") {
      					conditions['price']="PRICE <= 100000 ";
    				} else if (selectedOption === "option2") {
    					conditions['price']="PRICE BETWEEN 100000 AND 300000";
    				} else if (selectedOption === "option3") {
    					conditions['price']="PRICE BETWEEN 300000 AND 500000";
    				} else if (selectedOption === "option4") {
    					conditions['price']="PRICE BETWEEN 500000 AND 1000000";
    				} else if (selectedOption === "option5") {
    					conditions['price']="PRICE >= 1000000";
    				}
    				console.log(conditions);
    				getselectproduct(conditions);
     				removeKeyFromProduct(conditions);
     				
   				});	
        	   
        	   // input태그에 검색한 가격
        	   $("#prcBtn").click(function() {
        		    var minPrice = $(".prcinput[name='text'][placeholder='최소값']").val();
        		    var maxPrice = $(".prcinput[name='text'][placeholder='최대값']").val();

        		    
        		    if (minPrice && maxPrice) {
        		    	conditions['price']="PRICE BETWEEN " + minPrice + " AND " + maxPrice;
        		    } 
        		    console.log(conditions);
        		    getselectproduct(conditions);
         		    removeKeyFromProduct(conditions);
         		   
 	           	});
	        	// 지역검색클릭
	           	$("#gugun1").change(function() {
	           		
	           	    var selectedLocation = $('#sido1').val() + $(this).val();
	           	    if (selectedLocation) {
	           	    	conditions['area']="AREA_NAME LIKE '%" + selectedLocation + "%'";
	           	    }
	           	 	console.log(conditions);
	           	    getselectproduct(conditions);
 	           	 	removeKeyFromProduct(conditions);
 	           		
 	           	  });
	           	function handleRecentlyClick() {
					if(conditions['categoryname'] == null && conditions['subcategoryname'] == null && conditions['status'] == null && conditions['price'] == null && conditions['area'] == null){
		                console.log(conditions);
						$.ajax({
		                    url: "<%=request.getContextPath()%>/resentlyproductlist",
		                    dataType: 'html',
		                    success: function(data) {
		                        $("#productContainer").html(data);
		                    }
		                });
		            	}else if(conditions['categoryname'] !== null || conditions['subcategoryname'] !== null || conditions['status'] !== null || conditions['price'] !== null || conditions['area'] !== null){
		            		console.log(conditions);
		            		$.ajax({
			                    url: "<%=request.getContextPath()%>/getproduct.do",
			                    dataType: 'html',
			                    data: conditions,
			                    success: function(data) {
			                        $("#productContainer").html(data);
			                    }
			                });
		            	}
				}
				function handleDescClick() {
	         	if(conditions['categoryname'] == null && conditions['subcategoryname'] == null && conditions['status'] == null && conditions['price'] == null && conditions['area'] == null){
	         		console.log(conditions);
	         	$.ajax({
	                 url: "<%=request.getContextPath()%>/entiremaxprice",
	                 dataType: 'html',
	                 success: function(data) {
	                     $("#productContainer").html(data);
	                 }
	             });
	         	}else if(conditions['categoryname'] !== null || conditions['subcategoryname'] !== null || conditions['status'] !== null || conditions['price'] !== null || conditions['area'] !== null){
	         		console.log(conditions);
	         		$.ajax({
		                    url: "<%=request.getContextPath()%>/maxprice",
		                    dataType: 'html',
		                    data: conditions,
		                    success: function(data) {
		                        $("#productContainer").html(data);
		                    }
		                });
	         	}
	         }
	         function handleAscClick() {
	         	if(conditions['categoryname'] == null && conditions['subcategoryname'] == null && conditions['status'] == null && conditions['price'] == null && conditions['area'] == null){
		                $.ajax({
		                    url: "<%=request.getContextPath()%>/entireminprice",
		                    dataType: 'html',
		                    success: function(data) {
		                        $("#productContainer").html(data);
		                    }
		                });
		            	}else if(conditions['categoryname'] !== null || conditions['subcategoryname'] !== null || conditions['status'] !== null || conditions['price'] !== null || conditions['area'] !== null){
		            		$.ajax({
			                    url: "<%=request.getContextPath()%>/minprice",
			                    dataType: 'html',
			                    data: conditions,
			                    success: function(data) {
			                        $("#productContainer").html(data);
			                    }
			                });
		            	}
	         }		 --%>
	         	
/* 	       <!-- $(document).ready(function() {
	        // 필터에 넣기
	        	var currentFilters = 0; // 현재 필터 개수
				var existingFilter = null; // 기존 필터 객체
    		$('.pdcCategory span').on('click', function() {
        	if (currentFilters === 0) {
             categoryName = $(this).text(); // 클릭한 카테고리명 가져오기

            // 새로운 필터 태그 생성
            existingFilter = $('<div>').addClass('plusFiterbox')
                                      .append($('<div>').addClass('plusFiterboxText').text(categoryName))
                                      .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
									  .attr('categoryName', categoryName);
            $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
		    currentFilters++; // 현재 필터 개수 증가
        } else if (currentFilters === 1) {
        	var categoryName = $(this).text();
        	existingFilter.attr('categoryName', categoryName);
        	existingFilter.find('.plusFiterboxText').text(categoryName);
        }
        	
    });
    	//서브카테고리 필터에 넣어주기
    		$('.collapse ul li').on('click', function() {
            	if (currentFilters === 0) {
                subcategoryname = $(this).text(); // 클릭한 카테고리명 가져오기
                // 새로운 필터 태그 생성
                existingFilter = $('<div>').addClass('plusFiterbox')
                                          .append($('<div>').addClass('plusFiterboxText').text(subcategoryname))
                                          .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
										  .attr('subcategoryname', subcategoryname);
                $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
			    currentFilters++; // 현재 필터 개수 증가
            } else if (currentFilters === 1) {
            	//필터의 text값 업데이트시켜주기
            	subcategoryname = $(this).text(); // 클릭한 카테고리명 가져오기
            	existingFilter.attr('subcategoryname', subcategoryname);
            	existingFilter.find('.plusFiterboxText').text(subcategoryname);
            }
        });
    
    		$(document).on("click", ".plusFiterboxbtn button", function() {
    			$(this).closest(".plusFiterbox").remove();	
		});
    		
    		$('.statusbtn span').on('click', function() {
			    if (currentFilters === 0) {
			    // 새로운 필터 태그 생성
			      var statusTag = $(this).text(); // 클릭한 카테고리명 가져오기
			      existingFilter = $('<div>').addClass('plusFiterbox')
			        .append($('<div>').addClass('plusFiterboxText').text(statusTag))
			        .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
			        .attr('status', statusTag); // 태그의 속성값으로 키값 설정

			      $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
			      currentFilters++; // 현재 필터 개수 증가
			    } else if (currentFilters === 1) {
			      //필터의 text값 업데이트시켜주기
			      var statusTag = $(this).text(); // 클릭한 카테고리명 가져오기
			      existingFilter.attr('status', statusTag); // 필터의 태그 속성값 변경
			      existingFilter.find('.plusFiterboxText').text(statusTag); // 필터의 텍스트 값 업데이트
			    }
		 });		
    		
   }); */
	       
	        	// 클릭시 조건 삭제 함수
	        	 	/* var currentFilters = 0; // 현재 필터 개수
					var existingFilter = null; // 기존 필터 객체 */
	               -->
				<%-- function removeKeyFromProduct(conditions) {
					var currentFilters = 0; // 현재 필터 개수
					var existingFilter = null; // 기존 필터 객체
					
	            	var categoryname, subcategoryname, status, price, area;
	        		/* $(document).on("click", ".plusFiterboxbtn button", function() {
	        			$(this).closest(".plusFiterbox").remove();
	                    currentFilters--;// 현재 필터 개수 감소
	                    existingFilter = null; // 기존 필터 객체 초기화 */

	        		categoryname = $(this).closest(".plusFiterbox").attr("categoryname"); // 삭제할 키 값을 가져옴
	        		subcategoryname = $(this).closest(".plusFiterbox").attr("subcategoryname"); // 삭제할 키 값을 가져옴
	        		status = $(this).closest(".plusFiterbox").attr("status"); // 삭제할 키 값을 가져옴
	        		price = $(this).closest(".plusFiterbox").attr("price"); // 삭제할 키 값을 가져옴
	        		area = $(this).closest(".plusFiterbox").attr("area"); // 삭제할 키 값을 가져옴
	    	 		
	        		if(categoryname !== null){
	        			delete conditions['categoryname'];
	        			delete conditions['subcategoryname'];
	        		}else if(subcategoryname !== null){
	        			delete conditions['subcategoryname'];
	        		}else if(status !== null){
	        			delete conditions['status'];
	        		}else if(price !== null){
	        			delete conditions['price'];
	        		}else if(area !== null){
	        			delete conditions['area'];
	        		}
	        		console.log(conditions);
	        		getselectproduct(conditions);
	        		
	        		/* }); */
	              }
							/* $("#recently").click(function(){
				    			handleRecentlyClick();
							});

							$("#desc").click(function(){
				    			handleDescClick();
							});

							$("#asc").on("click", function() {
				   				 handleAscClick();
							}); */
					
						
			            
	           	// 키값으로 여러조건 가져오기 ajax
	           	function getselectproduct(conditions){
	           		 console.log(conditions);
	           		  $.ajax({
	           			url: "<%=request.getContextPath()%>/getproduct.do",
	           			dateType: 'html',
	           			data:conditions,
	           			success: function(data){
	          				if(conditions['categoryname'] == null && conditions['subcategoryname'] == null && conditions['status'] == null && conditions['price'] == null && conditions['area'] == null) {
	          					$.ajax({
	          						url: "<%=request.getContextPath()%>/categoryproductlist.do",
	          						dateType: 'html',
	          						success: function(data){
	          							$("section").html(data);
	          						}
	          					});
	          				} else if(conditions['categoryname'] !== null || conditions['subcategoryname'] !== null || conditions['status'] !== null || conditions['price'] !== null || conditions['area'] !== null){
	          			        $.ajax({
	          			            url: "<%=request.getContextPath()%>/getproduct.do",
	          			            dataType: 'html',
	          			            data: conditions,
	          			            success: function(data) {
	          			                $("#productContainer").html(data);
	          			                if (conditions['subcategoryname'] == null) {
	          			                    $("#categoryName span").text(categoryName);
	          			                } else {
	          			                    $("#categoryName span").text(categoryName + " > " + subcategoryname);
	          			                }
	          			            }
	          			        });
	          			    } 
	           			/*  } */
	           		 /* }); */
	           	};
	         /* // 동적 생성된 요소에 대한 이벤트 위임
	    	 	$(document).on("click", ".plusFiterboxbtn button", function() {
					$(this).closest(".plusFiterbox").remove();
					currentFilters--; // 현재 필터 개수 감소
				    existingFilter = null; // 기존 필터 객체 초기화	
	    	 	
	    	 	}); */
	        	// 전체 ready   
	            });
	    	
           	//필터에 넣어주기
           	     var currentFilters = 0; // 현재 필터 개수
    			var existingFilter = null; // 기존 필터 객체   
            	 function pdcCategoryClickHandler() {
    				if (currentFilters === 0) {
    	            var categoryName = $(this).text(); // 클릭한 카테고리명 가져오기

    	            // 새로운 필터 태그 생성
    	            existingFilter = $('<div>').addClass('plusFiterbox')
    	                                      .append($('<div>').addClass('plusFiterboxText').text(categoryName))
    	                                      .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
    										  .attr('categoryName', categoryName);
    	            $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
    			    currentFilters = 1; // 현재 필터 개수 증가
    	        } else if (currentFilters === 1) {
    	        	var categoryName = $(this).text();
    	        	existingFilter.attr('categoryName', categoryName);
    	        	existingFilter.find('.plusFiterboxText').text(categoryName);
    	        }
           	}
           	$('.pdcCategory span').on('click', pdcCategoryClickHandler);
           	
           	
           	 //카테고리 클릭시 필터추가
           	function collapseItemClickHandler() {
           		  if (currentFilters === 0) {
                    var subCategoryName = $(this).text(); // 클릭한 카테고리명 가져오기
                    // 새로운 필터 태그 생성
                    existingFilter = $('<div>').addClass('plusFiterbox')
                                              .append($('<div>').addClass('plusFiterboxText').text(subCategoryName))
                                              .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
    										  .attr('subCategoryName', subCategoryName);
                    $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
    			    currentFilters++; // 현재 필터 개수 증가
                } else if (currentFilters === 1) {
                	//필터의 text값 업데이트시켜주기
                	var subCategoryName = $(this).text(); // 클릭한 카테고리명 가져오기
                	existingFilter.attr('subCategoryName', subCategoryName);
                	existingFilter.find('.plusFiterboxText').text(subCategoryName);
                };
           	};
           	$('.collapse ul li').on('click', collapseItemClickHandler);
           	
           	//상품상테 클릭시 필터추가
            	function statusBtnClickHandler() {
			      if (currentFilters === 0) {
			    // 새로운 필터 태그 생성
			      var statusTag = $(this).text(); // 클릭한 카테고리명 가져오기
			      existingFilter = $('<div>').addClass('plusFiterbox')
			        .append($('<div>').addClass('plusFiterboxText').text(statusTag))
			        .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
			        .attr('status', statusTag); // 태그의 속성값으로 키값 설정

			      $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
			      currentFilters++; // 현재 필터 개수 증가
			    } else if (currentFilters === 1) {
			      //필터의 text값 업데이트시켜주기
			      var statusTag = $(this).text(); // 클릭한 카테고리명 가져오기
			      existingFilter.attr('status', statusTag); // 필터의 태그 속성값 변경
			      existingFilter.find('.plusFiterboxText').text(statusTag); // 필터의 텍스트 값 업데이트
		    };
           };
           $('.statusbtn span').on('click', statusBtnClickHandler);   
           
           function removeKeyFromProduct(conditions) {
   			$(this).closest(".plusFiterbox").remove();
               currentFilters--;// 현재 필터 개수 감소
               existingFilter = null; // 기존 필터 객체 초기화
     }
           $(".plusFiterboxbtn button").on("click", removeKeyFromProduct);
          --%>
      
				
			<!-- // 상품가격클릭시 필터에 적용
		 		/* $('.radio-label').on('click', function() {
		 			/* var currentFilters = 0; // 현재 필터 개수
					var existingFilter = null; // 기존 필터 객체 */
				    /* if (currentFilters === 0) {
				    // 새로운 필터 태그 생성
				      var price = $(this).text(); // 클릭한 카테고리명 가져오기
				      existingFilter = $('<div>').addClass('plusFiterbox')
				        .append($('<div>').addClass('plusFiterboxText').text(price))
				        .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
				        .attr('price', price); // 태그의 속성값으로 키값 설정

				      $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
				      currentFilters++; // 현재 필터 개수 증가
				    } else if (currentFilters === 1) {
				      //필터의 text값 업데이트시켜주기
				      var price = $(this).text(); // 클릭한 카테고리명 가져오기
				      existingFilter.attr('price', price); // 필터의 태그 속성값 변경
				      existingFilter.find('.plusFiterboxText').text(price); // 필터의 텍스트 값 업데이트
				    }
		 	 });  */
				
				  
				  // 지역옵션태그에 클릭시 필터에 적용
				  /* $('.gugun1').on('change', function() {
					/* var currentFilters = 0; // 현재 필터 개수
					var existingFilter = null; // 기존 필터 객체 */
				   /*  var selectedOption = $(this).find('option:selected');
				    var area = $('#sido1').val() + selectedOption.val(); // 클릭한 지역명

				    if (currentFilters === 0) {
				      // 새로운 필터 태그 생성
				      existingFilter = $('<div>')
				        .addClass('plusFiterbox')
				        .append(
				          $('<div>').addClass('plusFiterboxText').text(area),
				          $('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>')
				        )
				        .attr('area', area); // 태그의 속성값으로 키값 설정

				      $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
				      currentFilters++; // 현재 필터 개수 증가
				    } else if (currentFilters === 1) {
				      // 필터의 text값 업데이트시켜주기
				      existingFilter.attr('area', area); // 필터의 태그 속성값 변경
				      existingFilter.find('.plusFiterboxText').text(area); // 필터의 텍스트 값 업데이트
				    }
				  });  */
           	
           
			// 카테고리 클릭 이벤트 처리
			
 			/* var currentFilters = 0; // 현재 필터 개수
			var existingFilter = null; // 기존 필터 객체 */
    		/* $('.pdcCategory span').on('click', function() {
    		/* var currentFilters = 0; // 현재 필터 개수
			var existingFilter = null; // 기존 필터 객체 */
        	/* if (currentFilters === 0) {
            var categoryName = $(this).text(); // 클릭한 카테고리명 가져오기

            // 새로운 필터 태그 생성
            existingFilter = $('<div>').addClass('plusFiterbox')
                                      .append($('<div>').addClass('plusFiterboxText').text(categoryName))
                                      .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
									  .attr('categoryName', categoryName);
            $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
		    currentFilters++; // 현재 필터 개수 증가
        } else if (currentFilters === 1) {
        	var categoryName = $(this).text();
        	existingFilter.attr('categoryName', categoryName);
        	existingFilter.find('.plusFiterboxText').text(categoryName);
        }
        	
    }); */
    	//서브카테고리 필터에 넣어주기
    /* 		$('.collapse ul li').on('click', function() {
            	if (currentFilters === 0) {
                var subCategoryName = $(this).text(); // 클릭한 카테고리명 가져오기
                // 새로운 필터 태그 생성
                existingFilter = $('<div>').addClass('plusFiterbox')
                                          .append($('<div>').addClass('plusFiterboxText').text(subCategoryName))
                                          .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
										  .attr('subCategoryName', subCategoryName);
                $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
			    currentFilters++; // 현재 필터 개수 증가
            } else if (currentFilters === 1) {
            	//필터의 text값 업데이트시켜주기
            	var subCategoryName = $(this).text(); // 클릭한 카테고리명 가져오기
            	existingFilter.attr('subCategoryName', subCategoryName);
            	existingFilter.find('.plusFiterboxText').text(subCategoryName);
            }
        }); */
    		 //삭제
    		/* function removeKeyFromProduct(conditions) {
        		$(document).on("click", ".plusFiterboxbtn button", function() {
    			$(this).closest(".plusFiterbox").remove();
                currentFilters--;// 현재 필터 개수 감소
                existingFilter = null; // 기존 필터 객체 초기화
    	 	}); */
        		/* delete conditions['categoryname'];
        		getselectproduct(conditions);
        		console.log(conditions); */
             /*  }  */ 
 -->

     

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
<!-- /*          function removeKeyFromProduct(conditions) {
        	  $(document).on("click", ".plusFiterboxbtn button", function(event) {
        	    event.stopPropagation(); // 이벤트 버블링 방지

        	    $(this).closest(".plusFiterbox").remove();
        	    currentFilters--; // 현재 필터 개수 감소
        	    existingFilters.splice($(this).closest(".plusFiterbox").index(), 1); // 배열에서 삭제된 필터 객체 제거
        	  });
        	}

        	var currentFilters = 0; // 현재 필터 개수
        	var existingFilters = []; // 기존 필터 객체들을 저장할 배열

        	function createFilterTag(text) {
        	  var filterTag = $('<div>')
        	    .addClass('plusFiterbox')
        	    .append($('<div>').addClass('plusFiterboxText').text(text))
        	    .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'));

        	  return filterTag;
        	}

        	function pdcCategoryClickHandler() {
        	  var categoryName = $(this).text(); // 클릭한 카테고리명 가져오기

        	  var newFilter = createFilterTag(categoryName);
        	  $('.plusFilter').append(newFilter); // 필터 컨테이너에 새로운 필터 추가
        	  existingFilters.push(newFilter); // 새로운 필터 객체를 배열에 추가
        	  currentFilters = existingFilters.length; // 현재 필터 개수 갱신
        	}

        	$('.pdcCategory span').on('click', pdcCategoryClickHandler);


        	function collapseItemClickHandler() {
        	  var subCategoryName = $(this).text(); // 클릭한 카테고리명 가져오기

        	  var newFilter = createFilterTag(subCategoryName);
        	  $('.plusFilter').append(newFilter); // 필터 컨테이너에 새로운 필터 추가
        	  existingFilters.push(newFilter); // 새로운 필터 객체를 배열에 추가
        	  currentFilters = existingFilters.length; // 현재 필터 개수 갱신
        	}

        	$('.collapse ul li').on('click', collapseItemClickHandler);

        	function statusBtnClickHandler() {
        	  var statusTag = $(this).text(); // 클릭한 카테고리명 가져오기

        	  var newFilter = createFilterTag(statusTag);
        	  $('.plusFilter').append(newFilter); // 필터 컨테이너에 새로운 필터 추가
        	  existingFilters.push(newFilter); // 새로운 필터 객체를 배열에 추가
        	  currentFilters = existingFilters.length; // 현재 필터 개수 갱신
        	}

        	$('.statusbtn span').on('click', statusBtnClickHandler);


        	function radioLabelClickHandler() {
        	  var price = $(this).text(); // 클릭한 카테고리명 가져오기

        	  var newFilter = createFilterTag(price);
        	  $('.plusFilter').append(newFilter); // 필터 컨테이너에 새로운 필터 추가
        	  existingFilters.push(newFilter); // 새로운 필터 객체를 배열에 추가
        	  currentFilters = existingFilters.length; // 현재 필터 개수 갱신
        	}

        	$('.radio-label').on('click', radioLabelClickHandler);

        	// 지역옵션태그에 클릭시 필터에 적용
        	function gugun1ChangeHandler() {
        	  var selectedOption = $(this).find('option:selected');
        	  var area = $('#sido1').val() + selectedOption.val(); // 클릭한 지역명

        	  var newFilter = createFilterTag(area);
        	  $('.plusFilter').append(newFilter); // 필터 컨테이너에 새로운 필터 추가
        	  existingFilters.push(newFilter); // 새로운 필터 객체를 배열에 추가
        	  currentFilters = existingFilters.length; // 현재 필터 개수 갱신
        	}

        	$('.gugun1').on('change', gugun1ChangeHandler);

        	removeKeyFromProduct(); // 삭제 기능 활성화  */
        
        	
        	
        	
        	
     
        
        
        
        
		
 			/* // 상품상태클릭시 필터에 적용
   		 	   $('.statusbtn span').on('click', function() {
   		 			var currentFilters = 0; // 현재 필터 개수
   					var existingFilter = null; // 기존 필터 객체 */
				   /*  if (currentFilters === 0) {
				    // 새로운 필터 태그 생성
				      var statusTag = $(this).text(); // 클릭한 카테고리명 가져오기
				      existingFilter = $('<div>').addClass('plusFiterbox')
				        .append($('<div>').addClass('plusFiterboxText').text(statusTag))
				        .append($('<div>').addClass('plusFiterboxbtn').html('<button><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32" d="M320 320L192 192M192 320l128-128"/></svg></button>'))
				        .attr('status', statusTag); // 태그의 속성값으로 키값 설정

				      $('.plusFilter').append(existingFilter); // 필터 컨테이너에 새로운 필터 추가
				      currentFilters++; // 현재 필터 개수 증가
				    } else if (currentFilters === 1) {
				      //필터의 text값 업데이트시켜주기
				      var statusTag = $(this).text(); // 클릭한 카테고리명 가져오기
				      existingFilter.attr('status', statusTag); // 필터의 태그 속성값 변경
				      existingFilter.find('.plusFiterboxText').text(statusTag); // 필터의 텍스트 값 업데이트
			    }
   		 }); */  -->
   		<%-- function handleRecentlyClick() {
				if(conditions['categoryname'] == null && conditions['subcategoryname'] == null && conditions['status'] == null && conditions['price'] == null && conditions['area'] == null){
	                console.log(conditions);
					$.ajax({
	                    url: "<%=request.getContextPath()%>/resentlyproductlist",
	                    dataType: 'html',
	                    success: function(data) {
	                        $("#productContainer").html(data);
	                    }
	                });
	            	}else if(conditions['categoryname'] !== null || conditions['subcategoryname'] !== null || conditions['status'] !== null || conditions['price'] !== null || conditions['area'] !== null){
	            		console.log(conditions);
	            		$.ajax({
		                    url: "<%=request.getContextPath()%>/getproduct.do",
		                    dataType: 'html',
		                    data: conditions,
		                    success: function(data) {
		                        $("#productContainer").html(data);
		                    }
		                });
	            	}
			}
			function handleDescClick() {
         	if(conditions['categoryname'] == null && conditions['subcategoryname'] == null && conditions['status'] == null && conditions['price'] == null && conditions['area'] == null){
         		console.log(conditions);
         	$.ajax({
                 url: "<%=request.getContextPath()%>/entiremaxprice",
                 dataType: 'html',
                 success: function(data) {
                     $("#productContainer").html(data);
                 }
             });
         	}else if(conditions['categoryname'] !== null || conditions['subcategoryname'] !== null || conditions['status'] !== null || conditions['price'] !== null || conditions['area'] !== null){
         		console.log(conditions);
         		$.ajax({
	                    url: "<%=request.getContextPath()%>/maxprice",
	                    dataType: 'html',
	                    data: conditions,
	                    success: function(data) {
	                        $("#productContainer").html(data);
	                    }
	                });
         	}
         }
         function handleAscClick() {
         	if(conditions['categoryname'] == null && conditions['subcategoryname'] == null && conditions['status'] == null && conditions['price'] == null && conditions['area'] == null){
	                $.ajax({
	                    url: "<%=request.getContextPath()%>/entireminprice",
	                    dataType: 'html',
	                    success: function(data) {
	                        $("#productContainer").html(data);
	                    }
	                });
	            	}else if(conditions['categoryname'] !== null || conditions['subcategoryname'] !== null || conditions['status'] !== null || conditions['price'] !== null || conditions['area'] !== null){
	            		$.ajax({
		                    url: "<%=request.getContextPath()%>/minprice",
		                    dataType: 'html',
		                    data: conditions,
		                    success: function(data) {
		                        $("#productContainer").html(data);
		                    }
		                });
	            	}
         }		 --%>
