<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> PageFlow (import, redirect) </title>
</head>
<body>
<h2> forTokens, PageFlow (import, redirect) </h2>
<pre><b>
<h2>1 forTokens</h2>
=> 구분자로 분리된 각각의 토큰을 처리할때 사용됨.
=> test 1.1) 단일 구분자
<!-- delims : 토큰을 구분하는 구분자 -->
<c:forTokens var="city" items="성남,용인, 서울# 부산, Paris, NewYork" delims=",">
<!-- items에 있는 것들을 구분자를 통해 구분하고 배열처럼 나타낸다 -->
	${city}
</c:forTokens>

=> test 1.2) 복수 구분자
<!-- 구분자를 여러개 넣어도 다 인식해서 구분한다 -->
<c:forTokens var="city" items="성남,용인,서울#부산,Paris!NewYork" delims=",#!">
	${city}
</c:forTokens>

<h2>2 Import</h2>
=> directive: include -> 소스코드포함, 변수공유가능
=> jsp:include -> 웹Page포함, 변수공유 불가능
=> jstl:import -> 웹Page포함, 변수공유 불가능, 어떤 문서를 안에 포함할 수 있다
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
<%-- <!-- 상대경로 -->
<c:import url="../jsp01/ex01_HelloJsp.jsp"></c:import> --%>
<%-- <!-- 절대경로 -->
<c:import url="/jsp01/ex01_HelloJsp.jsp"></c:import> --%>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<h2>3 redirect </h2>
=> response.sendRedirect() 와 같은 기능
=> 웹브라우저 주소창의 url이 변경됨
<!-- JSTL에서 경로 설정시 프로젝트는 defualt로 들어가 그 이후부터 적는다 -->
<%-- <c:redirect url="/jsp01/ex01_HelloJsp.jsp" /> --%>

<h2>4 URL </h2>
=> Value 를 url로 인식 시켜줌
=> set 으로 정의해도 결과는 동일하지만 프로젝트명을 포함시켜줘야함
=> test 4.1) a_Tag Link
<c:url value="/jsp01/ex01_HelloJsp.jsp" var="urlTest" />
<%-- <c:set value="/web01/jsp01/ex01_HelloJsp.jsp" var="urlTest" /> --%>
<a href="${urlTest}">urlTest</a>

=> test 4.2) image
<c:url value="../images/aaa.gif" var="aaa" />
<img alt="urlTest" src="${aaa}">
</b></pre>
</body>
</html>