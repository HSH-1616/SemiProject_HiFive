<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Category> categorys = (List) request.getAttribute("categorys");
%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/product/productregist.css" />
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 다음 주소 api 사용 -->
<%@ include file="/views/common/header.jsp"%>


<section>

	<%
	if (loginMember != null) { // 로그인된상태에서 직접 login.view 화면으로 갔을때 로그인페이지가 아닌 메인화면으로 이동되는 예외처리
	%>

	<!-- <form class="container" method="post" enctype="multipart/form-data"> -->

		<div class="information">
			<div>
				<h3>기본정보</h3>
				<p>*필수항목</p>
			</div>
		</div>
		<hr>

		<div class="productImg">
			<div class="letters">
				<p>상품이미지</p>
				<p class="imgCount">(0/10)</p>
				<!-- document.all.file2.value=document.all.file1.value -->
			</div>

			<!-- input type file을 만들어서 눈에는 안보이게 숨기고 -->
			<!-- 이미지를 클릭했을 때 해당 input이 실행되도록 만듬 -->
			<img
				src="<%=request.getContextPath()%>/images/productregist/imgregist.png" 
				class="upload" width="250px" height="250px"> <input
				type="file" id="inputFile" class="real-upload" accept="image/*" required multiple
				style="display: none;">


			<ul class="image-preview">

			</ul>

			<div class="explane">
				<h4>* 상품 이미지는 640x640에 최적화 되어 있습니다.</h4>
				<p>- 상품 이미지는 PC에서는 1:1, 모바일에서는 1:1.23 비율로 보여집니다.</p>
				<p>- 이미지는 상품 등록 시 정사각형으로 잘려서 등록됩니다.</p>
				<p>- 이미지를 클릭할 경우 원본 이미지를 확인할 수 있습니다.</p>
				<p>- 이미지를 클릭 후 이동하여 등록순서를 변경할 수 있습니다.</p>
				<p>- 큰 이미지일 경우 이미지가 깨지는 경우가 발생할 수 있습니다.</p>
				<p>최대 지원 사이즈인 640 X 640으로 리사이즈 해서 올려주세요.(개당 이미지 최대 10M)</p>
			</div>
			<!-- <img src="./img/이미지등록.png" onclick='document.all.file1.click();' class="upload" width="250px" height="250px">   -->
			<!-- <input type=file name='file1' style='display: none;' accept="img/*">  accept="img/*" -> 이미지 파일만 등록가능 -->
		</div>

		<div class="title">
			<hr>
			<div class="productTitle">
				<h4 class="h4Size">제목 *</h4>
					<input type="text" placeholder="상품제목을 입력하세요" class="inputTitle" name="title">
				<p class="countTitle">0/20</p>	
			</div>
			<span id="spanTitle" ></span>
			<hr>
		</div>

		<div class="cate">
			<h4 class="h4Size">카테고리 *</h4>
<!-- 			<form name="frm1"> -->
				<select class="mainCate" onchange="chageSubCate(this.value);">
					<!-- this.value -> 선택된 option의 밸류값을 매개변수로 넣음 -->
					<%
					if (!categorys.isEmpty()) {
						for (int i = 0; i < categorys.size(); i++) {
					%>
					<option value="<%=categorys.get(i).getCategoryId()%>"><%=categorys.get(i).getCategoryName()%></option>
					<%
					}
		
					}
					%>

				</select> <select class="middleCate" name="subCate">

				</select>

		</div>
		<hr>

		<div class="productPlace">
			<h4 class="h4Size">거래지역 *</h4>
			<input type="button" id="sample6Id" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> 
			<input type="text" id="sample6_address" placeholder="주소" name="place" readonly> 
			<input type="hidden" id="sample6_postcode" placeholder="우편번호"> 
			<input type="hidden" id="sample6_detailAddress" placeholder="상세주소">
			<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
		</div>
		<span id="spanPlace" ></span>
		<hr>

		<div class="productStatus">
			<h4 class="h4Size">상태 *</h4>
			<fieldset>
				<label> <input type="radio" name="state" value="미개봉"
					checked /> <span>미개봉</span>
				</label> <label> <input type="radio" name="state" value="사용감 있음" />
					<span>사용감있음</span>
				</label>
			</fieldset>
		</div>
		<hr>

		<div class="price">
			<h4 class="h4Size">가격 *</h4>
			<!--             <input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                placeholder="숫자만 입력해주세요."> -->
			<input type="text" id="priceId" oninput="inputNumberFormat(this);" placeholder="숫자만 입력해주세요." name="price">
			<p>원</p>
		</div>
		<span id="spanPrice" ></span>
		<hr>

		<div class="explan">
			<h4 class="h4Size">설명 *</h4>
			<textarea id="explanId" name="explan" placeholder="여러 장의 상품 사진과 구입 연도, 브랜드, 사용감, 하자 유무 등 구매자에게 필요한 정보를 꼭 포함해 주세요. (10자 이상)"></textarea>
		</div>
		<span id="spanExplan" ></span>
		<h3 class="countExpaln">0/2000</h3>
		<hr>


		<div class="relativeTag">
			<h4 class="h4Size">
				상품태그
			</h4>
			<input type="text" id="searchTag" placeholder="연관 태그를 입력해주세요" autocomplete="on">
			<div id="relativeTagDiv">
			
			</div>
		</div>

		<div class="autocomplete"></div>


		<br>
		<div class="pp">
			<p>- 태그는 최대 5개까지 선택 가능합니다.</p>
			<p>- 선택된 태그는 x버튼을 클릭하면 삭제됩니다.</p>
			<p>- 태그는 검색의 부가정보로 사용 되지만, 검색 결과 노출을 보장하지는 않습니다.</p>
			<p>- 검색 광고는 태그정보를 기준으로 노출됩니다.</p>
			<p>- 상품과 직접 관련이 없는 다른 상품명, 브랜드, 스팸성 키워드 등을 입력하면 노출이 중단되거나 상품이 삭제될
				수 있습니다.</p>
		</div>
		<hr>


		<div class="fix">
			<button type="button" onclick="productRegist()">등록하기</button>  		
			<!-- form안의 버튼은 submit	이 가능함 -->
			<!-- button type="button" -> 자동으로 submit되는걸 방지하고 다른 동작을 실행시키고 싶을 때-->
		</div>



		<!-- 내부 js파일 호출 -->
		<script
			src="<%=request.getContextPath()%>/js/product/productregist.js"></script>

		<%
		} else {
		%>

		<script>
			location.href ="<%=request.getContextPath()%>/productRegist.do"
		</script>
		<!-- 여기부분이 직접 검색해서 들어갔을 때 들어가짐  -->
		<%
		}
		%>
<!-- 	</form> -->
</section>






<%@ include file="/views/common/footer.jsp"%>