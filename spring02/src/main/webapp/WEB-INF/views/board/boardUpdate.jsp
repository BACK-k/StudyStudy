<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> updateForm </title>
</head>
<body>
<form action="update" method="post">
<table>
	<tr height="40">
		<td bgcolor="Plum"><label for="seq"> Seq </label></td>
		<!-- readonly 읽기전용 설정, 서버로 데이터 전송
		disabled : 서버로 전송되지 않음 -->
		<td><input type="text" name="seq" id="seq" value="${requestScope.apple.seq}" readonly size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="title"> Title </label></td>
		<td><input type="text" name="title" id="title" value="${requestScope.apple.title}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="content"> Content </label></td>
		<td><input type="text" name="content" id="content" value="${requestScope.apple.content}" size="20"></td>
	</tr>
	<tr>
		<td></td>
		<td>
		<input type="submit" value="변경">&nbsp;&nbsp;
		<input type="reset" value="취소">
		</td>
	</tr>
</table>	
</form>
<hr>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}	
</c:if>

<br>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>