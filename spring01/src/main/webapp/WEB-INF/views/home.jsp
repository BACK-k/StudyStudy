<!-- 한글 깨짐 해결 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 세션을 사용하지 않겠다는 의미
<%@ page session="false" %> --%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h2> Hello Spring!!! </h2>
<P>  The time on the server is ${serverTime}. </P>
<hr>
<c:choose>
	<c:when test="${!empty sessionScope.loginName}">
		<span>${sessionScope.loginName}님 안녕하세요</span>
	</c:when>
	<c:otherwise>
		<span> 로그인 후 이용하세요 </span>
	</c:otherwise>
</c:choose>
<c:if test="${!empty requestScope.message}">
	<hr><h4>${requestScope.message}</h4>
</c:if>
<hr>
<c:choose>
	<c:when test="${not empty sessionScope.loginName}">
		&nbsp;<a href="mdetail">MyInfo</a>&nbsp;
		&nbsp;<a href="mdetail?jCode=U">내정보수정</a>&nbsp;
		&nbsp;<a href="logout">Logout</a><br>
		&nbsp;<a href="mdelete">회원탈퇴</a><br>
	</c:when>
	<c:otherwise>
		&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
		&nbsp;<a href="/web02/member/joinForm.jsp">Join</a><br>
	</c:otherwise>
</c:choose>
&nbsp;<a href="mlist">MList</a>
&nbsp;<a href="mdetail">MDetail</a>
&nbsp;<a href="mlistsp">MListSp</a>
&nbsp;<a href="mdetailsp">MDetailSp</a>
<hr>
<img alt="" src="resources/images/blue.gif" width="855" height="300">
<br>
<img alt="" src="resources/images/karina2.gif" width="450" height="450">
<img alt="" src="resources/images/karina1.gif" width="400" height="450">

</body>
</html>
