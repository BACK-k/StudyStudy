<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Spring01_MemberDetail </title>
</head>
<body>
<table border="1" style="width:100%">
	<tr bgcolor="purple">
		<th>ID</th><th>PASSWORD</th><th>NAME</th><th>AGE</th><th>JNO</th>
		<th>Info</th><th>Point</th><th>BRITHDAY</th><th>추천인</th>
	</tr>
<c:set var="a" value="${requestScope.apple}"/>
	<tr>
		<td>${a.id}</td><td>${a.password}</td><td>${a.name}</td><td>${a.age}</td><td>${a.jno}</td>
		<td>${a.info}</td><td>${a.point}</td><td>${a.birthday}</td><td>${a.rid}</td>
	</tr>
</table>

&nbsp;<a href="home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>