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
<form action="/web02/mupdate" method="post">
<table>
	<tr height="40">
		<td bgcolor="Plum"><label for="id"> I D </label></td>
		<!-- readonly 읽기전용 설정 -->
		<td><input type="text" name="id" id="id" value="${requestScope.apple.id}" readonly size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="password"> PASSWORD </label></td>
		<td><input type="password" name="password" id="password" value="${requestScope.apple.password}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="name"> Name </label></td>
		<td><input type="text" name="name" id="name" value="${requestScope.apple.name}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="age"> Age </label></td>
		<td><input type="text" name="age" id="age" value="${requestScope.apple.age}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="jno"> Jno </label></td>
		<td><select name="jno" id="jno">
		<!-- requestScope를 사용해 직접 찾으라고 명령 없어도 apple가 하나뿐이라 상관없음 -->
			<option value="1" selected="${request.apple.jno == 1 ? "selected" : ""}">1조 : Business</option>
			<option value="2" selected="${request.apple.jno == 2 ? "selected" : ""}">2조 : Static</option>
			<option value="3" selected="${request.apple.jno == 3 ? "selected" : ""}">3조 : 칭찬해조</option>
			<option value="4" selected="${request.apple.jno == 4 ? "selected" : ""}">4조 : 카톡으로얘기하조</option>
			<option value="7" selected="${request.apple.jno == 7 ? "selected" : ""}">7조 : 칠면조</option>
		</select></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="info"> Info </label></td>
		<td><input type="text" name="info" id="info" value="${requestScope.apple.info}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="point"> Point </label></td>
		<td><input type="text" name="point" id="point" value="${requestScope.apple.point}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="birthday"> Birthday </label></td>
		<td><input type="date" name="birthday" id="birthday" value="${requestScope.apple.birthday}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="Plum"><label for="rid"> 추천인 </label></td>
		<td><input type="text" name="rid" id="rid" value="${requestScope.apple.rid}" size="20"></td>
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

<hr>
&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>