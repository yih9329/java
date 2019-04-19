<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>
</head>
<body>
	<%
		int seatNum = (int) request.getAttribute("seatNum");
		String memName = (String) request.getAttribute("memName");
		String memSex = (String) request.getAttribute("memSex");
		int memAge = (int) request.getAttribute("memAge");
		String[] memPhone = request.getAttribute("memPhone").toString().split("-");
		String memAddress = (String) request.getAttribute("memAddress");
		String memPassword = (String) request.getAttribute("memPassword");
	%>
	<h3>회원 정보</h3>
	<form action="/mylib/admin/modify" method="post">
		좌석번호 : <%=seatNum%><br>
		이 름 : <input type="text" name="memName" value="<%=memName%>" readonly="readonly" style="width:60px"><br>
		성 별 : <input type="text" name="memSex" value="<%=memSex%>" style="width:20px"><br>
		나 이 : <input type="text" name="memAge" value="<%=memAge%>" style="width:20px"><br>
		주 소 : <input type="text" name="memAddress" value="<%=memAddress%>" style="width:500px"><br>
		연락처 : <input type="text" name="memPhone.ph_1" value="<%=memPhone[0]%>" style="width:50px">-
			   <input type="text" name="memPhone.ph_2" value="<%=memPhone[1]%>" style="width:50px">-
			   <input type="text" name="memPhone.ph_3" value="<%=memPhone[2]%>" style="width:50px"><br>
		현재 비밀번호 : <input type="password" name="curPassword" value="<%=memPassword%>" readonly="readonly" style="width:80px"><br>
		변경 비밀번호 : <input type="password" name="memPassword" value="<%=memPassword%>" style="width:80px"><br>
		<input type="submit" value="수정">&nbsp;
	</form>
	<input type="button" value="삭제" onClick="location.href='/mylib/admin/delete?seatNum=<%=seatNum%>'">&nbsp;
	<input type="button" value="좌석이동" onClick="location.href='/mylib/admin/selectSeat?seatNum=<%=seatNum%>'">
</body>
</html>