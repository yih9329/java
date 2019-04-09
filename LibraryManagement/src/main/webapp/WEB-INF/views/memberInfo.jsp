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
		String memPhone = (String) request.getAttribute("memPhone").toString();
		String memAddress = (String) request.getAttribute("memAddress");
		String memPassword = (String) request.getAttribute("memPassword");
	%>
	<h3>회원 정보</h3>
	좌석번호 : <%=seatNum%><br>
	이     름 : <%=memName%><br>
	성     별 : <%=memSex%><br> 
	나     이 : <%=memAge%><br>
	주     소 : <%=memAddress%><br>
	연 락 처 : <%=memPhone%><br>
	비밀번호 : <%=memPassword%><br>
	
	
	
	
</body>
</html>