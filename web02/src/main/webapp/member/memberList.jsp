<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Web02_MVC02 MemberList </title>
</head>
<body>
<h2> Web02_MVC02 MemberList </h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>

<table border="1" style="width:100%">
	<tr bgcolor="Aquamarine">
		<th>ID</th><th>PASSWORD</th><th>NAME</th><th>AGE</th><th>JNO</th>
		<th>Info</th><th>Point</th><th>BRITHDAY</th><th>추천인</th>
	</tr>
	
<c:if test="${!empty requestScope.banana }">
	<c:forEach var="b" items="${requestScope.banana}">
		<tr>
			<td>${b.id}</td><td>${b.password}</td><td>${b.name}</td>
			<td>${b.age}</td><td>${b.jno}</td><td>${b.info}</td>
			<td>${b.point}</td><td>${b.birthday}</td><td>${b.rid}</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana }">
	<tr>
		<td colspan="9"> 정보가 없습니다 </td>
	</tr>
</c:if>

&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</table>
</body>
</html>