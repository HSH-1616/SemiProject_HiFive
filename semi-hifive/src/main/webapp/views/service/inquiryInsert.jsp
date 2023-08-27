<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<section>
<%@ include file="/views/service/serviceCategory.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/inquiryInsert.css">
<form action="<%=request.getContextPath()%>/service/inquiryInsertEnd.do"
		onsubmit="return titleCheck();" method="post" enctype="multipart/form-data">
<div class="ServiceCenter">
      <h2 class="ServietHead">1:1 문의하기</h2>
      <div class="writeTitle">
          <label for="">제목</label>
          <input type="text" id="contentTitle" name="contentTitle" placeholder="제목을 입력하세요.">
          <span id="titleTextNum">0/40</span>
      </div>
      <div class="writeBody">
        <textarea class="write" id="inquiryContent" rows="20" cols="150"
          placeholder="문의할 내용을 입력하세요."></textarea>
      </div>

      <div class="upfile">
        <ion-icon class="uploadIcon" name="images-outline"></ion-icon>
        <input class="form-control" type="file" id="formFileMultiple" multiple>
        <span id="contentTextNum">0/1000</span>
      </div>
      <div id="fileList"></div>
      
      <div class="contentPrivate">
        <ion-icon name="lock-closed-outline"></ion-icon>
          <div class="slideContainer">
           	 	<label class="list-label">
                  <input type="checkbox" id="secretCheck" value="Y" checked>
                  <div class="secretCk"></div>
               	</label>
          </div>
        </div>
        <p>* 문의글을 비공개로 작성하면 작성자만 확인 가능합니다.</p>
      <div class="serviceInsertBtn">
        <input type="reset"  class="serviceCancelBtn" value="취소">
		<input type="button"  onclick="uploadFile();" class="serviceSubmitBtn" value="완료">
      </div>
</div>
</form>
</section>
<script type="text/javascript">
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
    formData.append("title",$("#contentTitle").val());
    formData.append("content",$("#inquiryContent").val());
    if($("#secretCheck:checked").length>0){
    	formData.append("secret","Y");
    }else{
    	formData.append("secret","N");
    }
    
    $.ajax({
        url : "<%=request.getContextPath()%>/service/inquiryInsertEnd.do",
        type : "post",
        data : formData,
        contentType : false,
        processData : false,
        success : function(result) {
			if (result) {
				alert("업로드 성공했습니다.");
				location.replace("<%=request.getContextPath()%>/service/inquiryList.do");
			}else{
				alert("업로드 실패했습니다.");
				location.replace("<%=request.getContextPath()%>/service/inquiryList.do");
			}
		},
		error : function() {
			alert("업로드에 실패했습니다. 작성 내용을 확인하세요.");
		}
    });
}
</script>
<script src="<%=request.getContextPath()%>/js/service/inquiryInsert.js"></script>
<%@ include file="/views/common/footer.jsp" %>