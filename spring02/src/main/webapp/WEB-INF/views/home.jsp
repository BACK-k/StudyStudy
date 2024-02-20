<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" 
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2> Hello Spring MVC !!! </h2>
<P> Home_time : ${serverTime} </P>
<hr>
<!-- 절대경로로 이미지넣어주기 -->
<img alt="" src="resources/images/Arcana1.png" width="250" height="250">
<img alt="" src="resources/images/Arcana2.gif" width="250" height="250">
<img alt="" src="resources/images/Arcana3.gif" width="250" height="250">
<img alt="" src="resources/images/RohJeongeui.jpg" width="380" height="450"><br>
<!-- <img alt="" src="resources/images/KarinaSapporo1.jpg" width="140" height="200">
<img alt="" src="resources/images/KarinaBlack11.jpg" width="130" height="200">
<img alt="" src="resources/images/KarinaPink6.jpg" width="140" height="200">
<img alt="" src="resources/images/KarinaSapporo3.jpg" width="160" height="200">
<img alt="" src="resources/images/KarinaBrown.gif" width="120" height="200">
<img alt="" src="resources/images/KarinaSapporo4.jpg" width="130" height="200">
<img alt="" src="/spring02/resources/images/karina1.gif" width="150" height="200">
<img alt="" src="resources/images/KarinaPink2.jpg" width="180" height="200">
<img alt="" src="resources/images/KarinaBlack4.jpg" width="140" height="200">
<img alt="" src="resources/images/KarinaBlack2.jpg" width="140" height="200"><br>
<img alt="" src="resources/images/KarinaSpring.jpg" width="220" height="400">
<img alt="" src="resources/images/karina2.gif" width="280" height="400">
<img alt="" src="resources/images/KarinaPink5.gif" width="380" height="300">
<img alt="" src="resources/images/KarinaParis.gif" width="330" height="400">
<img alt="" src="resources/images/KarinaBlack.jpg" width="270" height="400"><br>
<img alt="" src="resources/images/KarinaBlack6.jpg" width="200" height="200">
<img alt="" src="resources/images/KarinaFlower1.jpg" width="160" height="200">
<img alt="" src="resources/images/KarinaPink4.jpg" width="150" height="200">
<img alt="" src="resources/images/KarinaPink.jpg" width="200" height="140">
<img alt="" src="resources/images/KarinaFlower2.jpg" width="200" height="200">
<img alt="" src="resources/images/KarinaBlack12.jpg" width="150" height="200">
<img alt="" src="resources/images/KarinaBlack9.jpg" width="130" height="200">
<img alt="" src="resources/images/KarinaWhite1.jpg" width="100" height="200">
<img alt="" src="resources/images/KarinaPink7.jpg" width="140" height="200">
<img alt="" src="resources/images/KarinaBlack8.jpg" width="140" height="200">
<img alt="" src="resources/images/Karina3.jpg" width="200" height="110">
<img alt="" src="resources/images/KarinaBlack5.jpg" width="150" height="200">
<img alt="" src="resources/images/KarinaPink3.jpg" width="170" height="200">
<img alt="" src="resources/images/KarinaSapporo2.jpg" width="125" height="200">
<img alt="" src="resources/images/KarinaBlack10.jpg" width="140" height="200"><br>
<img alt="" src="resources/images/ShinSaekyeong3.gif" width="190" height="200">
<img alt="" src="resources/images/ShinSaekyeong.gif" width="200" height="200">
<img alt="" src="resources/images/ShinSaekyeong.jpg" width="150" height="200">
<img alt="" src="resources/images/ShinSaekyeong2.gif" width="180" height="200"> -->
<c:if test="${!empty sessionScope.loginName}">
	${sessionScope.loginName}님 안녕하세요<br>
</c:if>
<c:if test="${empty sessionScope.loginID}">
	로그인 후 이용하세요<br>
</c:if>
<c:if test="${!empty requestScope.message}">
	<hr><h4>${requestScope.message}</h4>
</c:if>
<hr>
<!-- Login 전 -->
<c:if test="${empty sessionScope.loginID}">
	&nbsp;<a href="member/loginForm">LoginF</a>
	&nbsp;<a href="member/joinForm">JoinF</a>
</c:if>
<!-- Login 후 -->
<c:if test="${!empty sessionScope.loginName}">
	&nbsp;<a href="member/detail?jCode=D">내정보</a>
	&nbsp;<a href="member/detail?jCode=U">내정보수정</a>
	&nbsp;<a href="member/logout">Logout</a>
	&nbsp;<a href="member/delete">회원탈퇴</a>
</c:if>
<br><hr>
	&nbsp;<a href="member/memberList">MList</a>
	&nbsp;<a href="jo/joList">JList</a>
	&nbsp;<a href="board/boardList">BList</a>
	&nbsp;<a href="bcrypt">BCrypt</a><br>
	&nbsp;<a href="board/bPageList">BPage</a>
	&nbsp;<a href="member/mPageList">MPage</a><br>
	&nbsp;<a href="etest">Exception</a>
	&nbsp;<a href="member/log4jTest">@Log4jTest</a>
	&nbsp;<a href="member/log4jTest">@Log4jTest</a><br>
	<hr>
	&nbsp;<a href="greensn">GreenSN</a>&nbsp;
	&nbsp;<a href="greenall">GreenALL</a>&nbsp;
	&nbsp;<a href="jeju">JeJu</a>&nbsp;
	&nbsp;<a href="gps">GPS</a>&nbsp;<br>

</body>
</html>
