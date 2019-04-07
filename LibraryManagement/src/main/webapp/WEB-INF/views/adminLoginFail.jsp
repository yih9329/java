<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 실패</title>
</head>
<body>
	<h1>로그인에 실패하셨습니다.</h1>
	<form action="/mylib">
		<input type="submit" value="메인으로"><br>
	</form>
	<input type="button" value="다시시도" onClick="location.href='/mylib/resources/adminLogin.html'">
	
</body>
</html>