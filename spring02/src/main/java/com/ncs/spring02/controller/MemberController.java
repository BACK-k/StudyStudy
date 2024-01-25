package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;

//IOC/DI 적용 ( @Component의 세분화 )
//스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @을 추가함
//@Controller :사용자 요청을 제어하는 Controller 클래스
//	원래는 Controller로 인식시키기 위해서 Controller를 Interface로 구현해놨음
//	DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌
//	 -> Interface Controller의 구현의무 없어짐
//		이로인해 메서드 handleRequest() 의 오버라이딩 의무가 없어짐
//		따라서 메서드이름, 매개변수, 리턴타입이 자유로워짐
//		 그러나 ModelAndView, String, void 타입만 사용 가능
//		그리고 메서드, 클래스 단위로 매핑해주는 @RequestMapping 사용가능
//		그러므로 하나의 컨트롤러 클래스에 여러개의 매핑메서드의 구현이 가능해짐
//		그래서 주로 DataBase의 테이블(Entity) 단위로 작성을 한다(MemberController.java)
//@Service : 비즈니스로직을 담당하는 Service 클래스
//@Repository : DB 연동을 담당하는 DAO 클래스
//	DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

//DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌
@Controller
// member로 시작하는 요청들이 모두 여기로 오게함
@RequestMapping(value = "/member")
public class MemberController {
	@Autowired(required = false)
	MemberService service;

// Login Form 출력
// ver01 : return String
//	@RequestMapping(value = { "/loginForm" }, method = RequestMethod.GET)
//	public String loginForm(Model model) {
//		return "member/loginForm";
//	} // loginForm

// ver02 : return void
	// viewName이 생략됨 -> 요청명과 동일한 viewName을 자동으로 찾음
	// /WEB-INF/views/member/loginFom.jsp
	@RequestMapping(value = { "/loginForm" }, method = RequestMethod.GET)
	public void loginForm() {
	} // loginForm

	// login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, MemberDTO dto) {
//		String id = request.getParameter("id");
//		dto.setID(id);
		// 맵핑 메서드에 인자로 정의되어있는 객체와
		// 동일한 컬럼명의 값은 자동으로 담아준다

		// 1 요청분석
		// => requst 로 전달되는 id, password 처리:
		// 매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		// ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
		// => 전달된 password 보관
		String password = dto.getPassword();
		String uri = "redirect:/home"; // 성공시 uri

		// 2 서비스 & 결과 처리
		// id확인 -> 존재하면 Password 확인
		// 성공 : id, name은 session에 보관 후 home으로 이동
		// 실패 : 재로그인 유도
		dto = service.selectOne(dto.getId());

		if (dto != null && dto.getPassword().equals(password)) {
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		} else {
			uri = "memeber/loginForm";
			model.addAttribute("message", " 아이디, 비밀번호를 확인해주세요 ");
		} // if
		return uri;
	} // login

	// logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/";
	} // logout

	// Member Detail
	// 단일 Parameter의 경우 @RequestParam("...")을 활용
	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
	// String jCode = request.getParameter("jCode")와 동일
	// 단, 해당하는 Parameter가 없으면 400 오류 발생
	// 그러므로 detail 요청에도 ?jCode=D 를 추가
	public String datail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		// 1 요청분석
		// id를 session에서 get
		String id = (String) session.getAttribute("loginID");
		String uri = "member/memberDetail";

		// update 요청 확인 후 uri 수정
		if ("U".equals(jCode)) {
			uri = "member/updateForm";
		}

		// 2 Service 처리
		model.addAttribute("apple", service.selectOne(id));
		return uri;
	} // detail

	// Member List
	@RequestMapping(value = { "/memberList" }, method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	} // mList

	// JoinForm
	@RequestMapping(value = { "/joinForm" }, method = RequestMethod.GET)
	public void joinForm() {
	} // JoinForm

	// Join
	@RequestMapping(value = { "/join" }, method = RequestMethod.POST)
	public String join(Model model, MemberDTO dto) {
		// 1 요청 분석
		// 이전 : 한글처리, request 값 -> dto에 set
		// 스프링 : 한글은 filter, request 처리는 parameter
		String uri = "member/loginForm";

		// 2 Service
		if (service.insert(dto) > 0) {
			model.addAttribute("message", " 회원가입 성공 ");
		} else {
			uri = "member/joinForm";
			model.addAttribute("message", " 회원가입 실패 ");
		}
		return uri;
	} // Join

	// Update
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String update(HttpSession session, Model model, MemberDTO dto) {
		// 1 요청 분석
		// 성공 : memberDetail
		// 실패 : updateForm
		// 출력하려면 dto 객체의 값("apple")이 필요하므로 보관
		String uri = "member/memberDetail";
		model.addAttribute("apple", dto);

		// 2 Service
		if (service.update(dto) > 0) {
			model.addAttribute("message", " 회원정보수정 성공 ");
			// name을 수정한 경우를 대비해 session의 loginName을 수정
			session.setAttribute("loginName", dto.getName());
		} else {
			uri = "member/updateForm";
			model.addAttribute("message", " 회원정보수정 실패 ");
		}
		return uri;
	} // Update

	// Delete
	@RequestMapping(value = { "/delete" }, method = RequestMethod.GET)
	public String datail(HttpSession session, Model model) {
		// 1 요청분석
		// id를 session에서 get
		// id 삭제와 session 무력화
		String id = (String) session.getAttribute("loginID");
		String uri = "/home";

		// 2 Service 처리
		if (service.delete(id) > 0) {
			model.addAttribute("message", " 탈퇴 성공 ");
			session.invalidate();
		} else {
			model.addAttribute("message", " 탈퇴 실패 ");
		}
		return uri;
	} // Delete
}
// class
