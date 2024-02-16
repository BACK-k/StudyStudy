<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Spring MVC2 MemberList </title>
<link rel="stylesheet" type="text/css" 
href="/spring02/resources/myLib/myStyle.css">
<script type="text/javascript">
"use strict"
function searchDB(){
	self.location = 'mPageList'
			+'?currPage=1&rowsPerPage=5'
			+'&searchType='+document.getElementById('searchType').value
			+'&keyword='+document.getElementById('keyword').value;
} // searchDB

function keywordClear(){
	if(document.getElementById('searchType').value == 'all')
		document.getElementById('keyword').value='';
} // keywordClear

// Member Check List
function checkClear(){
	let ck = document.querySelectorAll('.clear');
	for(let i = 0; i < ck.length; i++){
		ck[i].checked = false;
	}
	return false;
} // checkClear

</script>
</head>
<body>
<h2> Spring MVC2 MemberList </h2>
<hr>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br>
</c:if>
<hr>

<!-- 검색기능 -->
<div id="searchBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${pageMaker.cri.searchType == 'all' ? 'selected' : ''}>전체</option>
		<option value="id" ${pageMaker.cri.searchType == 'id' ? 'selected' : ''}>ID</option>
		<option value="name" ${pageMaker.cri.searchType == 'name' ? 'selected' : ''}>Name</option>
		<option value="age" ${pageMaker.cri.searchType == 'age' ? 'selected' : ''}>Age</option>
		<option value="birthday" ${pageMaker.cri.searchType == 'birthday' ? 'selected' : ''}>Birthday</option>
		<option value="info" ${pageMaker.cri.searchType == 'info' ? 'selected' : ''}>Info</option>
		<option value="rid" ${pageMaker.cri.searchType == 'rid' ? 'selected' : ''}>추천인</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}">
	<button id="searchBtn" onclick="searchDB()">Search</button>
	
	<!-- CheckBox Test -->
	<form action="mCheckList" method="get">
		<b>ID : </b>
		
		<!-- check 선택한 값을 유지하기 위함 -->
		<c:set var="ck1" value="false" />
		<c:set var="ck2" value="false" />
		<c:set var="ck3" value="false" />
		<c:set var="ck4" value="false" />
		<c:set var="ck5" value="false" />
        <c:forEach  var="id" items="${pageMaker.cri.check}" >
	        <c:if test="${jno=='1'}"> <c:set var="ck1" value="true" /> </c:if>
	        <c:if test="${jno=='2'}"> <c:set var="ck2" value="true" /> </c:if>
	        <c:if test="${jno=='3'}"> <c:set var="ck3" value="true" /> </c:if>
	        <c:if test="${jno=='4'}"> <c:set var="ck4" value="true" /> </c:if>
	        <c:if test="${jno=='7'}"> <c:set var="ck5" value="true" /> </c:if>
        </c:forEach>
		
		<!-- jo -->
		<input type="checkbox" name="check" class="clear" value="1" ${ck1 ? 'checked' : ''}>Business&nbsp;
		<input type="checkbox" name="check" class="clear" value="2" ${ck2 ? 'checked' : ''}>static&nbsp;
		<input type="checkbox" name="check" class="clear" value="3" ${ck3 ? 'checked' : ''}>칭찬해조&nbsp;
		<input type="checkbox" name="check" class="clear" value="4" ${ck4 ? 'checked' : ''}>카톡으로얘기하조&nbsp;
		<input type="checkbox" name="check" class="clear" value="7" ${ck5 ? 'checked' : ''}>칠면조&nbsp;
		<input type="submit" value="Search">&nbsp;
		<input type="reset" value="Clear" onclick="return checkClear()"><br>
	</form>
	
</div>

<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table style="width:100%">
	<tr bgcolor="Aquamarine">
		<th>ID</th><!-- <th>PASSWORD</th> --><th>NAME</th>
		<th>AGE</th><th>JNO</th><th>Info</th>
		<th>Point</th><th>BRITHDAY</th><th>추천인</th>
		<th>Image</th>
	</tr>
	
<c:if test="${!empty requestScope.banana }">
	<c:forEach var="b" items="${requestScope.banana}">
		<tr>
			<td>${b.id}</td><%-- <td>${b.password}</td> --%><td>${b.name}</td>
			<td>${b.age}</td><td>${b.jno}</td><td>${b.info}</td>
			<td>${b.point}</td><td>${b.birthday}</td><td>${b.rid}</td>
			<td><img alt="myImage" width="150" height="170"
				src="/spring02/resources/uploadImages/${b.uploadfile}"></td>
		</tr>
	</c:forEach>
</c:if>

<c:if test="${empty requestScope.banana }">
	<tr>
		<td colspan="5"> 정보가 없습니다 </td>
	</tr>
</c:if>
</table>
</div>
<hr>

<div align="center">
<!-- 1) Prev, FirstPage -->
     <c:choose>
     	<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
     		<a href="${pageMaker.searchQuery(1)}">&LT;&LT;</a>&nbsp;
     		<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&nbsp;&LT;</a>&nbsp;&nbsp;
     	</c:when>
     	<c:otherwise>
     		<font color="Gray">&LT;&LT;&nbsp;&nbsp;&LT;&nbsp;</font>
     	</c:otherwise>
     </c:choose>
     
<!-- 2) Display PageNo -->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage}">
			<a href="${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage -->
     <c:choose>
     	<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
     		&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;&nbsp;</a>
     		&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo)}">&GT;&GT;</a>
     	</c:when>
     	<c:otherwise>
     		<font color="Gray">&nbsp;&GT;&nbsp;&nbsp;&GT;&GT;</font>
     	</c:otherwise>
     </c:choose>
</div>
<hr>

<hr>
<c:if test="${!empty sessionScope.loginID}">
&nbsp;<a href="boardInsert">글쓰기</a>
</c:if>
<br>

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>