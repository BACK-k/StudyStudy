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
<h2> Dynamic Web Project </h2>
<%-- <%
// Java Code
// Jsp 가 간편한 이유는 웹에서 필요한 기본 객체
// request, response, session, page, out 들을 객체화해서 제공해줌
	if(session.getAttribute("loginName") != null){ %>
		<h3><%=session.getAttribute("loginName")%>님 안녕하세요</h3>
<%	} else{ %>
		<h3> 로그인 후 이용하세요 </h3>
<%	}
%> --%>

<!-- JSTL -->
<%-- <!-- if -->
<c:if test ="${!empty sessionScope.loginName}">
	<h3>${sessionScope.loginName}님 안녕하세요</h3>
</c:if>
<c:if test ="${empty sessionScope.loginName}">
	<h3>로그인 후 이용하세요</h3>
</c:if>
<hr> --%>

<c:choose>
	<c:when test="${!empty sessionScope.loginName}">
		<span>${sessionScope.loginName}님 안녕하세요</span>
	</c:when>
	<c:otherwise>
		<span> 로그인 후 이용하세요 </span>
	</c:otherwise>
</c:choose>
<hr>
	<form action="getpost" method="get">
		<input type="text" name="id" value="banana" size="10"> &nbsp; <input type="text" name="name" value="바나나" size="10"> &nbsp; <input type="text" value="password" size="10"> <input type="submit" value="Test">
	</form>
	<hr>
<!-- 경로 표기
	절대경로 : / 로 시작, 프로젝트명부터 시작
		/web01/images/letsgo.png
	상대경로 : 현재위치에서 시작
		./ 현재위치, ../ 상위 폴더로
		./images/lestgo.p
-->
	<img alt="" src="images/letsgo.png" width="300" height="200">
	<hr>

<c:choose>
	<c:when test="${not empty sessionScope.loginName}">
	<!-- home.jsp에서 MyInfo를 클릭했을 때
	controller 서블릿
	mvcTest패키지 Ex02_MVC02Detail.java로 이동, Service를 처리하고 결과를 requestScope에 담고, Forward
	View JSP
	mvcTestJsp파일 ex03_MVC02Detail.jsp로 이동해 requestScope에 담긴 결과를 확인하고 출력 -->
		<a href="/web01/detail">MyInfo</a>&nbsp;
		<a href="/web01/logout">Logout</a><br>
	</c:when>
	<c:otherwise>
		<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>&nbsp;
		<a href="#">Join</a><br>
	</c:otherwise>
</c:choose>

	<a href="/web01/hello">HelloS</a>&nbsp; &nbsp;
	<a href="/web01/list">MVC01ListS</a>&nbsp; &nbsp;
	<a href="/web01/life">LifeCycle</a>
	<br> &nbsp;
	<a href="/web01/servletTestForm/form01_Adder.html">Adder</a>&nbsp; &nbsp;
	<a href="/web01/servletTestForm/form02_Radio.jsp">Radio</a>&nbsp; &nbsp;
	<a href="/web01/servletTestForm/form03_Check.jsp">Check</a>&nbsp; &nbsp;
	<a href="/web01/servletTestForm/form04_Select.jsp">Select</a>&nbsp; &nbsp;
	<a href="/web01/flow01">Flow01</a>&nbsp;
	<a href="/web01/sessioni">SessionI</a><br>
	<a href="/web01/jsp01/ex01_HelloJsp.jsp">HelloJ</a>
	<a href="/web01/jsp01/ex02_mvc01List.jsp">M01ListJ</a>
	<a href="/web01/list2">M02List</a>
</body>
</html>