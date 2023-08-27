<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
	String script=(String)request.getAttribute("script");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템메세지</title>
</head>
<body>
	<script>
		// alert 뒤에 "" 없이 값만넣으면 변수로 인식됨
		alert("<%=msg%>");
		<%=script!=null?script:""%>; // 받아온 script 값이 있으면 저장된 close(); 함수호출하여 창을닫게함
		location.replace("<%=request.getContextPath()%><%=loc%>");
	</script>
</body>
</html>