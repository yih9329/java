<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 삭제 실패</title>
</head>
<body>
	<h2>회원 삭제가 실패하였습니다.</h2>
	<input type="button" value="다시 시도" onClick="location.href='/mylib/admin/showSeatInfo'">&nbsp;
	<form action="/mylib/admin/goToMngMntPage">
		<input type="submit" value="회원 관리 창으로">&nbsp;
	</form>
	<form action="/mylib/admin/logout">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>