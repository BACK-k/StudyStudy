// ** Ajax_REST API, Axios Test **
// => Axios 메서드형식 적용
// => 1. List 출력
//   - axiMList : MemberController, Page response(axMemberList.jsp)
//   - idbList(id별 boardList) : RTestController, List_Data response 
// => 2. 반복문에 이벤트 적용하기
//   - Delete, JoDetail
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

"use strict"

// 1. List 출력 
// 1.1) Page response
// response를 resultArea1에 출력하기
// 요청명 : /member/aximlist
// response : axMemberList.jsp
function axiMList(){
	let url = "/member/aximlist";
	
	axios.get(url
	).then(response => {
		console.log(" response 성공 ");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		alert(" response 실패 => "+err.message);
	});
	document.getElementById('resultArea2').innerHTML = '';
}

// 1.2 idbList(id별 boardList)
// RESTController, PathVariable으로 Param(id) 처리, List_Data response
// Server : service, SQL 구문
// request : id를 path로 전송 "/rest/idblist/banana"
// response(결과)
//	성공 : 반복문을 이용해 Table로 List 출력문 완성, resultArea2에 출력
//	출력 자료의 유무 : server에서 status로 없으면 502로 처리
//	실패 : resultArea2 clear, alert로 에러메시지 출력
function idbList(id){
	let url = "/rest/idblist/"+id;
	
	axios.get(url
	).then(response => {
		alert(" 성공 => resultArea2에 List 작성");
		console.log(" result List Data => "+response.data);
		let listData = response.data;
		let resultHtml = 
			`
			<table style="width:100%">
				<tr bgcolor="Aquamarine">
					<th>Seq</th><th>Title</th><th>ID</th><th>RegDate</th><th>조회수</th>
				</tr>`;
		// 반복문 적용
		for(let b of listData){
			resultHtml += `
				<tr>
					<td>${b.seq}</td><td>${b.title}</td>
					<td>${b.id}</td><td>${b.regdate}</td><td>${b.cnt}</td>
				</tr>`;
		} // for
		resultHtml += `</Table>`;
		
		document.getElementById('resultArea2').innerHTML = resultHtml;
		
	}). catch(err => {
		// response의 status 값이 502면 출력자료없음
		if(err.response.status == '502'){
		document.getElementById('resultArea2').innerHTML = err.response.data;
		} else {
		document.getElementById('resultArea2').innerHTML = "";
		alert(" 시스템 오류, 잠시후 다시 하세요 => "+err.message);
		}
	});
} // idbList

// 2.2 axiDelete
// 요청명 : "/rest/axidelete/{id}" PathVariable 적용
// response 결과는 성공/실패 여부만 전달 : RESTController
// 성공 : deleted로 변경, onclick 해제

// event 객체 적용하기
// document.getElementById(id) 대신 e.target로 이벤트 발생 대상 객체인식 가능
function axiDelete(e, id){
	let url = "/rest/axidelete/"+id;

	axios.delete(url
	).then(response => {
		alert(response.data);
		// 삭제 성공
		// Delete -> Deleted, Gray color, Bold
		// onclick 이벤트 제거 : removeAttribute('onclick')
		// style 제거 : classList.remove('textlink')
		
		// e.target 적용
//		document.getElementById(id).innerHTML="Deleted";
		e.target.innerHTML="Deleted";
		document.getElementById(id).style.color="Gray";
		document.getElementById(id).style.fontWeight="bold";
		document.getElementById(id).classList.remove('textlink');
		document.getElementById(id).removeAttribute('onclick');
	}).catch(err => {
		// 내부에서 해당하는 데이터를 못찾은 경우
		if(err.response.status == '502'){
			alert(err.response.data);
		} else {
			alert(" 시스템 오류, 잠시후 다시 하세요 => "+err.message);
		}
	});
} // axiDelete

// 2.3 JoDetail
// 2.3.1 MouseOver : showJoDetail
// jno에 mouseover하면 JoDetail content Div에 출력(마우스 포인터 위치에)
// request : axios, get, RESTController에 "/joDetail"
// response : 성공시 JoDTO 객체
function showJoDetail(e, jno){
	// 마우스 포인터의 위치 확인
	// 이벤트 객체 활용
   //     - event객체 (이벤트핸들러 첫번째 매개변수) 가 제공
   //     - event객체 프로퍼티: type, target, preventDefault() 등 (JS 9장_Event.pptx 28p)   
   //    - e.pageX, e.pageY : 전체 Page 기준
   //     - e.clientX, e.clientY : 보여지는 화면 기준-> page Scroll 시에 불편함
	console.log(` e.pageX = ${e.pageX}, e.pageY = ${e.pageY} `);
	console.log(` e.clientX = ${e.clientX}, e.clientY = ${e.clientY} `);
	
	let url = "/rest/jodetail/"+jno;
	let mletf = e.pageX + 20;
	let mtop = e.pageY;
	
	axios.get(url
	).then(response => {
		console.log(` response 성공 => ${response.data}`);
	
		// ** JSON.stringify 적용 비교
      let jj =JSON.stringify(response.data);   
      // => Object -> JSON : Data를 나열해줌 
      // => JS 객체포맷을 JSON 포맷화 하면 key:value 형태로 나열해줌
      //    (즉, 하나의 긴문자열, 문자Type 이됨)
      //    console.log 로 response.data 의 내용을 확인할때 사용하면 편리함.  
      console.log(`** response 성공: JSON포맷 => ${jj}`);
		
		let jo = response.data;
		console.log(` Data : jo.jno => ${jo.jno}`);
		
		let resultHtml = 
			`
			<table style="width:100%">
				<tr bgcolor="purple">
					<th>JNO</th><th>JNAME</th><th>CAPTAIN</th>
					<th>PROJECT</th><th>SLOGAN</th>
				</tr>
				<tr>
					<td>${jo.jno}</td><td>${jo.jname}</td><td>${jo.captain}</td>
					<td>${jo.project}</td><td>${jo.slogan}</td>
				</tr>
			</Table>`;
		
		document.getElementById('content').innerHTML = resultHtml;
		document.getElementById('content').style.display = 'block';
		document.getElementById('content').style.left = mletf + "px";
		document.getElementById('content').style.top = mtop + "px";
		document.getElementById('content').style.visibility = 'visible';
     // ** for 간편출력 : of, in
      // => in: undifined 는 통과하고, 배열(index Return), 객체(속성명 Return)
      // => of: undifined 까지 모두출력 (순차출력과 동일), value 를 return, 
      //        ES6 에 for ~ in 의 단점을 보완 개선하여 추가됨.
      //        일반 객체에는 적용안되지만, (오류발생, 개발자모드로 확인가능)
      //         Array, String, Map, Set, function의 매개변수 객체 와
      //        이터러블 규약을 따르는 이터러블 객체 (Iterable Object) 는 적용됨
      // => 이터러블 규약
      //      내부에 Symbol.iterator (줄여서 @@iterator로 표현하기도함) 메서드가 구현되어 있어야 한다는 규약 

		
	}).catch(err => {
		if(err.response.status == '502'){
			document.getElementById('resultArea2').innerHTML = err.response.data;
		} else {
			document.getElementById('resultArea2').innerHTML = "";
			alert(" 시스템 오류, 잠시후 다시 하세요 => "+err.message);
		}
	})
} // showJoDetail
// 2.3.2 MouseOut : hideJoDetail
// 화면에 표시되었던 content Div가 사라지면 됨
function hideJoDetail(){
		document.getElementById('content').style.visibility = 'hidden';
} // hideJoDetail


// ** Ajax Member_PageList *********************
// => axiMList 에 Paging + 검색기능 추가
// => 검색조건 & Paging , Ajax 구현
//    -> 입력된 값들을 서버로 전송요청: axios
//   -> url 완성후 axios 호출

// => 1) 검색조건 입력 후 버튼클릭
//   -> jsp  문서내무의 script 구문을 외부문서로 작성 : EL Tag 적용안됨
//    ${pageMaker.makeQuery(1)} -> ?currPage=1&rowsPerPage=5 
function searchDB(){
	// 요청명 
	let url = 'axmcri'
			+'?currPage=1&rowsPerPage=5'
			+'&searchType='+document.getElementById('searchType').value
			+'&keyword='+document.getElementById('keyword').value;
		// axios 호출
		axiMListCri(url);
} // searchDB

// => 2) searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear(){
	if(document.getElementById('searchType').value == 'all')
		document.getElementById('keyword').value='';
} // keywordClear

// => 3) axios Code
function axiMListCri(url){
	url = "/member/" + url;
	alert(`axiMListCri url=${url}`);
	axios.get(url
	).then(response => {
		console.log(" response 성공 ");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		document.getElementById('resultArea1').innerHTML = " axiMListCri 실패 => "+err.message;
	});
	document.getElementById('resultArea2').innerHTML = "";
} // axiMListCri

// => 4) Check 검색기능 추가
function axiMListCheck(){
	// 첫 요청
	let checkAll = document.querySelectorAll(".check");
	let checkData = "";
/*	for(let i = 0; i < checkAll.length; i++){
		if(checkAll[i].checked){
			checkData += "&check=" + checkAll[i].value;
		}		
	}*/
	
	checkAll.forEach(check =>{
		if(check.checked){
			checkData += "&check="+check.value;
		}
	})
	
	let url = 'axmcheck'
		+'?currPage=1&rowsPerPage=5'
		+checkData;	
	axiMListCri(url);
} // axiMListCheck
