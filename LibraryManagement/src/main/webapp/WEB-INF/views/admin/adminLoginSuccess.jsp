<%@ page language="java" contentType="text/html; charset=MS949"
    pageEncoding="MS949"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=MS949">
<title>ȸ�� ���� ������</title>
</head>
<body>
	<p>������ : ${admin.adminId} &nbsp;
		<form action="/mylib/admin/logout">
			<input type="submit" value="�α׾ƿ�">
		</form>
	<h2>��������</h2>
	<form action="/mylib/admin/showSeatInfo">
		<input type="submit" value="�¼� ����">
	</form>
	<form action="/mylib/admin/showRideInfo">
		<input type="submit" value="���� ����">
	</form>
	
</body>
</html>