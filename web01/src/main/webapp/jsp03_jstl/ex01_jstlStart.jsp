<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 라이브러리 인식할 수 있게 선언, prefix : 접두어선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> JSTL Start </title>
</head>
<body>
<h2> JSTL Start </h2>
<pre><b>
=> Jstl Library 를 정의 (현재문서_Page 가 인식할 수 있도록)
   디렉티브 taglib 에 uri=".." prefix=".."
   
1 출력 : out Tag
=> Java의 out 객체, 표현식, EL 역할
<c:out value=""></c:out>
<!-- 내용이 필요없는 경우 열기와 닫기를 하나의 태그 안에서 해결 -->
<c:out value=" Hello JSTL! 안녕 " />

2 변수 정의 : set
<c:set value="홍길동" var="name" />
<c:set value="22" var="age" />

3 변수 출력 : out Tag, EL
=> JSTL의 out Tag
* name = <c:out value="${name}" />
* age = <c:out value="${age}" />

=> EL
* name = ${name}
* age = ${age}
* age*100 = ${age*100}

=> Java는 JSTL 변수와 호환되는지 Test : 불가능
<%-- * name = <%=name%> --%>

4 연산적용
<c:set value="${age+age}" var="add"/>
\${add} = ${add}
<c:set value="${name==age}" var="bool" />
\${bool} = ${bool}
<c:set value="${ age>add ? age : add }" var="max" />
\${max} = ${max}

5 변수삭제 : remove
<c:remove var="add" />
\${empty_add} = ${empty add}
\${empty_age} = ${empty age}
<!-- 정의하지 않은 변수 삭제 : 오류는 발생하지 않음 -->
<c:remove var="see" />

6 우선순위 비교
같은 페이지 안에서 사용되는 JSTL 변수와 pageScope의 Attribute 비교
나중에 정의한 값이 우선시된다
<% // pageScope에 Attribute를 정의 후 TEST
	pageContext.setAttribute("name", "그린컴");
%>

* Test1. name을 정의한 순서 : set -> page_setAttribute
\${name} = ${name}

* Test2. set의 name을 재정의 : set -> set 
<c:set value="new_홍길동" var="name" />
\${name} = ${name} : 나중에 정의한 값이 우선시됨
</b></pre>
</body>
</html>