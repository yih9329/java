<%@page import="com.lmp.mylib.SeatWeb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 로그인 성공</title>
</head>
<body>
	<%
		SeatWeb seat = (SeatWeb) request.getAttribute("seat");
		String memName = seat.getMemName();
		int seatNum = seat.getSeatNum();
	%>
	<p>회원명 : <%=memName%> </p>
	<input type="button" value="차량 신청" onClick="location.href='/mylib/member/showRideTime'">&nbsp;
	<input type="button" value="차량 신청 내역" onClick="location.href='/mylib/member/showMyRideInfo'">&nbsp;
	<input type="button" value="메시지 보내기"><br>
	<input type="button" value="로그아웃" onClick="location.href='/mylib/member/logout'">
</body>
</html>