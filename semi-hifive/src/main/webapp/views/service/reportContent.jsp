<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.semi.sc.model.dto.*, com.semi.sc.model.dto.ReportData" %>
<%@ include file="/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/boardContent.css">
<%
	Report r=(Report)request.getAttribute("report");
	List<BoardComment> comments=(List)request.getAttribute("comments");
	List<ServiceFile> files=(List<ServiceFile>)request.getAttribute("files");
	ReportData rd=(ReportData)request.getAttribute("reportData");
%>
<section>
<%@ include file="/views/service/serviceCategory.jsp" %>
	<div class="ServiceCenter">
	<%if(r!=null){ %>
            <div class="reportContent">
                <div class="contentTitle">
                    <h2><%=r.getReportTitle() %></h2>
                    <button class="backBtn" onclick="history.back();">뒤로 가기</button>
                </div>
                <%if(loginMember!=null&&r.getReportWriter().equals(loginMember.getNickName())
                	||loginMember.getAuth().equals("M")){ %>
					<div>
					<button id="contentDelete"
					onclick="location.href='<%=request.getContextPath() %>/service/deleteReport.do?no=<%=r.getReportNo()%>'">
					삭제</button>
					</div>
				<%} %>
                <div class="content">
                    <div class="contentTop">
                        <p>작성자 : <%=r.getReportWriter() %></p>
                        <p><%=r.getReportDate() %></p>
                    </div>
            <div class="productContainer">
			<div>
				<h4 style="padding-left: 10px;">신고한 판매글</h4>
			</div>
			<div class="reportList">
				<table>
					<tr>
						<th>거래자</th>
						<th>거래 게시물</th>
						<th>거래 금액</th>
						<th>판매글 등록 날짜</th>
					</tr>
				<%if(rd!=null){%>
					<tr>
						<td><%=rd.getBuyerId() %></td>
						<td class="productTitle"><%=rd.getProductTitle() %></td>
						<td><%=rd.getPrice() %>원</td>
						<td><%=rd.getRegistTime() %></td>
					</tr>
					<%
					}else{%>
					<tr>
					<td colspan="4"><h4>신고한 거래 내역이 없습니다.</h4></td>
					</tr>
					<%}%>
				</table>
			</div><!-- reportList -->
		</div><!-- buyliseContainer -->
		<br>
                    <p><%=r.getReportContent() %></p>
                    <%if(files!=null){
						for(ServiceFile sf:files){%>
						<img src="<%=request.getContextPath() %>/upload/report/<%=sf.getFilename() %>" width="800">
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
        </div>
   <%} %>
</section>
<script>
//댓글 삭제
$(document).on("click",".commentDeleteBtn",e=>{
	const $commentNo=$(e.target).parent().prev().find(".commentPK");
	$.ajax({
		url : "<%=request.getContextPath()%>/service/reportCommentDelete.do",
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
		url : "<%=request.getContextPath()%>/service/reportcommentUpdate.do",
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
		url : "<%=request.getContextPath()%>/service/reportCommentInsert.do",
		data : {
			"writer" : "<%=loginMember != null ? loginMember.getUserId() : null%>",
			"reportNo":<%=r.getReportNo()%>,
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