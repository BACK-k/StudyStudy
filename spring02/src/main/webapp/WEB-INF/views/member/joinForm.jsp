<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> JoinForm </title>
	<link rel="stylesheet" type="text/css" 
	href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<form action="join" method="post">
<table>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="id"> I D </label></td>
		<td><input type="text" name="id" id="id" placeholder="영문과 숫자로 4-10글자" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="password"> PASSWORD </label></td>
		<td><input type="password" name="password" id="password" placeholder="특수문자 필수" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="name"> Name </label></td>
		<td><input type="text" name="name" id="name" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="age"> Age </label></td>
		<td><input type="text" name="age" id="age" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="jno"> Jno </label></td>
		<td><select name="jno" id="jno">
			<option value="1">1조 : Business</option>
			<option value="2">2조 : Static</option>
			<option value="3">3조 : 칭찬해조</option>
			<option value="4">4조 : 카톡으로얘기하조</option>
			<option value="7">7조 : 칠면조</option>
		</select></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="info"> Info </label></td>
		<td><input type="text" name="info" id="info" placeholder="자기소개 & 희망사항" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="point"> Point </label></td>
		<td><input type="text" name="point" id="point" placeholder="실수 입력" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="birthday"> Birthday </label></td>
		<td><input type="date" name="birthday" id="birthday"></td>
	</tr>
	<tr height="40">
		<td bgcolor="MediumPurple"><label for="rid"> 추천인 </label></td>
		<td><input type="text" name="rid" id="rid" size="20"></td>
	</tr>
	
	<tr>
		<td></td>
		<td>
		<input type="submit" value="가입">&nbsp;&nbsp;
		<input type="reset" value="취소">
		</td>
	</tr>
</table>	
</form>
<hr>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}	
</c:if>

<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>