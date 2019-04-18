<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>차량 신청 내역 존재</title>
</head>
<body>
	<%
		String rtime = (String) request.getAttribute("rtime");
	%>
	
	신청 시간 : <%=rtime%><br>
	<input type="button" value="시간 변경" onClick="location.href='/mylib/member/modifyMyRideInfo'">&nbsp;
	<input type="button" value="차량 취소" onClick="location.href='/mylib/member/deleteMyRideInfo'"><br>
	<input type="button" value="확인" onClick="location.href='/mylib/member/goToMngMntPage'">
	
</body>
</html>