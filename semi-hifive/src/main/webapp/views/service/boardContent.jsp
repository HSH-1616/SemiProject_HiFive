<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.semi.sc.model.dto.*, java.util.List"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/service/boardContent.css">
<section>
	<%@ include file="/views/service/serviceCategory.jsp"%>
	<%
	Board b=(Board)request.getAttribute("board"); //게시판 객체
	List<BoardComment> comments=(List)request.getAttribute("comments");
	List<BoardFile> files=(List<BoardFile>)request.getAttribute("files");
%>
<%if(b!=null){ %>
	<div class="ServiceCenter">
		<div class="boardContent">
			<div class="contentTitle">
				<%if(b.getNoticeYn()=='Y'){ %>
				<h2><%=b.getBoardTitle() %></h2>
				<%}else{ %>
				<h2>
					[<%=b.getBoardCategory() %>]<%=b.getBoardTitle() %></h2>
				<%} %>
				<button class="backBtn" onclick="history.back();">뒤로 가기</button>
			</div>
			<%if(loginMember!=null&&loginMember.getAuth().equals("M")){ %>
			<div>
			<button id="contentDelete"
			onclick="location.href='<%=request.getContextPath() %>/service/deleteBoard.do?no=<%=b.getBoardNo()%>'">
			삭제</button>
			</div>
			<%} %>
			<div class="content">
				<input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>">
				<div class="contentTop">
					<p>작성자 : <%=b.getBoardWriter() %></p>
					<p><%=b.getBoardDate() %></p>
				</div>
				<p><%=b.getBoardContent() %></p>
				<%if(files!=null){
					for(BoardFile bf:files){%>
				<img src="<%=request.getContextPath() %>/upload/board/<%=bf.getBoardFileName() %>" width="800">
				<%}
				}%>
			</div>
			<%if(b.getNoticeYn()=='Y'){ %>
				<%if(loginMember!=null){ %>
				<div class="commentWrite">
					<textarea name="boardComment" class="boardComment" cols="120"
						rows="5" placeholder="댓글을 작성하세요."></textarea>
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
						<%if(loginMember!=null){ %>
						<button class="recommentBtn">댓글</button>
						<%} %>
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
			<%} %>
		</div>
	</div>
<%} %>
</section>
<script src="<%=request.getContextPath()%>/js/service/boardContent.js"></script>
<script>
//댓글 삭제
$(document).on("click",".commentDeleteBtn",e=>{
	const $commentNo=$(e.target).parent().prev().find(".commentPK");
	$.ajax({
		url : "<%=request.getContextPath()%>/service/commentDelete.do",
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
		url : "<%=request.getContextPath()%>/service/commentUpdate.do",
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
		url : "<%=request.getContextPath()%>/service/commentInsert.do",
		data : {
			"writer" : "<%=loginMember != null ? loginMember.getUserId() : null%>",
			"boardNo":<%=b.getBoardNo()%>,
			"commentContent":comment.val(),
			"commentFK":$(".recomment>.commentWrite").find(".commentFK").val()
		},
		type : "post",
		success : function(result) {
			if (result) {
				alert("등록성공");
			}
			comment.val(''); //댓글 등록시 댓글 등록창 초기화
			location.reload();
		},
		error : function() {
			alert("등록 실패");
		}
	});
});
</script>
<%@ include file="/views/common/footer.jsp"%>