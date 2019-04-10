<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 등록</title>
</head>
<body>
	<%
		String seatNum = request.getParameter("seatNum");
	%>
	<form action="/mylib/admin/register" method="post">
	  	좌석번호 : <input type="text" name="seatNum" value="<%=seatNum%>" style="width:20px" readonly="readonly"><br>
		이     름 : <input type="text" name="memName" required style="width:60px"><br>
		성     별 : <input type="radio" name="memSex" value="남" required>남 <input type="radio" name="memSex" value="여">여<br>
		나     이 : <input type="text" name="memAge" required style="width:20px"><br>
		연 락 처 : <input type="text" name="memPhone.ph_1" required style="width:50px">-
			    <input type="text" name="memPhone.ph_2" required style="width:50px">-
			    <input type="text" name="memPhone.ph_3" required style="width:50px"><br>
		주     소 : <input type="text" name="memAddress" required style="width:500px"><br>
		비밀번호 : <input type="password" name="memPassword" required style="width:80px"><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>