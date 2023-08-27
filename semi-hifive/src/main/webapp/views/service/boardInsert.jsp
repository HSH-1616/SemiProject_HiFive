<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%
	String writer="";
	if(loginMember!=null){
		writer=loginMember.getUserId();
	}
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/boardInsert.css">
<section>
<%@ include file="/views/service/serviceCategory.jsp" %>
	<form action="<%=request.getContextPath()%>/service/boardInsertEnd.do?login=admin"
		onsubmit="return titleCheck();" method="post" enctype="multipart/form-data">
	<div class="ServiceCenter">
		<h2 class="ServietHead">게시글 작성</h2>
		<div class="writeTitle">
			<div class="titleGroup">
				<select class="titleSelect" name="titleCategory">
					<option value="Y" selected>공지사항</option>
					<option value="N">자주하는 질문</option>
				</select>
				<select class="QASelect" name="QACategory">
					<option value="" selected disabled>필수 선택</option>
					<option value="회원정보">회원정보</option>
					<option value="판매">판매</option>
					<option value="구매">구매</option>
					<option value="기타">기타</option>
				</select>
				<br>
				<label for="">제목</label>
				<input type="text" name="boardTitle"
					id="baordTitle" placeholder="제목을 입력하세요.">
				<span id="titleTextNum">0/40</span>
				<div id="titleCheck"></div>
			</div>
		</div>
		<div class="writeBody">
			<textarea class="writeContent" name="boardContent" id="boardContent" rows="20" cols="150"
				placeholder="작성할 내용을 입력하세요."></textarea>
		</div>

		<div class="upfile">
			<ion-icon class="uploadIcon" name="images-outline"></ion-icon>
			<input class="form-control" type="file" name="boardFile" id="formFileMultiple" multiple>
			<span id="contentTextNum">0/2000</span>
		</div>
		<div id="fileList"></div>
		<div class="serviceInsertBtn">
			<input type="reset"  class="serviceCancelBtn" value="취소">
			<input type="button"  onclick="uploadFile();" class="serviceSubmitBtn" value="완료">
		</div>
	</div>
	</form>
</section>
<script>
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
    console.log(fileInput);
    if (fileInput[0].files.length > 0) { //file이 있는 경우
    	for (let i = 0; i < fileInput[0].files.length; i++){
    		formData.append('file'+i, fileInput[0].files[i]);
    	}
    }
    formData.append("login",'<%=loginMember.getUserId()%>');
    formData.append("titleCategory",$(".titleSelect :selected").val());
    formData.append("QACategory",$(".QASelect :selected").val());
    formData.append("boardTitle",$("#baordTitle").val());
    formData.append("boardContent",$("#boardContent").val());
    
    $.ajax({
        url : "<%=request.getContextPath()%>/service/boardInsertEnd.do",
        type : "post",
        data : formData,
        contentType : false,
        processData : false,
        success : function(result) {
			if (result) {
				alert("업로드 성공했습니다.");
				location.replace("<%=request.getContextPath()%>/service/boardList.do?notice=Y");
			}
		},
		error : function() {
			alert("업로드에 실패했습니다. 작성 내용을 확인하세요.");
		}
    });
}

</script>
<script src="<%=request.getContextPath()%>/js/service/boardInsert.js"></script>
<%@ include file="/views/common/footer.jsp" %>
