<%@page import="java.util.List"%>
<%@page import="com.lmp.mylib.RTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>차량 신청 시간 변경</title>
</head>
<body>
	<%
		List<RTime> rtime = (List<RTime>) request.getAttribute("rtime");
		List<Integer> rideNum = (List<Integer>) request.getAttribute("rideNum");
		String curTime = (String) request.getAttribute("curTime");
	%>
		<h3>변경 시간을 선택하세요</h3>
		<p>현재 신청 시간 : <%=curTime%></p>
		<table border="1" style="text-align:center">
			<tr>
				<td>시간</td>
				<td>현재인원</td>
				<td>최대인원</td>
			</tr>
	<%
			for(int i=0; i<rtime.size(); i++){
				String time = rtime.get(i).getPosTime();
				int maxNum = rtime.get(i).getMaxNum();
				int curNum = rideNum.get(i);
				if(curNum == maxNum || curTime.equals(time)){
	%>
					<tr>
						<td><%=time%></td>
						<td><%=curNum%></td>
						<td><%=maxNum%></td>
					</tr>
	<%
				}
				else {
	%>
					<tr>
						<td><a href="/mylib/member/modifyRide?newTime=<%=time%>"><%=time%></a></td>
						<td><%=curNum%></td>
						<td><%=maxNum%></td>
					</tr>
	<%
				}
			}
	%>
		</table>
</body>
</html>