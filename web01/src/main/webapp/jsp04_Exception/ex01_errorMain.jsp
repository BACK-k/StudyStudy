<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Exception Main </title>
</head>
<body>
<pre><h3>
** Exception Main **
3) Exception Type 별로: web.xml
	2),3) web.xml에 설정하는 경우에는 프로젝트 전체에 적용

<hr>
1) NullPointerException : Exception Type
* Country : <%=request.getParameter("country").toUpperCase() %>

2) NumberFormatException : 상태코드 500
* Number : <%=Integer.parseInt(request.getParameter("country")) %>

3) ArithmeticException : Exception Type
   => by Zero
   123/0 = <%=123/0 %>
   
   
</h3></pre>

</body>
</html>