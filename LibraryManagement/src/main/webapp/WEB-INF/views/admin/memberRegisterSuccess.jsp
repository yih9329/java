<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 등록 성공</title>
</head>
<body>
	<h2>회원 등록이 완료되었습니다.</h2>
	<form action="/mylib/admin/goToMngMntPage">
		<input type="submit" value="회원 관리 창"> &nbsp;
	</form>
	<form action="/mylib/admin/logout">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>