<%@page import="java.util.List"%>
<%@page import="com.lmp.mylib.Seat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>좌석 정보</title>
</head>
<body>
	<%
		List<Seat> seat = (List<Seat>) request.getAttribute("seatList");
	%>
	<table border="1" style="text-align:center;">
		<th width="50">좌석</th>
		<th width="100">이름</th>
	<%
		for(int i=0; i<seat.size(); i++){
			Seat s = seat.get(i);
			int seatNum = s.getSeatNum();
			String memName = s.getMemName();
			String memPw = s.getMemPassword();
			if(memName != null){
	%>
		<tr>
			<td><a href="/mylib/admin/showMemInfo?seatNum=<%=seatNum%>&memName=<%=memName%>&memPw=<%=memPw%>"><%=seatNum%></a></td>
			<td><%=memName%></td>
		</tr>
	<%		
			}
			else {
	%>
		<tr>
			<td><a href="/mylib/admin/goToMngMntPage"><%=seatNum%></a></td>
			<td>사용가능</td>
		</tr>	
	<%
			}
		}
	%>
	
	</table>
</body>
</html>