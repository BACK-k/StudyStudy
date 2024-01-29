<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Board Insert </title>
	<link rel="stylesheet" type="text/css" 
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<form action="insert" method="get">
<table>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="title"> Title </label></td>
		<td><input type="text" name="title" id="title"></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="ctt"> Content </label></td>
		<td><input type="text" name="content" id="ctt" size="20"></td>
	</tr>
	
	<tr>
		<td></td>
		<td>
		<input type="submit" value="추가">&nbsp;&nbsp;
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