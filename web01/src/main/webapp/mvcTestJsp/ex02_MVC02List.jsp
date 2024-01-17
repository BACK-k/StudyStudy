<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> MVC02_List_JSTL </title>
</head>
<body>
<h2> MVC02_List_JSTL </h2>
<table border="1" style="width:100%">
	<tr bgcolor="Aquamarine">
		<th>Sno</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th><th>Point</th>
	</tr>
<c:if test="${!empty requestScope.myList}">
	<c:forEach var="s" items="${requestScope.myList}" >
		<tr>
		<!-- EL Tag는 변수명 Sno만 써줘도 get 메서드를 호출함 -->
		<!-- 멤버변수명의 2번째 알파벳도 소문자 이용
		라이브러리 별로 대문자를 인식하는 방법이 다르기 때문 cName -> setCName -->
			<td>${s.sno}</td><td>${s.name}</td><td>${s.age}</td>
			<td>${s.jno}</td><td>${s.info}</td><td>${s.point}</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.myList}">
	<tr>
		<td colspan="6"><h3> 출력할 자료가 없습니다 </h3></td>
	</tr>
</c:if>
</table>
<hr>
<h3><a href='javascript:history.go(-1)'>이전으로</h3>
</body>
</html>