<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.semi.product.model.vo.Product" %>
<%@ include file="/views/common/header.jsp"%>
<%
	Product p=(Product)request.getAttribute("product");
%>
<section>
<%@ include file="/views/service/serviceCategory.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/buylistInsert.css">
<form action="<%=request.getContextPath()%>/service/reportProductInsertEnd.do"
		onsubmit="return titleCheck();" method="post" enctype="multipart/form-data">
	<div class="ServiceCenter">
		<h2 class="reportHead">판매글 신고하기</h2>
		<div class="writeTitle">
			<label for="">제목</label> <input type="text" id="reportTitle"
				placeholder="제목을 입력하세요."> <span id="titleTextNum">0/40</span>
		</div>
		<div class="productContainer">
			<div>
				<h4 style="padding-left: 10px;">신고할 판매글</h4>
				<p>* 신고할 게시글이 맞는지 확인하세요.</p>
			</div>
			<div class="reportList">
				<table>
					<tr>
						<th>거래자</th>
						<th>거래 게시물</th>
						<th>거래 금액</th>
						<th>거래 날짜</th>
					</tr>
				<%if(p!=null){%>
					<tr>
						<td><%=p.getUserId() %></td>
						<td class="productTitle"><%=p.getTitle() %></td>
						<td><%=p.getPrice() %>원</td>
						<td><%=p.getRegistTime() %></td>
					</tr>
					<%}else{%>
					<tr>
					<td colspan="4"><h4>신고할 거래 내역이 없습니다.</h4></td>
					</tr>
					<%}%>
				</table>
			</div><!-- reportList -->
		</div><!-- buyliseContainer -->

		<div class="writeBody">
			<textarea class="write" name="reportContent" rows="20" cols="150"
				id="reportContent" placeholder="문의할 내용을 입력하세요."></textarea>
		</div>

		<div class="upfile">
			<ion-icon class="uploadIcon" name="images-outline"></ion-icon>
			<input class="form-control" type="file" id="formFileMultiple" multiple>
			<span id="contentTextNum">0/1000</span>
		</div>
		<div id="fileList"></div>
		<div class="serviceInsertBtn">
			<button class="serviceCancelBtn">취소</button>
			<input type="button" onclick="uploadFile();" class="serviceSubmitBtn" value="완료">
		</div>
	</div>
</section>
<script>
//파일크기 체크
$(document).on("change","#formFileMultiple",function(){
	let maxSize = 1024 * 1024 * 200;  // 파일 최대 크기 200MB
	const files = this.files; // 첨부된 파일 목록
	$('#fileList').empty();
	
	$.each(files, function(i, file){ // 첨부된 파일 순회
		if(file.size > maxSize){ // 크기 체크
			alert('10MB 이하의 파일만 첨부할 수 있습니다.');
			$(this).val('');  // 첨부된 파일을 모두 없애줌
			return;
		}
		// 첨부 목록 표시
		$('#fileList').append('<div>' + file.name + '</div>');
	});
});

function uploadFile(){
    const formData=new FormData();
    const fileInput=$("#formFileMultiple");
    if (fileInput[0].files.length > 0) { //file이 있는 경우
    	for (let i = 0; i < fileInput[0].files.length; i++){
    		formData.append('file'+i, fileInput[0].files[i]);
    	}
    }
    formData.append("writer","<%=loginMember.getUserId()%>");
    formData.append("title",$("#reportTitle").val());
    formData.append("content",$("#reportContent").val());
    formData.append("productId","<%=p.getProductId()%>");
    
    
    $.ajax({
        url : "<%=request.getContextPath()%>/service/reportProductInsertEnd.do",
        type : "post",
        data : formData,
        contentType : false,
        processData : false,
        success : function(result) {
			if (result) {
				alert("업로드 성공했습니다.");
				location.replace("<%=request.getContextPath()%>/service/reportList.do");
			}else{
				alert("신고할 글을 다시 작성하세요. 업로드에 실패했습니다.");
				location.replace("<%=request.getContextPath()%>/");
			}
		},
		error : function() {
			alert("업로드에 실패했습니다. 작성 내용을 확인하세요.");
		}
    });
}
</script>
<%@ include file="/views/common/footer.jsp"%>