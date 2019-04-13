<%@ page language="java" contentType="text/html; charset=MS949"
    pageEncoding="MS949"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=MS949">
<title>회원 관리 페이지</title>
</head>
<body>
	<p>관리자 : ${admin.adminId} &nbsp;
		<form action="/mylib/admin/logout">
			<input type="submit" value="로그아웃">
		</form>
	<h2>업무선택</h2>
	<form action="/mylib/admin/showSeatInfo">
		<input type="submit" value="좌석 정보">
	</form>
	<form action="/mylib/admin/showRideInfo">
		<input type="submit" value="차량 지원">
	</form>
	
</body>
</html>