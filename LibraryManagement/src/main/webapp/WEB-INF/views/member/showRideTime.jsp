<%@page import="java.util.List"%>
<%@page import="com.lmp.mylib.RTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>차량 운행 시간</title>
</head>
<body>
	<%
		List<RTime> rtime = (List<RTime>) request.getAttribute("rtime");
		if(rtime.size() == 0) {
	%>
		<h3>차량 운행 예정이 없습니다.</h3>	
		<form action="/mylib/member/logout">
			<input type="submit" value="로그아웃">
		</form>
		<form action="/mylib/member/goToMngMntPage">
			<input type="submit" value="작업 선택">
		</form>
	<%
		}
		
		else {
	%>
		<h3>원하는 시간을 선택하세요</h3>
		<form action="" method="post">
	<%
			for(int i=0; i<rtime.size(); i++){
				String time = rtime.get(i);	
	%>
				<input type="radio" name="reqRTime" value="<%=time%>"><%=time%><br>
	<%
			}
	%>
		</form>
	<%
		}
	%>
	
</body>
</html>