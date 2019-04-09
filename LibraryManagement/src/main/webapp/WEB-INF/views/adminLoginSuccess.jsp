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
	<form action="showSeatInfo">
		<input type="submit" value="좌석 정보"><br>
	</form>
	<input type="button" value="회원 등록" onClick="location.href='/mylib/resources/regMember.html'"><br>
	<input type="button" value="회원 삭제" onClick="location.href='/mylib/resources/delMember.html'"><br>
	<input type="button" value="회원 정보 수정" onClick="location.href='/mylib/resources/modMember.html'"><br>
	<input type="button" value="등록 회원 목록" onClick="location.href='/mylib/resources/showMember.html'"><br>
	
	
</body>
</html>