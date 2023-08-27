<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.semi.product.model.vo.Product"%>

<%
List<Product> products = (List)request.getAttribute("productList");
%>

<%@ include file="/views/admin/manageMemberHome.jsp"%>


<section class="info">			
	<div class="userTable">
	

		<table class="table text-center">
			<thead>
				<tr>
					<th colspan="1">
					<th>상품번호</th>
					<th>상품주인</th>
					<th>제목</th>
					<th>상품상태</th>
					<th>가격</th>
					<th>등록날짜</th>
					<th>키워드</th>
					<th>카테고리</th>
					<th>지역</th>
					<th>확인</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (products.isEmpty()) {
				%>
				<tr>
					<td colspan="8">조회된 신고글이 없습니다.</td>
					<%
					} else {
					for (Product p : products) {
						
					%>
				
				<tr>
					<td>
						<input type="checkbox" id="memberChoice" name="deleteCheck" value="<%=p.getProductId()%>"/>
					</td>
					<td><%=p.getProductId()%></td>
					<td><%=p.getUserId()%></td>
					<td><%=p.getTitle()%></td>
					<td><%=p.getProductStatus()%></td>
					<td><%=p.getPrice()%></td>
					<td><%=p.getRegistTime()%></td>
					<td><%=p.getKeyword()%></td>
					<td><%=p.getSubCategoryName()%></td>
					<td><%=p.getAreaName()%></td>

					<td>
					<button type="button" class="conBtn btn btn-dark"
							onclick="location.replace('<%=request.getContextPath()%>/productpage?no=<%=p.getProductId()%>');">확인</button></td>	
						</td>
					<td>
					<button type="button" class="deleteBtn btn btn-danger btn-sm"
							onclick="location.replace('<%=request.getContextPath()%>/productRemove.do?no=<%=p.getProductId()%>');">삭제</button>
					</td>	
				</tr>
				<%} %>
				<%
				}
				%>
			</tbody>
		</table>
		<div id="pageBar" class="text-center">
			<%=request.getAttribute("pageBar")%>
		</div>
		
		<button id="userRemoveCheck" type="button" class="btn btn-danger">선택삭제</button>
	</div>
</section>

	<script>
	
	
		
	$(document).ready(function() { 
		$("#userRemoveCheck").hide();
	$("input:checkbox").on('click', function() {
		let arr=[];
		const cnt = document.querySelectorAll('input[name="deleteCheck"]:checked').length;
		
		$('input:checkbox[name=deleteCheck]').each(function (index) {
			if($(this).is(":checked")==true){
		    	arr.push($(this).val());
		    	console.log($(this).val());
		    }
		})
		
		
		if(cnt>=1){  // 체크된 체크박스 개수가 한개이상일때는 선택삭제 버튼만 보이게함
			$("#userRemoveCheck").show();
			$("#userRemoveAll").hide();
			$(".updateBtn").hide();
			$(".deleteBtn").hide();
			$(".conBtn").hide();
			$(".processBtn").hide();
		}
		else{
			$("#userRemoveCheck").hide();
			$("#userRemoveAll").show();
			$(".updateBtn").show();
			$(".deleteBtn").show();
			$(".conBtn").show();
			$(".processBtn").show();
		}
	
	    });
	});
	
	
	$("#userRemoveCheck").click(e=>{
		let arr = [];
		$("input[name=deleteCheck]:checked").each((index,e)=>{
			/* console.log(e.value); */
			arr.push(e.value);
		});
		console.log(arr);
		
		
		$.ajax({
			url: "deleteCheckProduct",
			data: {"arr": arr},  
			success: function(result) {
				if(result>=1){
					alert("성공적으로 삭제되었습니다.");
					location.replace("/semi-hifive/productAdmin.do");
				}else{
					alert("오류로 인해 삭제가 실패했습니다");
					location.replace("/semi-hifive/productAdmin.do");
				}
			},
			error: function() {
				console.log("선택삭제실패");
			}
		})
		
	})
	</script>