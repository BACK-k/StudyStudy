<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Spring MVC2 BoardList </title>
<link rel="stylesheet" type="text/css" 
href="/spring02/resources/myLib/myStyle.css">
<script type="text/javascript">
"use strict"
// 1 검색 조건 입력한 후 버튼 클릭
//	입력된 값들을 서버로 전송요청처리 : location

// ** self.location   		
// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
// => self.location="bcrilist?currPage=?????" : 해당 요청을 서버로 전달 	
// 2) location 객체의 메서드
// => href, replace('...'), reload()

//** JS 코드 내부에서 el Tag 사용시 주의사항
//=> JS 코드의 스트링 내에서 사용한 el Tag 는 JSP 가 처리해주므로   
// 사용가능 하지만, 이 스크립트가 외부 문서인 경우에는 처리해주지 않으므로 주의
// 이 코드를 외부문서로 작성하면 "${pageMaker.makeQuery(1)}" 이 글자 그대로 적용되어 404 발생 
function searchDB(){
// => searchBtn을 클릭하는 경우 : 검색조건 입력 후 첫 Page 요청
//	    이때는 서버에 searchType, keyword 가 전달되기 이전이므로 
//	    searchType, keyword 가 없는 makeQuery 메서드사용
	self.location = 'bPageList'
/*      => 하나의 jsp 문서로 다양한 요청을 처리하기위해 쿼리스트링에 url 을 포함했기 때문에
        첫 요청에서는  makeQuery 메서드 사용할수 없음 */
//			+'${pageMaker.makeQuery(1)}'
			+'?currPage=1&rowsPerPage=5'
			+'&searchType='+document.getElementById('searchType').value
			+'&keyword='+document.getElementById('keyword').value;
} // searchDB

//2. searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear(){
	if(document.getElementById('searchType').value == 'all')
		document.getElementById('keyword').value='';
} // keywordClear

// Board Check List
function checkClear(){
// ** querySelector
// => css 선택자를 이용하여 첫번째 만난 요소 1개만 선택

// ** querySelectorAll 
// => css 선택자를 이용하여 해당하는 nodeList 를 반환
// =>  ","를 사용하면 여러 요소를 한번에 가져올 수 있음
//     querySelectorAll("#id,.class");
// => 그러므로 반복문과 이용됨.
	
	// nodeList를 반환하기 때문에 적용안됨
	// document.querySelectorAll('.clear').checked = false
	let ck = document.querySelectorAll('.clear');
	for(let i = 0; i < ck.length; i++){
		ck[i].checked = false;
	}
	// reset의 기본 이벤트를 제거
	return false;
} // checkClear

</script>
</head>
<body>
<h2> Spring MVC2 BoardList </h2>
<hr>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<hr>

<!-- 검색기능 -->
<div id="searchBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${pageMaker.cri.searchType == 'all' ? 'selected' : ''}>전체</option>
		<option value="title" ${pageMaker.cri.searchType == 'title' ? 'selected' : ''}>Title</option>
		<option value="content" ${pageMaker.cri.searchType == 'content' ? 'selected' : ''}>Content</option>
		<option value="id" ${pageMaker.cri.searchType == 'id' ? 'selected' : ''}>ID(글쓴이)</option>
		<option value="regdate" ${pageMaker.cri.searchType == 'regdate' ? 'selected' : ''}>RegDate</option>
		<option value="tc" ${pageMaker.cri.searchType == 'tc' ? 'selected' : ''}>Title or Content</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}">
	<button id="searchBtn" onclick="searchDB()">Search</button>
	
	<!-- CheckBox Test -->
	<form action="bCheckList" method="get">
		<b>ID : </b>
		
		<!-- check 선택한 값을 유지하기 위함 -->
		<c:set var="ck1" value="false" />
		<c:set var="ck2" value="false" />
		<c:set var="ck3" value="false" />
		<c:set var="ck4" value="false" />
		<c:set var="ck5" value="false" />
        <c:forEach  var="id" items="${pageMaker.cri.check}" >
	        <c:if test="${id=='simsim916'}"> <c:set var="ck1" value="true" /> </c:if>
	        <c:if test="${id=='agr4005'}"> <c:set var="ck2" value="true" /> </c:if>
	        <c:if test="${id=='bamboo7'}"> <c:set var="ck3" value="true" /> </c:if>
	        <c:if test="${id=='kso1'}"> <c:set var="ck4" value="true" /> </c:if>
	        <c:if test="${id=='admin'}"> <c:set var="ck5" value="true" /> </c:if>
        </c:forEach>
		
		<input type="checkbox" name="check" class="clear" value="simsim916" ${ck1 ? 'checked' : ''}>최문석&nbsp;
		<input type="checkbox" name="check" class="clear" value="agr4005" ${ck2 ? 'checked' : ''}>김수빈&nbsp;
		<input type="checkbox" name="check" class="clear" value="bamboo7" ${ck3 ? 'checked' : ''}>최승삼&nbsp;
		<input type="checkbox" name="check" class="clear" value="kso1" ${ck4 ? 'checked' : ''}>김수옥&nbsp;
		<input type="checkbox" name="check" class="clear" value="admin" ${ck5 ? 'checked' : ''}>관리자&nbsp;
		<input type="submit" value="Search">&nbsp;
		<input type="reset" value="Clear" onclick="return checkClear()"><br>
	</form>
	
</div>

<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table style="width:100%">
<tr bgcolor="Aquamarine">
	<th>Seq</th><th>Title</th><th>ID</th><th>RegDate</th><th>조회수</th>
</tr>
	
<c:if test="${!empty requestScope.banana }">
	<c:forEach var="b" items="${requestScope.banana}">
		<tr>
			<td>${b.seq}</td>
			<td>
			<div style="text-align:left;">
			<!-- 답글 등록 후 Title 출력전에 들여쓰기 추가 -->
			<c:if test="${b.indent>0}">
				<c:forEach  begin="1" end="${b.indent}">
					<span>&nbsp;&nbsp;</span>
				</c:forEach>
				<span sytle="color:blue;">RE: </span>
			</c:if>
			<!-- 로그인 한 경우에만 글내용을 볼 수 있게 -->
			<c:if test="${!empty loginID}">
				<a href="detail?jCode=D&seq=${b.seq}">${b.title}</a>
			</c:if>
			<c:if test="${empty loginID}">
				${b.title}
			</c:if>
			</div>
			</td>
			<td>${b.id}</td><td>${b.regdate}</td><td>${b.cnt}</td>
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
<!-- ** Paging Block ** 
   => ver01 : QueryString 수동 입력 -> 자동생성
   => ver02 : makeQuery -> searchQuery 변경
     1) FirstPage, Prev
     구버전
	<a href="bPageList?currPage=1&rowsPerPage=5">&LT;&LT;</a>&nbsp;
	<a href="bPageList?currPage=${pageMaker.spageNo-1}&rowsPerPage=5">&nbsp;&LT;</a>&nbsp;&nbsp;
-->
     <c:choose>
     	<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
<!-- ver01 : makeQuery 메서드 적용
     		<a href="bPageList${pageMaker.makeQuery(1)}">&LT;&LT;</a>&nbsp;
     		<a href="bPageList${pageMaker.makeQuery(pageMaker.spageNo-1)}">&nbsp;&LT;</a>&nbsp;&nbsp;
 -->
<!-- ver02 : searchQuery 메서드 적용 -->
     		<a href="${pageMaker.searchQuery(1)}">&LT;&LT;</a>&nbsp;
     		<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&nbsp;&LT;</a>&nbsp;&nbsp;
     	</c:when>
     	<c:otherwise>
     		<font color="Gray">&LT;&LT;&nbsp;&nbsp;&LT;&nbsp;</font>
     	</c:otherwise>
     </c:choose>
     
<!-- 2) Display PageNo
	currPage 제외한 PageNo 만 a Tag
 -->
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