<%@page import="com.lmp.mylib.Ride"%>
<%@page import="com.lmp.mylib.RTime"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>차량 지원 현황</title>
</head>
<body>
	<%
		List<Ride> rideInfo = (List<Ride>) request.getAttribute("rideInfo");
		List<RTime> rtimeInfo = (List<RTime>) request.getAttribute("rtimeInfo");
	%>
	<table border="1" style="text-align:center;">
	<tr>
		<td>시간</td>
		<td>이름</td>
		<td>주소</td>
	</tr>
	<% 
		for(int i=0; i<rtimeInfo.size(); i++){
			String posTime = rtimeInfo.get(i).getPosTime();
	%>	
		<tr>
			<td><%=posTime%></td>
	<% 		for(int j=0; j<rideInfo.size(); j++){
				String appTime = rideInfo.get(i).getPosTime();
				if(posTime.equals(appTime)){
					String memName = rideInfo.get(i).getMemName();
					String memAddress = rideInfo.get(i).getMemAddress();
	%>
					<td><%=memName%></td>
					<td><%=memAddress%></td>
	<% 			}
					
			}
	%>
		</tr>
	<%
		}
	%>
	</table>
	
	<input type="button" value="시간 설정" onClick="location.href='/mylib/resources/setRideTime.html'">
</body>
</html>