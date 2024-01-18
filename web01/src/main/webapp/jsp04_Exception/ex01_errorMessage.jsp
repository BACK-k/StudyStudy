<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- exception 객체를 사용하기 위해 설정 -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Error Message Page </title>
</head>
<body>
<pre><h3>
  Error Message Page
<!-- 	exception객체
   - 전달받은 예외객체를 표현하는 객체
   - page 디렉티브의 isErrorPage="true" 일때만 사용 가능
 -->
->	서비스 처리과정에서 <%=exception.getClass().getSimpleName() %>
	오류가 발생
	잠시후 다시 시도해주세요

<!-- exception의 종류에 따라 나오지 않는 경우도 있다 -->
=> Exception Type : <%=exception.getClass().getName() %>
=> Exception toString : <%=exception.toString() %>
=> Exception getMessage : <%=exception.getMessage() %>

</h3></pre>
</body>
</html>