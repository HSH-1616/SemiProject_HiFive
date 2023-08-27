<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.semi.member.model.vo.Member"%>
<%
List<Member> members = (List) request.getAttribute("members");

%>

 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/manageMember.css" />
<%@ include file="/views/admin/manageMemberHome.jsp"%>



<section class="info">
			<div id="search-userId">
				<form action="<%=request.getContextPath()%>/searchMember.do">
					<select name="option" id="optionSelecter">
						<option value="email">이메일</option>
						<option value="user_id">아이디</option>
						<option value="user_name">이름</option>
						<option value="nickName">별명</option>
					</select>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요" value="">
					<button class="searchBtn" type="submit">검색</button>
				</form>
			</div>
			
			
			
	
	<div class="userTable">
		<table class="table text-center">
			<thead>
				<tr>
					<th colspan="1">
					<th>이메일</th>
					<th>아이디</th>
					<th>이름</th>
					<th>별명</th>
					<th>신고누적수</th>
					<th>가입일</th>
					<th>온도</th>
					<th>수정/삭제</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (members.isEmpty()) {
				%>
				<tr>
					<td colspan="7">조회된 회원이 없습니다.</td>
					<%
					} else {
					for (Member m : members) {
					%>
				
				<tr>
					<td>
						<input type="checkbox" id="memberChoice" name="deleteCheck" value="<%=m.getUserId()%>"/>
					</td>
					<td><%=m.getEmail()%></td>
					<td><%=m.getUserId()%></td>
					<td><%=m.getUserName()%></td>
					<td><%=m.getNickName()%></td>
					<td><%=m.getDeclareCount()%></td>
					<td><%=m.getEnrollDate()%></td>
					<td><%=m.getTemperature()%></td>
					<td><button id="<%=m.getUserId() %>" type="button" class="updateBtn btn btn-primary btn-sm">수정</button>
						<button type="button" class="deleteBtn btn btn-danger btn-sm"
							onclick="location.replace('<%=request.getContextPath()%>/userRemove.do?email=<%=m.getEmail()%>');">삭제</button></td>	
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
		<button id="userRemoveAll" type="button" class="btn btn-danger">모든회원삭제</button>
		<button id="userRemoveCheck" type="button" class="btn btn-danger">선택삭제</button>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script>
	
	$().ready(function () {
        $("#userRemoveAll").click(function () {
            Swal.fire({
                title: '정말로 모든회원을 삭제하시겠습니까?',
                text: "회원을 다시 되돌릴 수 없습니다. 신중히 선택하세요.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '승인',
                cancelButtonText: '취소'
            }).then((result) => {
               if (result.isConfirmed) {
                    location.replace("/semi-hifive/allUserRemove.do");
               }
            })
        });
    });
	
	$(".updateBtn").click(e=>{
		const userId = $(e.target)[0].id;
		var width = '600';
		var height = '700';
		let left = Math.ceil((window.screen.width - width) / 2);
		let top = Math.ceil((window.screen.height - height) / 2);
		/* 마이페이지 memberdao */
		open("<%=request.getContextPath()%>/userUpdate.do?userId=" + userId, "_blank", 'width=' + width + ', height=' + height + ', left=' + left + ', top = ' + top);
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
			url: "deleteCheck",
			data: {"arr": arr},  
			success: function(result) {
				if(result>=1){
					alert("성공적으로 삭제되었습니다.");
					location.replace("/semi-hifive/memberList.do");
				}else{
					alert("오류로 인해 삭제가 실패했습니다");
					location.replace("/semi-hifive/memberList.do");
				}
			},
			error: function() {
				console.log("선택삭제실패");
			}
		})
		
	})
	
	
	
	</script>

</section>