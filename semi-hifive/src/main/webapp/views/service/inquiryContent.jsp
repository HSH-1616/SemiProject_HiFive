<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.semi.sc.model.dto.*" %>
<%@ include file="/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/boardContent.css">
<%
	Inquiry q=(Inquiry)request.getAttribute("inquiry");
	List<BoardComment> comments=(List)request.getAttribute("comments");
	List<ServiceFile> files=(List<ServiceFile>)request.getAttribute("files");
%>
<section>
<%@ include file="/views/service/serviceCategory.jsp" %>
	<div class="ServiceCenter">
		<%if(q!=null){ %>
            <div class="reportContent">
            <%if(q.getInquirySecret()=='Y'){ %>
                <ion-icon name="lock-closed-outline"></ion-icon> 비공개
                <%} %>
                <div class="contentTitle">
                    <h2><%=q.getInquiryTitle() %></h2>
                    <input type="hidden" id="inquiryNo" name="inquiryNo" value="<%=q.getInquiryNo()%>">
                    <button class="backBtn" onclick="history.back();">뒤로 가기</button>
                </div>
                <%if(loginMember!=null&&q.getInquiryWriter().equals(loginMember.getNickName())
                	||loginMember.getAuth().equals("M")){ %>
				<div>
				<button id="contentDelete"
				onclick="location.href='<%=request.getContextPath() %>/service/deleteInquiry.do?no=<%=q.getInquiryNo()%>'">
				삭제</button>
				</div>
				<%} %>
                <div class="content">
                    <div class="contentTop">
                        <p>작성자 : <%=q.getInquiryWriter() %></p>
                        <p><%=q.getInquiryDate() %></p>
                    </div>
                    <p><%=q.getInquiryContent() %></p>
                    <%if(files!=null){
						for(ServiceFile sf:files){%>
						<img src="<%=request.getContextPath() %>/upload/inquiry/<%=sf.getFilename() %>" width="800">
						<%}
                    }%>
                </div>
                <%if(loginMember.getAuth().equals("M")){ %>
                <div class="commentWrite">
                    <textarea name="comment" id="" cols="120" rows="5" placeholder="관리자만 달 수 있습니다."></textarea>
                    <button class="commentBtn">댓글 작성</button>
                    <input type="hidden" value="0" class="commentFK">
                </div>
                <%} %>
                
                <div class="comment">
			<%if(comments!=null){
				for(BoardComment bc:comments){
					if(bc.getCommentNoFK()==0){%>
					<div class="commentTop">
						<h4><%=bc.getCommentWriter() %></h4>
						<p><%=bc.getCommentDate() %></p>
					</div>
					<div class="commentContent">
						<p>
							<%=bc.getCommentContent() %>
						</p>
						<button class="recommentBtn">댓글</button>
						<input type="hidden" value="<%=bc.getCommentNo() %>" class="commentPK">
					</div>
					<div class="commentUpdate">
					<%if(loginMember!=null&&(bc.getCommentWriter().equals(loginMember.getNickName())
						||loginMember.getAuth().equals("M"))){ %>
							<button class="commentUpdateBtn">수정</button>
							<button class="commentDeleteBtn">삭제</button>
					<%}%>
					</div>
					<hr>
					<%}%>
				<div class="recomment">
					<%for(BoardComment brc:comments){
						if(bc.getCommentNo()==brc.getCommentNoFK()){%>
						<div class="commentTop">
							<h4><%=brc.getCommentWriter() %></h4>
							<p><%=brc.getCommentDate() %></p>
						</div>
						<div class="commentContent">
							<p><%=brc.getCommentContent() %></p>
							<input type="hidden" value="<%=brc.getCommentNo() %>" class="commentPK">
						</div>
						<%if(loginMember!=null&&(brc.getCommentWriter().equals(loginMember.getNickName())
									||loginMember.getAuth().equals("M"))){ %>
							<div class="commentUpdate">
								<button class="commentUpdateBtn">수정</button>
								<button class="commentDeleteBtn">삭제</button>
							</div>
						<%} %>
						<hr>
					<%}
					}//for문%>
				</div>
				<%} //for문
				}%>
			</div><!-- comment -->
            </div>
            <%}else{ %>
            <div>조회된 데이터가 없습니다. 관리자에게 문의하세요.</div>
            <%} %>
        </div><!-- ServiceCenter -->
</section>
<script>
//댓글 삭제
$(document).on("click",".commentDeleteBtn",e=>{
	const $commentNo=$(e.target).parent().prev().find(".commentPK");
	$.ajax({
		url : "<%=request.getContextPath()%>/service/inquiryCommentDelete.do",
		data:{"commentNo":$commentNo.val()},
		type:"post",
		success : function(result) {
			if (result) {
				alert("삭제됐습니다.");
				location.reload();
			}
		},
		error : function() {
			alert("삭제 실패했습니다. 관리자에게 문의하세요.");
		}
	});
});

//댓글 수정
$(document).on("click",".updateCommentData",e=>{
	const $div=$(e.target).parent();
	const newData=$div.find("textarea").val();
	console.log($div);
	console.log(newData);
	$.ajax({
		url : "<%=request.getContextPath()%>/service/inquiryCommentUpdate.do",
		data : {
			"commentNo":$div.prev().val(),
			"content":newData
		},
		type : "post",
		success : function(result) {
			if (result) {
				alert("수정됐습니다.");
				$div.parent().find("p").text(newData);
				$("p").css("display","block");
				$("button").css("display","block");
				$div.css("display","none");
			}
		},
		error : function() {
			alert("수정 실패했습니다. 관리자에게 문의하세요.");
		}
	});
});

//댓글 작성
$(document).on("click",".commentBtn",e=>{ //동적 태그에도 이벤트 부여
	const comment=$(e.target).parent().find("textarea");
	$.ajax({
		url : "<%=request.getContextPath()%>/service/inquiryCommentInsert.do",
		data : {
			"writer" : "<%=loginMember != null ? loginMember.getUserId() : null%>",
			"inquiryNo":<%=q.getInquiryNo()%>,
			"commentContent":comment.val(),
			"commentFK":$(".recomment>.commentWrite").find(".commentFK").val()
		},
		type : "post",
		success : function(result) {
			if (result) {
				alert("댓글 등록됐습니다.");
			}else{
				alert("등록 실패했습니다.");
			}
			comment.val(''); //댓글 등록시 댓글 등록창 초기화
			location.reload();
		},
		error : function() {
			alert("등록 실패했습니다.");
		}
	});
});
</script>
<script src="<%=request.getContextPath()%>/js/service/inquiryContent.js"></script>
<%@ include file="/views/common/footer.jsp" %>