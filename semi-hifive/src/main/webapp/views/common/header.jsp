<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="com.semi.category.model.vo.Category"%>
<%@page import="java.util.List"%>
<%@ page import="com.semi.member.model.vo.Member"%>
<%
Member loginMember = (Member) session.getAttribute("loginMember");//여기 로그인멤버 
Cookie[] cookies = request.getCookies(); // 존재하는 쿠키들 다 갖고옴 
String saveId = null;
if (cookies != null) {
   for (Cookie c : cookies) {
      if (c.getName().equals("saveId")) {
   saveId = c.getValue();
   break;
      }
   }
}
%>
<%
        String recentList="";
		if (cookies != null) {
            for (Cookie pc : cookies) {
                if (pc.getName().equals("recentList")) {
                	recentList=URLDecoder.decode(pc.getValue(),"UTF-8");
                	//생성된 쿠키 디코드 해서 문자열로 반환
                	break;
                }
            }
        }
%>
<!DOCTYPE html>
<html>
<head>
<script>
   //로그인한 아이디 sessionStorage에 저장하자..ㅋㅋㅋ
   sessionStorage.setItem("loginId",'<%=loginMember!=null?loginMember.getUserId():""%>');
</script>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- css 파일 -->
<link rel="stylesheet"
   href="<%=request.getContextPath()%>/css/default.css" />
<link rel="icon"
   href="<%=request.getContextPath()%>/images/common/fivicon.png"
   type="image/x-icon" />
<!-- js 파일 -->
<script type="module"
   src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
   src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
<!--  -->
<title>중고 거래 HiFive</title>
</head>
<body>

	<header>
		<div id="headerContainer">
			<div id="fixedContainer">
				<%
				if (loginMember == null) {
				%>
				<div class="loginSerivce">
					<a href="<%=request.getContextPath()%>/loginView.do" id="login">로그인</a>
					<a href="<%=request.getContextPath()%>/enrollMember.do" id="enroll">회원가입</a>
					<a
						href="<%=request.getContextPath()%>/service/boardList.do?notice=Y"
						id="service">고객센터</a>
				</div>
				<%
				} else {
				%>
				<div class="loginSerivce">
				<%if(loginMember.getAuth().equals("M")){%>
					<a href="<%=request.getContextPath()%>/adminMode.do">관리자모드</a>
					<%} %>
					<a href="#"
						onclick="location.replace('<%=request.getContextPath()%>/logout.do')"
						id="logout">로그아웃</a> <a
						href="<%=request.getContextPath()%>/service/boardList.do?notice=Y"
						id="service">고객센터</a>
				</div>
				<%
				}
				%>
            <div class="headerMain">
               <div class="logo">
                  <a href="<%=request.getContextPath()%>/index.jsp"> <img
                     src="<%=request.getContextPath()%>/images/common/hifiveLogo.png"
                     alt="" />
                  </a>
               </div>
               <div class="searchBar">
                  <div class="searchDetail">
                     <form action="<%=request.getContextPath()%>/search" method="post" id="searchForm" class="form" autocomplete="off" >
                        <input required id="searchInput" maxlength="10" type="text" 
                           placeholder="상품명, #키워드 검색" onfocus="this.placeholder = ''"
                        onblur="this.placeholder = '상품명, #키워드 검색'">
                        <button type="reset" id="resetBtn">
                     <ion-icon name="close"></ion-icon>
                     </button>              
                     </form>                    
                    <button type="submit" form="searchForm" id="submitBtn">
                        <img
                           src="<%=request.getContextPath()%>/images/common/magnifier.png"
                           alt="" />
                     </button>
                  </div>
                  <div class="searchpage">

                     <div class="searchbody">
                        <hr>
                        <div class="recentSearch">
                           <div class="allDelete off">
                              <span id="recentHead">최근 검색어</span> <span id="allDeleteBtn">모두
                                 지우기</span>
                           </div>
                           <p class="recentText"></p>
                           <ul id="recentList">

                           </ul>
                        </div>
                        <div class="autoSearch">
                        	
                        </div>
                     </div>               
                  </div>
               </div>
               <div class="memberIcon">
                  <a href="<%=request.getContextPath()%>/productRegist.do"> <ion-icon
                        name="storefront-outline" class="storeIcon"></ion-icon> <span>
                        판매하기</span>
                  </a>
                  <%
                  if (loginMember != null) {
                  %>
                  <a
                     href="<%=request.getContextPath()%>/myPage/myPageMain.do?userId=<%=loginMember.getUserId()%>">
                     <ion-icon name="person-outline" class="myIcon"></ion-icon> 내정보
                  </a> <a href="<%=request.getContextPath()%>/myPage/wishList.do?userId=<%=loginMember.getUserId()%>"> <ion-icon name="heart-outline" class="heartIcon"></ion-icon>
                     찜한상품
                  </a>
                  <%
                  } else {
                  %>
                  <a href="<%=request.getContextPath()%>/productRegist.do"> <ion-icon
                        name="person-outline" class="myIcon"></ion-icon> 내정보
                  </a> <a href="<%=request.getContextPath()%>/productRegist.do"> <ion-icon
                        name="heart-outline" class="heartIcon"></ion-icon> 찜한상품
                  </a>
                  <%
                  }
                  %>
               </div>
            </div>
            <div class="categoryNrank">
                <input type="checkbox" id="menuIcon" /> <label for="menuIcon"
                  class="headercategorybtn"> <span></span> <span></span> <span></span>
               </label>
               <div id="menuList">
                  <ul>
                   </ul>
               </div> 
               
               
               <p>카테고리</p>
               
               <div id="printSearch">
              <div id="rankSearch">
              </div>
         <button><ion-icon name="chevron-down-outline"></ion-icon></button>
            </div>
             
            </div>
               
         </div>
         <hr width="1280px" color="#eeeeee" noshade />
             <div id="rankAllSearch">
            <div>
               <p>인기검색어 순</p><button><ion-icon name="chevron-up-outline"></ion-icon></button>
               </div>
              </div>
      </div>

      <div id="itemBox">
         <div id="recentProduct">
            <p>최근본상품</p>
            <div class="rpCount">0</div>
            <div id="recently" >
            
            </div>
         </div>
      </div>
   </header>
   <script>
<%--        <a href="<%=request.getContextPath()%>/productpage?no=<%=productId%>"> --%>
<%--        <img src="<%=request.getContextPath()%>/upload/productRegist/<%=URLDecoder.decode(productFileName,"UTF-8")%>"> --%>
//        </a>
<%--        <div><%=URLDecoder.decode(productTitle,"UTF-8")%></div> --%>
//        <div>최근본상품이 없습니다.</div>
$(()=>{
	if('<%=recentList%>'==""){ 
		return; //recentList에 저장된 문자열이 없으면 리턴(쿠기 생성 전)
	}
  	const recentList='<%=recentList%>';
  	const map=JSON.parse(recentList);
  	/* console.log(map);
  	console.log(recentList);
  	console.log(typeof recentList); */
	$("#recently").html("");
	if(map.length>0){
		$(".rpCount").text(map.length);
		//console.log("if문 실행");
		map.forEach(e=>{ //객체로 forEach 실행
			//console.log("for문 실행");
			const $recentA=$("<a>").attr("class", "recentlyLink").attr("href","<%=request.getContextPath()%>/productpage?no="+e.productId);
			const $recentImg=$("<img>").attr("class", "imgrecently").attr("src","<%=request.getContextPath()%>/upload/productRegist/"+e.productFileName);
			/* const $jbrecentDiv = $("<div>").attr("class", "jbrecentDiv"); */
			/* const $jbrecentDivText=$("<div>").attr("class", "jbrecentDivText"); */
			const $recentDiv=$("<div>").attr("class", "resentlyTag").text(e.productTitle);
			$("#recently").append($recentA).append($recentImg).append($recentDiv)/* .append($jbrecentDivText); */
			/* $jbrecentDivText.append($recentDiv); */
		});
	}else{
		$("#recently").append($("<div>최근본상품이 없습니다.</div>"));
	}
});

<%--     $(()=>{
    	const recentList=<%=recentList%>;
	   	console.log(recentList);
	   	$("#recently").html("");
	   	if(recentList.length>0){
	   		$(".rpCount").text(recentList.length);
	   		// 최대 개수만큼 순회하면서 태그를 생성
	        for (let i = 0; i < 3; i++) {
	            const e = recentList[i];
	            const $recentA = $("<a>").attr("href", "<%=request.getContextPath()%>/productpage?no=" + e.productId);
	            const $recentImg = $("<img>").attr("src", "<%=request.getContextPath()%>/upload/productRegist/" + e.productFileName);
	            const $recentDiv = $("<div>").text(e.productTitle);
	            $("#recently").append($recentA).append($recentImg).append($recentDiv);
	        }
	     	// 최대 개수보다 많은 경우, 가장 오래된 태그를 삭제하고 새로운 태그를 추가
	        for (let i = 3; i < recentList.length; i++) {
	        	$("#recently").children().first().remove();
	            
	            const e = recentList[i];
	            const $recentA = $("<a>").attr("href", "<%=request.getContextPath()%>/productpage?no=" + e.productId);
	            const $recentImg = $("<img>").attr("src", "<%=request.getContextPath()%>/upload/productRegist/" + e.productFileName);
	            const $recentDiv = $("<div>").text(e.productTitle);
	            $("#recently").append($recentA).append($recentImg).append($recentDiv);
	        }
	   	}else{
	   		$("#recently").append($("<div>최근본상품이 없습니다.</div>"));
	   	}
	   	
}); --%>
//전체 선택페이지 서블릿
<%-- function ProductList_btn() {
   $.ajax({
      url: "<%=request.getContextPath()%>/categoryproductlist.do",
      dateType: 'html',
      success: function(data){
         $("section").html(data);
      }
   })
} --%>
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
}
<%-- //헤더에서 카테고리 클릭시 클릭한 카테고리 상품리스트 출력 ajax
function searchCategory(name) {
   $.ajax({
      url: "<%=request.getContextPath()%>/headersearchcategory.do",
         dateType: 'html',
         data:{'name': name},
         success: function(data){
            $("section").html(data); 
          }
   });
}
 //헤더에서 서브카테고리 클릭시 클릭한 서브카테고리 상품리스트 출력 ajax
function searchsubcategory(subname) {
   $.ajax({
      url: "<%=request.getContextPath()%>/searchheadersubcategory.do",
         dateType: 'html',
         data:{'subname': subname},
         success: function(data){
            $("section").html(data); 
          }
   });
}  --%>

// 페이지 로딩되었을때 로딩후 마지막에 실행되는 함수
$(()=>{HeaderCategoryMenu()});
// 해더 카테고리에 DB데이터값을 Map으로 가져와서 뿌려주는 ajax 
function HeaderCategoryMenu() {
    $.ajax({
        url: "<%=request.getContextPath()%>/headercategories.do",
        dataType: 'json',
        success: function(data) {
           $("#menuList>ul").html("<li><a href='<%=request.getContextPath()%>/getproduct.do' id='category0'>전체</a></li>");
           $("#categoryName span").text("전체"); 
           data.main.forEach(function(category,index) {
                makeCategoryHeader(category.categoryName, index);
                const subCategory=data.sub.filter(cate=>cate.category.categoryName==category.categoryName);
                //console.log(subCategory);
                makeCatetorySub(subCategory, index);
            });
        }
    });
}
/* function makeCategoryHeader(name, index) {
    const $li = $("<li>");
    const $a = $("<a>").attr("id", "category" + (index + 1)).data('categoryname', "CATEGORY_NAME = '" + name + "'").text(name);

    $a.click(function() {
        const conditions = {};
        conditions['categoryname'] = $(this).data('categoryname');
        console.log(conditions);
        getselectproduct(conditions);
    });

    $li.append($a);
    $("#menuList>ul").append($li);
} */

<%-- function getselectproduct(conditions) {
    console.log(conditions);
    $.ajax({
        url: "<%=request.getContextPath()%>/test",
        dataType: 'html',
        data: conditions,
        success: function(data) {
            $("section").html(data);
        }
    });
} --%>
/* function makeCategoryHeader(name, index) {
   
    const $li = $("<li>");
    const $a = $("<a>").attr("id", "category" + (index + 1)).text(name).attr("onclick", "searchCategory('" + name + "');");
    $li.append($a);
    $("#menuList>ul").append($li);
} */
function makeCategoryHeader(name, index) {
	var categoryname = "CATEGORY_NAME = '" + name + "'";
    const $li = $("<li>");
    const $a = $("<a>").attr("href", "<%=request.getContextPath()%>/getproduct.do?categoryname="+categoryname).attr("id", "category" + (index + 1)).text(name);
    <%-- const $a = $("<a>").attr("href", "<%=request.getContextPath()%>/headersearchcategory.do?categoryname="+name).attr("id", "category" + (index + 1)).text(name); --%> 
    $li.append($a);
	$("#menuList>ul").append($li);
}
function makeCatetorySub(subcateList, index) {
	console.log(subcateList);
    const $div=$("<div>").attr({"id":"sideMenu-category"+(index+1),"class":"sideMenu"});
    const $ul=$("<ul>");
    subcateList.forEach(sub=>{
    	var subcategoryname = "SUBCATEGORY_NAME = '" + sub.subCategory.subcategoryName +"'";
    	const $a = $("<a>").attr("href", "<%=request.getContextPath()%>/getproduct.do?subcategoryname="+subcategoryname).text(sub.subCategory.subcategoryName);
<%--            const $a = $("<a>").attr("href", "<%=request.getContextPath()%>/searchheadersubcategory.do?subcategroyname="+sub.subCategory.subcategoryName).text(sub.subCategory.subcategoryName);
 --%>           const $li = $("<li>").append($a);
           $ul.append($li);
    });
$div.html($ul);
$("div#menuList").after($div);

}

     /* function makeCatetorySub(subcateList, index) {
       const $div=$("<div>").attr({"id":"sideMenu-category"+(index+1),"class":"sideMenu"});
       const $ul=$("<ul>");
       subcateList.forEach(sub=>{
              const $a = $("<a href='javascript:void(0);'>").text(sub.subCategory.subcategoryName).attr("onclick", "searchsubcategory('" + sub.subCategory.subcategoryName + "');");
              const $li = $("<li>").append($a);
              $ul.append($li);
       });
       $div.html($ul);
       $("div#menuList").after($div); */

     
     <%-- const subcategoryName = sub.subCategory.subcategoryName;
      const encodedSubcategoryName = encodeURIComponent(subcategoryName);
      const href = '<%= request.getContextPath() %>/headersearchcategory.do?subcategoryname=' + encodedSubcategoryName;
      const $a = $("<a>").attr("href", href).text(subcategoryName); --%>
</script>
   <script src="<%=request.getContextPath()%>/js/common/header.js"></script>
  <%--  <script src="<%=request.getContextPath()%>/js/searchpage/searchPage.js"></script> --%>