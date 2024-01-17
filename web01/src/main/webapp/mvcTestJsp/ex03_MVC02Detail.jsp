<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ex03_MVC02Detail </title>
</head>
<body>
<h2> ex03_MVC02Detail </h2>
<table border="1" style="width:100%">
	<tr bgcolor="orange">
		<th>Sno</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th><th>Point</th>
	</tr>
<c:if test="${!empty requestScope.myInfo}">
		<tr>
			<td>${requestScope.myInfo.sno}</td><td>${requestScope.myInfo.name}</td><td>${requestScope.myInfo.age}</td>
			<td>${requestScope.myInfo.jno}</td><td>${requestScope.myInfo.info}</td><td>${requestScope.myInfo.point}</td>
		</tr>
</c:if>
<c:if test="${empty requestScope.myInfo}">
	<tr>
		<td colspan="6"><h3> 출력할 자료가 없습니다 </h3></td>
	</tr>
</c:if>
</table>
<hr>
<h3><a href='javascript:history.go(-1)'>이전으로</h3>

</body>
</html>