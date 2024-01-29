<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Spring MVC2 BoardList </title>
	<link rel="stylesheet" type="text/css" 
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2> Spring MVC2 BoardList </h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table border="1" style="width:100%">
<tr bgcolor="Aquamarine">
	<th>Seq</th><th>Title</th><th>ID</th><th>RegDate</th><th>조회수</th>
</tr>
	
<c:if test="${!empty requestScope.banana }">
	<c:forEach var="b" items="${requestScope.banana}">
		<tr>
			<td>${b.seq}</td>
			<td>
			<c:if test="${!empty loginID}">
				<a href="detail?jCode=D&seq=${b.seq}">${b.title}</a>
			</c:if>
			<c:if test="${empty loginID}">
				${b.title}
			</c:if>
			</td>
			<td>${b.id}</td><td>${b.regdate}</td><td>${b.cnt}</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana }">
	<tr>
		<td colspan="5"> 정보가 없습니다 </td>
	</tr>
</c:if>
</table>
</div>
<c:if test="${!empty sessionScope.loginID}">
&nbsp;<a href="boardInsert">글쓰기</a>
</c:if>
<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>