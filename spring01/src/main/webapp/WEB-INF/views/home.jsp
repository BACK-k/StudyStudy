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
<c:if test="${!empty requestScope.message}">
	<hr><h4>${requestScope.message}</h4>
</c:if>
<hr>

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
