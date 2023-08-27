<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.semi.sc.model.dto.Report"%>

<%
List<Report> reports = (List) request.getAttribute("reports");
%>

<%@ include file="/views/admin/manageMemberHome.jsp"%>


<section class="info">			
	<div class="userTable">
	

		<table class="table text-center">
			<thead>
				<tr>
					<th colspan="1">
					<th>신고글번호</th>
					<th>신고글작성자</th>
					<th>신고글제목</th>
					<th>신고글내용</th>
					<th>신고글등록날짜</th>
					<th>신고글카테고리</th>
					<th>글확인</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (reports.isEmpty()) {
				%>
				<tr>
					<td colspan="8">조회된 신고글이 없습니다.</td>
					<%
					} else {
					for (Report r : reports) {
						
					%>
				
				<tr>
					<td>
						<input type="checkbox" id="memberChoice" name="deleteCheck" value="<%=r.getReportNo()%>"/>
					</td>
					<td><%=r.getReportNo()%></td>
					<td><%=r.getReportWriter()%></td>
					<td><%=r.getReportTitle()%></td>
					<td><%=r.getReportContent()%></td>
					<td><%=r.getReportDate()%></td>
					<td><%=r.getReportCategory()%></td>


					<td>
					<button type="button" class="btn btn-dark"
							onclick="location.replace('<%=request.getContextPath()%>/service/reportContent.do?no=<%=r.getReportNo()%>');">확인</button></td>	
						</td>
					<td>
					<button type="button" class="deleteBtn btn btn-danger btn-sm"
							onclick="location.replace('<%=request.getContextPath()%>/reportRemove.do?reportNo=<%=r.getReportNo()%>');">삭제</button>
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
			url: "deleteCheckReport",
			data: {"arr": arr},  
			success: function(result) {
				if(result>=1){
					alert("성공적으로 삭제되었습니다.");
					location.replace("/semi-hifive/reportListAdmin.do");
				}else{
					alert("오류로 인해 삭제가 실패했습니다");
					location.replace("/semi-hifive/reportListAdmin.do");
				}
			},
			error: function() {
				console.log("선택삭제실패");
			}
		})
		
	})
	</script>