<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> EL 기본사항 </title>
</head>
<body>
<h2> EL 기본사항 </h2>
<%
// 비교하기 위한 Java
String name = " 홍길동 ";
%>
<pre>
EL : Expression Language, 표현언어
편리한 값(Value)의 출력과 사용

1 값(변수의 값)의 출력 비교
=> Java 표현식 : <%=name%>
=> Java out객체 : <% out.print("Java out객체 => "+name); %>
=> EL 출력 : ${" Hello EL, 표현언어 "}
<!-- 직접적으로 사용은 불가능해 500 error 발생 --> 
	<%-- Java변수 출력 : ${"EL로 Java변수 출력 : "+name} --%>
<!-- \를 넣어 컨텐츠를 그대로 출력함 
	EL 내부에서는 Java 변수 사용불가능
	JSTL과 병행해서 사용된다 -->
	Java변수 출력 : \${"EL로 Java변수 출력 : "+name}
<hr><b>
2 EL TEST
EL 자료형
정수형 : ${123}
실수형 : ${10.123}
문자형 : ${"안녕하세요"}
논리형 : ${true}
null : ${null}
	
EL 연산
산술연산
\${5+2} => ${5+2}
\${5-2} => ${5-2}
\${5*2} => ${5*2}
\${5/2} => ${5/2}
\${5%2} => ${5%2}
-> 나누기 결과는 실수형

관계(비교) 연산
<!-- >, <, >=, <=, ==, != -->
gt : greater than / lt : less than
ge: greater equal / le: less equal
eq: equal, == / ne: not equal, !=

\${5>2} = ${5>2}
\${5gt2} = ${5 gt 2}
\${5<2} = ${5<2}
\${5lt2} = ${5 lt 2}

\${5>=2} = ${5>=2}
\${5ge2} = ${5 ge 2}
\${5<=2} = ${5<=2}
\${5le2} = ${5 le 2}

\${5==2} = ${5==2}
\${5eq2} = ${5 eq 2}
\${5!=2} = ${5!=2}
<%-- 에디터 상에서는 오류지만 실행은 잘됨
\${5ne2} = ${5 ne 2} --%>

논리(집합) 연산 : &&, ||
\${5 > 2 && 10 > 20} = ${5 > 2 && 10> 20}
\${5 > 2 || 10 > 20} = ${5 > 2 || 10> 20}

조건(삼항)식
\${5 > 2 ? 5 : 2} = ${5 > 2 ? 5 : 2}
\${5 > 2 ? '오' : '이'} = ${5 > 2 ? '오' : '이'}

<hr>
3 기타
Java 변수
	Java 표현식 : <%=name%>
	<!-- 자바변수는 출력하지 않음, JSTL로 정의한 변수는 출력 
	단독으로 사용시 에러가 발생하지는 않는다, name의 값이 없음을 확인 -->
	EL \${name} : ${name}
   
    <!-- empty : 검사할 객체가 비어있는지 확인, 비어있으면 true 
            list, map 타입의 객체가 값이 있는지 없는지 구분해줌  
    EL 에 자바변수는 직접 값을 전달하지 못함
     (jsp에서 자바코드가 완전 분리됨을 목표로 하기때문에 자바변수를 사용할 필요는 없으므로)  
    EL 에 변수명이 오면 JSTL로 정의한 변수 또는 속성(Attribute) 의 이름으로 인식함 -->
	\${empty_name} : ${empty name}

Request 객체의 Parameter 처리
	<!-- request 객체의 Parameter를 전달하는 el의 내부객체 제공 : param
	퀴리스트링으로 id 지정 전.후 Test : ~/web01/jsp02_el/ex01_elStart.jsp?id=banana -->
	자바 표현식으로 출력 : <%=request.getParameter("id")%>
	EL 존재여부 확인 : \${empty_param.id} : ${empty param.id}
	EL 출력 : \${param.id} : ${param.id}
		\${param["id"]} : ${param["id"]}
		
Scope에 대한 접근 - getAttribute 처리
	ex02_getAttribute에서
	
</b></pre>

</body>
</html>