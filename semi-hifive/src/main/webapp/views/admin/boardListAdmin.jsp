<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.semi.sc.model.dto.Board"%>

<%
List<Board> boards = (List) request.getAttribute("boards");
%>
<%@ include file="/views/admin/manageMemberHome.jsp"%>



<section class="info">
			<div id="search-userId">
				<form action="<%=request.getContextPath()%>/searchMember.do">
					<select name="option" id="optionSelecter">
						<option value="email">작성자</option>
						<option value="user_id">제목</option>
						<option value="user_name">내용</option>
						<option value="nickName">날짜</option>
					</select>
					<input type="text" name="searchKeyword" size="25" placeholder="" value="">
					<button class="searchBtn" type="submit">검색</button>
				</form>
			</div>
			
			
	<div class="userTable">
		<table class="table text-center">
			<thead>
				<tr>
					<th colspan="1">
					<th>게시물번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>날짜</th>
					<th>확인/수정/삭제</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (boards.isEmpty()) {
				%>
				<tr>
					<td colspan="6">조회된 공지사항이 없습니다.</td>
					<%
					} else {
					for (Board b : boards) {
					%>
				
				<tr>
					<td>
						<input type="checkbox" id="memberChoice" name="deleteCheck" value="<%=b.getBoardNo()%>"/>
					</td>
					<td><%=b.getBoardNo()%></td>
					<td><%=b.getBoardWriter()%></td>
					<td><%=b.getBoardTitle()%></td>
					<td><%=b.getBoardDate()%></td>
					<td>

					<button onclick="location.href='<%=request.getContextPath()%>/service/boardContent.do?boardNo=<%=b.getBoardNo()%>'" type="button" class="btn btn-dark">확인</button>
					<button id="<%=b.getBoardNo()%>" type="button" class="updateBtn btn btn-primary btn-sm">수정</button>
						<button type="button" class="deleteBtn btn btn-danger btn-sm"
							onclick="location.replace('<%=request.getContextPath()%>/boardRemove.do?boardNo=<%=b.getBoardNo()%>');">삭제</button></td>	
				</tr>
				<%
					}
				}
				%>
			</tbody>
		</table>
		<div id="pageBar" class="text-center">
			<%=request.getAttribute("pageBar")%>
		</div>
		
		<button id="userRemoveAll" type="button" class="btn btn-danger">모든공지사항삭제</button>
		<button id="userRemoveCheck" type="button" class="btn btn-danger">선택삭제</button>
	</div>


<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script>
	
	$().ready(function () {
        $("#userRemoveAll").click(function () {
            Swal.fire({
                title: '정말로 모든공지사항을 삭제하시겠습니까?',
                text: "다시 되돌릴 수 없습니다. 신중히 선택하세요.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '승인',
                cancelButtonText: '취소'
            }).then((result) => {
               if (result.isConfirmed) {
                    location.replace("/semi-hifive/allBoardRemove.do");
               }
            })
        });
    });
	
	$(".updateBtn").click(e=>{
		const boardNo = $(e.target)[0].id;
		var width = '600';
		var height = '700';
		let left = Math.ceil((window.screen.width - width) / 2);
		let top = Math.ceil((window.screen.height - height) / 2);
		/* 마이페이지 memberdao */
		open("<%=request.getContextPath()%>/boardUpdate.do?boardNo=" + boardNo, "_blank", 'width=' + width + ', height=' + height + ', left=' + left + ', top = ' + top);
	})
	
	
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
		}
		else{
			$("#userRemoveCheck").hide();
			$("#userRemoveAll").show();
			$(".updateBtn").show();
			$(".deleteBtn").show();
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
			url: "deleteCheckBoard",
			data: {"arr": arr},  
			success: function(result) {
				if(result>=1){
					alert("성공적으로 삭제되었습니다.");
					location.replace("/semi-hifive/boardListAdmin.do");
				}else{
					alert("오류로 인해 삭제가 실패했습니다");
					location.replace("/semi-hifive/boardListAdmin.do");
				}
			},
			error: function() {
				console.log("선택삭제실패");
			}
		})
		
	})
	</script>

</section>


