<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** home **</title>
</head>
<body>
<h2> Web02_MVC02 </h2>
<c:choose>
	<c:when test="${!empty sessionScope.loginName}">
		<span>${sessionScope.loginName}님 안녕하세요</span>
	</c:when>
	<c:otherwise>
		<span> 로그인 후 이용하세요 </span>
	</c:otherwise>
</c:choose>
<br>
<img alt="" src="/web02/images/letsgo.png" width="300" height="200">
<hr>

<c:choose>
	<c:when test="${not empty sessionScope.loginName}">
		&nbsp;<a href="/web02/mdetail">MyInfo</a>&nbsp;
		&nbsp;<a href="/web02/logout">Logout</a><br>
	</c:when>
	<c:otherwise>
		&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
		&nbsp;<a href="/web02/member/joinForm.jsp">Join</a><br>
	</c:otherwise>
</c:choose>

&nbsp;<a href="/web02/mlist">MList</a>
</body>
</html>