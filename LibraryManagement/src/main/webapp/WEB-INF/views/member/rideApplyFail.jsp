<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>차량 신청 실패</title>
</head>
<body>
	<h3>차량 신청을 이미 하셨습니다.</h3>
	<input type="button" value="시간 변경" onClick="location.href='/mylib/member/modifyMyRideInfo'">&nbsp;
	<input type="button" value="작업 창" onClick="location.href='/mylib/member/goToMngMntPage'">&nbsp;
	<input type="button" value="로그아웃" onClick="location.href='/mylib/member/logout'">
</body>
</html>