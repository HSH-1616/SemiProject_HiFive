<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.semi.mypage.model.vo.ProductList, java.text.DecimalFormat" %>
<%@ include file="/views/common/header.jsp"%>
<%@ include file="/views/mypage/myPageCategory.jsp" %>
<%
	List<ProductList> product = (List)request.getAttribute("sellProduct");
	int countAll = (int)request.getAttribute("countAll");
	int countStatusSell = (int)request.getAttribute("countStatusSell");
	int countStatusRes = (int)request.getAttribute("countStatusRes");
	int countStatusSol = (int)request.getAttribute("countStatusSol");
	String status = (String)request.getAttribute("nowButton");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage/sellList.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css" />

<!-- 오른쪽 판매내역 section -->
    <div class="mypageMain">
        <!-- 판매내역 헤더 div -->
        <div class="mypageMainHeader">
            <h3>판매 내역</h3>
        </div>

        <!-- 판매상태 아이콘 div -->
        <div class="buttonAll">
            <button id="allBtn" class="<%=status.equals("all")?"nowButton":""%>">전체 <%=countAll%></button>
            <button id="sellBtn" class="<%=status.equals("sell")?"nowButton":""%>">판매중 <%=countStatusSell%></button>
            <button id="resBtn" class="<%=status.equals("res")?"nowButton":""%>">예약중 <%=countStatusRes%></button>
            <button id="solBtn" class="<%=status.equals("sol")?"nowButton":""%>">판매완료 <%=countStatusSol%></button>
        </div>

        <!-- 판매내역 목록 div -->
        <div class="productAll">
            <input type="hidden" id="sellTarget" name="target">
            <input type="hidden" id="nowStatus" name="target1">
        <% if(!product.isEmpty()) {
                        	for(ProductList p : product){
        %>
            <div class="product">
                <a href="<%=request.getContextPath() %>/productpage?no=<%=p.getProduct().getProductId()%>"><img src="<%=request.getContextPath() %>/upload/productRegist/<%=p.getProductfile().getImageName() %>" alt=""></a>
                <div class="proContent">
                    <h5 class="grayFont">
                    <a href="<%=request.getContextPath() %>/headersearchcategory.do?categoryname=<%=p.getCategory().getCategoryName() %>" class="aTag grayFont categoryA"><%=p.getCategory().getCategoryName() %></a> &rsaquo;
                    <a href="<%=request.getContextPath() %>/searchheadersubcategory.do?subcategroyname=<%=p.getProduct().getSubCategoryName() %>" class="aTag grayFont"><%=p.getProduct().getSubCategoryName() %></a></h5>
                    <h4 class="contentMargin"><a href="<%=request.getContextPath() %>/productpage?no=<%=p.getProduct().getProductId()%>" class="aTag productTitle"><%=p.getProduct().getTitle() %></a></h4>
                    <h3><%=new DecimalFormat("###,###").format(p.getProduct().getPrice()) %>원</h3>
                </div>
                <div class="count">
                    <ion-icon name="heart" class="interestIcon"></ion-icon><span><%=new DecimalFormat("###,###").format(p.getWishCount()) %></span>
                    <ion-icon name="eye" class="viewIcon"></ion-icon><span><%=new DecimalFormat("###,###").format(p.getProduct().getViewCount()) %></span>
                </div>
                <div class="buttonNcategory">
                    <div class="updateDelete">
                        <button class="updateBtn">수정</button>
                        <button class="delMem openBtn" id="<%=p.getProduct().getProductId() %>">삭제</button>
                    </div>
                    <div class="selectNow">
                        <select id="<%=p.getProduct().getProductId() %>" class="<%=p.getProduct().getSellStatus().equals("판매중")?"selectIng":p.getProduct().getSellStatus().equals("예약중")?"selectRes":"selectSol"%> selectStatus"
                        	<%=p.getProduct().getSellStatus().equals("판매완료")?"disabled":""%>>
                            <option id="<%=p.getProduct().getProductId() %>" value="sell" data-color="#20C997" <%=p.getProduct().getSellStatus().equals("판매중")?"selected":""%>>판매중</option>
                            <option id="<%=p.getProduct().getProductId() %>" value="reservation" data-color="#FFD800" <%=p.getProduct().getSellStatus().equals("예약중")?"selected":""%>>예약중</option>
                            <option id="<%=p.getProduct().getProductId() %>" value="soldOut" data-color="#cccccc" <%=p.getProduct().getSellStatus().equals("판매완료")?"selected":""%>>판매완료</option>
                        </select>
                    </div>
                </div>
            </div>
            <hr color="#eeeeee" noshade style="margin-top: 23px; margin-bottom: 23px;" />
        <%}
        } else { %>
        	<span>판매중인 상품이 없습니다.</span>
        <%} %>
        </div>
        <!-- 페이지 버튼 -->
        <div class="pageBar">
            <%=request.getAttribute("pageBar") %>
        </div>
    </div>

<%for(ProductList p : product){
        %>
    <div class="modal hidden">
        <div class="bg"></div>
        <div class="modal_delMem">
            <div class="trashIcon">
                <ion-icon name="trash-outline" style="font-size: 32px; color: white;"></ion-icon>
            </div>
            <h3>정말 삭제하시겠습니까?</h3>
            <p>삭제 시, 상품은 삭제되며<br>
                복구되지 않습니다.</p>
            <div class="modal_button">
                <button id="<%=p.getProduct().getProductId() %>" class="modalBtn modalDelBtn">삭제</button>
                <button class="closeBtn modalBtn">취소</button>
            </div>
        </div>
    </div>
<%} %>

</section>
<script src="<%=request.getContextPath()%>/js/mypage/sellList.js"></script>
<%@ include file="/views/common/footer.jsp"%>