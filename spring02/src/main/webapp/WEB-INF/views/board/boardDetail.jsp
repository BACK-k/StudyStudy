<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Spring02_BoardDetail </title>
	<link rel="stylesheet" type="text/css" 
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table border="1" style="width:100%">
	<tr bgcolor="purple">
		<th>Seq</th><th>Title</th><th>ID</th><th>Regdate</th><th>Cnt</th>
		<th>content</th>
		<th>Root</th><th>Step</th><th>Indent</th>
	</tr>
<c:set var="a" value="${requestScope.apple}"/>
	<tr>
		<td>${a.seq}</td><td>${a.title}</td><td>${a.id}</td><td>${a.regdate}</td>
		<td>${a.cnt}</td>
		<td>${a.content}</td>
		<td>${a.root}</td><td>${a.step}</td><td>${a.indent}</td>
	</tr>
</table>
</div>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}	
</c:if>
&nbsp;<a href="boardList">목록</a>&nbsp;
<c:if test="${requestScope.apple.id == sessionScope.loginID}">
&nbsp;<a href="detail?jCode=U&seq=${a.seq}">수정</a>&nbsp;
&nbsp;<a href="boardDelete?seq=${a.seq}">삭제</a>&nbsp;
</c:if>
<br>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;
</body>
</html>