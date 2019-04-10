<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정 실패</title>
</head>
<body>
	<h3>회원 정보 수정에 실패하였습니다.</h3>
	<form action="/mylib/admin/goToMngMntPage">
		<input type="submit" value="회원관리창">
	</form>
	<form action="/mylib/admin/showSeatInfo">
		<input type="submit" value="좌석정보">
	</form>
	<form action="/mylib/admin/logout">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>