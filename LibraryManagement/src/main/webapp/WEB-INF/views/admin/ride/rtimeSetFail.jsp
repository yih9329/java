<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>운행 시간 설정 실패</title>
</head>
<body>
	<h3>시간 설정에 실패하였습니다.</h3>
	<input type="button" value="다시 시도" onClick="location.href='/mylib/resources/setRideTime.html'"><br>
	<form action="/mylib/admin/goToMngMntPage">
		<input type="submit" value="관리 페이지로">
	</form>
	<form action="/mylib/admin/logout">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>