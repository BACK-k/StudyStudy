<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> SpringBoot_MVC02 MemberList </title>
	<link rel="stylesheet" type="text/css" 
	href="/resources/myLib/myStyle.css">
</head>
<body>
<h2> SpringBoot_MVC02 MemberList </h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>

<table border="1" style="width:100%">
	<tr bgcolor="hotpink">
		<th>ID</th><th>NAME</th>
		<th>AGE</th><th>JNO</th><th>Info</th>
		<th>Point</th><th>BRITHDAY</th><th>추천인</th>
		<th>Image</th><th>Delete</th>
	</tr>
	
<c:if test="${!empty requestScope.banana }">
	<c:forEach var="b" items="${requestScope.banana}">
	<!-- idbList : id별 boardList
			선택된 id를 function에 전달(매개변수 활용)
			idbList('banana')
	 -->
		<tr>
		<!-- a Tag에 이벤트 적용시 책갈피 기능 활용 가능
				href : 적용하지 않은 경우 -> 이동하지 않음
				href="#id" -> id 위치로 이동, "#" -> 최상단으로 이동
				href="javascript:;" 이동하지 않음
		 -->
			<%-- <td><a href="#resultArea2" onclick="idbList('${b.id}')">${b.id}</a></td> --%>
			<td><span class="textlink" onclick="idbList('${b.id}')">${b.id}</span></td>
			<td>${b.name}</td>
			<td align="center">${b.age}</td>
			<!-- Jo 정보 Div에 출력 -->
			<td align="center">
				<span class="textlink" onmouseover="showJoDetail(event, ${b.jno})" 
										onmouseout="hideJoDetail()">${b.jno}</span>
			</td>
			<td>${b.info}</td><td>${b.point}</td><td>${b.birthday}</td>
			<td>${b.rid}</td>
			<td><img alt="myImage" width="150" height="170"
				src="/resources/uploadImages/${b.uploadfile}"></td>
				<!-- Delete 기능 추가
						선택된 id를 function에 전달(매개변수를 활용)
						결과는 성공/실패 여부만 전달 : RESTController
						성공 : deleted로 변경, onclick 해제
							이를 위해 Delete Tag를 function에서 인식할 수 있어야 함 -->
		<!--		
            ** function 에 이벤트객체 전달
            => 이벤트핸들러의 첫번째 매개변수에 event 라는 이름으로 전달함.
             => a Tag 와 span 사용시 e.target 값 비교
                -> a Tag : "javascript:;" 
                -> span  : [object HTMLSpanElement]          
         -->
			<td><span class="textlink" onclick="axiDelete(event, '${b.id}')" id="${b.id}">Delete</span></td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana }">
	<tr>
		<td colspan="10"> 정보가 없습니다 </td>
	</tr>
</c:if>
</table>
<div id="content">
	
</div>

<hr>
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>