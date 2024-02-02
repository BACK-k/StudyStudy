package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//** Spring 의 redirect ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** RedirectAttributes
//=> Redirect 할 때 파라메터를 쉽게 전달할 수 있도록 지원하며,
// addAttribute, addFlashAttribute, getFlashAttribute 등의 메서드가 제공됨.
//=> addAttribute
// - url에 퀴리스트링으로 파라메터가 붙어 전달됨. 
// - 그렇기 때문에 전달된 페이지에서 파라메터가 노출됨.

//=> addFlashAttribute
// - Redirect 동작이 수행되기 전에 Session에 값이 저장되고 전달 후 소멸된다.
// - Session을 선언해서 집어넣고 사용 후 지워주는 수고를 덜어주고,
//    -> url에 퀴리스트링으로 붙지 않기때문에 깨끗하고 f5(새로고침)에 영향을 주지않음.  
//    -> 주의사항 
//       받는쪽 매핑메서드의 매개변수로 parameter를 전달받는 VO가 정의되어 있으면
//       이 VO 생성과 관련된 500 발생 하므로 주의한다.
//      ( Test : JoController 의 jupdate 성공시 redirect:jdetail )
//      단, VO로 받지 않는 경우에는 url에 붙여 전달하면서 addFlashAttribute 사용가능함        

//=> getFlashAttribute
//    - insert 성공 후 redirect:jlist 에서 Test (JoController, 결과는 null)
//    - 컨트롤러에서 addFlashAttribute 가 session에 보관한 값을 꺼내는것은 좀더 확인이 필요함 

//** redirect 로 한글 parameter 전달시 한글깨짐
//=> 한글깨짐이 발생하는경우 사용함.
//=> url 파라메터 로 전달되는 한글값 을 위한 encoding
//    - String message = URLEncoder.encode("~~ member 가 없네용 ~~", "UTF-8");
//      mv.setViewName("redirect:mlist?message="+message);  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** Model & ModelAndView **

//=> Model(interface)
//-> controller처리 후 데이터(Model) 을 담아서 반환 
//-> 구현클래스 : ConcurrentModel, ExtendedModelMap 등.
//-> 아래의 매핑 메서드들 처럼, ModelAndView 보다 심플한 코드작성 가능하므로 많이사용됨. 
// mv.setViewName("~~~~~") 하지않고 viewName 을 return 

//=> ModelAndView (class)
//-> controller처리 후 데이터(Model) 와 viewName 을 담아서 반환
//-> Object -> ModelAndView
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** @RequestMapping
//=> DefaultAnnotationHandlerMapping에서 컨트롤러를 선택할 때 대표적으로 사용하는 애노테이션. 
//=> DefaultAnnotationHandlerMapping은 클래스와 메서드에 붙은 @RequestMapping 애노테이션 정보를 결합해 최종 매핑정보를 생성한다.
//=> 기본적인 결합 방법은 클래스 레벨의 @RequestMapping을 기준으로 삼고, 
//  메서드 레벨의 @RequestMapping으로 세분화하는 방식으로 사용된다.

//** @RequestMapping 특징
//=> url 당 하나의 컨트롤러에 매핑되던 다른 핸들러 매핑과 달리 메서드 단위까지 세분화하여 적용할 수 있으며,
// url 뿐 아니라 파라미터, 헤더 등 더욱 넓은 범위를 적용할 수 있다. 
//=> 요청과 매핑메서드 1:1 mapping 
//=> value="/mlist" 
// : 이때 호출되는 메서드명과 동일하면 value 생략가능 그러나 value 생략시 404 (확인필요함)
// : 해당 메서드 내에서 mv.setViewName("...."); 을 생략 
//  또는 아래의 메서드를 사용하는 경우에는 void 로 작성 (view 를 return 하지않음) 하는 경우
//   요청명을 viewName 으로 인식 즉, mv.setViewName("mlist") 으로 처리함.
//  또는 return "mlist" ( 즉, mlist.jsp 를 viewName으로 인식 )

//** @RequestMapping 속성
//=> value : URL 패턴 ( 와일드카드 * 사용 가능 )
//  @RequestMapping(value="/post")
//  @RequestMapping(value="/post.*")
//  @RequestMapping(value="/post/**/comment")
//  @RequestMapping(value={"/post", "/P"}) : 다중매핑 가능

//=> method 
// @RequestMapping(value="/post", method=RequestMethod.GET)
// -> url이 /post인 요청 중 GET 메서드인 경우 호출됨
// @RequestMapping(value="/post", method=RequestMethod.POST)
// -> url이 /post인 요청 중 POST 메서드인 경우 호출됨
//    GET, POST, PUT, DELETE, OPTIONS, TRACE 총 7개의 HTTP 메서드가 정의되어 있음.
//    ( 이들은 아래 @GetMapping ... 등으로도 좀더 간편하게 사용가능
//      그러나 이들은 메서드 레벨에만 적용가능    )  

//=> params : 요청 파라미터와 값으로도 구분 가능함.
// @RequestMapping(value="/post", params="useYn=Y")
// -> /post?useYn=Y 일 경우 호출됨
// @RequestMapping(value="/post", params="useYn!=Y")
// ->  not equal도 가능
// @RequestMapping(value="/post", parmas="useYn")
// > 값에 상관없이 파라미터에 useYn이 있을 경우 호출됨
// @RequestMapping(value="/post", params="!useYn")
// > 파라미터에 useYn이 없어야 호출됨
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** Lombok 지원 로그메시지  
//=> @Log4j Test
// -> dependency 필요함 (pom.xml 확인)
// -> 로깅레벨 단계 준수함 ( log4j.xml 의 아래 logger Tag 의 level 확인)
//    TRACE > DEBUG > INFO > WARN > ERROR > FATAL(치명적인)
//    <logger name="com.ncs.green">
//       <level value="info" />
//    </logger>   

// -> Logger 사용과의 차이점 : "{}" 지원안됨 , 호출명 log
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌
@Controller
// member로 시작하는 요청들이 모두 여기로 오게함
@RequestMapping(value = "/member")
public class MemberController {
	@Autowired(required = false)
	MemberService service;
	@Autowired
	PasswordEncoder passwordEncoder;
	// = new BCryptPasswordEncoder(); -> root--.xml에 bean을 등록

	// ID 중복확인
	@GetMapping("/idDupCheck")
	public void idDupCheck(@RequestParam("id") String id, Model model) {
		// newID 존재여부 확인 & 결과처리
		if (service.selectOne(id) != null) {
			// 사용 불가능
			model.addAttribute("idUse", "F");
		} else {
			// 사용 가능
			model.addAttribute("idUse", "T");
		}

	} // idDupCheck

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
	@PostMapping("/login")
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
		// PasswordEncoder 적용
//		if (dto != null && dto.getPassword().equals(password)) {
		if (dto != null && passwordEncoder.matches(password, dto.getPassword())) {
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
		// passwordEncoder 적용
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		if (service.insert(dto) > 0) {
			model.addAttribute("message", " 회원가입 성공 ");
		} else {
			uri = "member/joinForm";
			model.addAttribute("message", " 회원가입 실패 ");
		}
		return uri;
	} // Join

	// password 수정 (PasswordEncorder 추가 후)
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		// View_name 생략
	}

	// PasswordUpdate
	// service, DAO 에 pwUpdate(dto) 메서드 추가
	// 성공 : 세션 무효화, 로그인창으로
	// 실패 : pwUpdate
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, MemberDTO dto, Model model) {
		// 1 요쳥분석
		// id를 세션에서 가져오기
		dto.setId((String) session.getAttribute("loginID"));
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		String uri = "member/loginForm";

		// 2 Service
		if (service.pwUpdate(dto) > 0) {
			session.invalidate();
			model.addAttribute("message", " 비밀번호 변경 성공, 재로그인하세요 ");
		} else {
			model.addAttribute("message", " 비밀번호 변경 실패, 다시 시도하세요 ");
			uri = "member/pwUpdate";
		}
		return uri;
	} // pwUpdate

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
	public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
		// 1 요청분석
		// id를 session에서 get
		// id 삭제와 session 무력화
		String id = (String) session.getAttribute("loginID");
		String uri = "/home";

		// 2 Service 처리
		if (service.delete(id) > 0) {
			model.addAttribute("message", " 탈퇴 성공 ");
			// requestScope의 message를 redirect 시에도 유지하려면
			// session에 보관했다가 사용 후에는 삭제해야함
			// RedirectAttributes API가
			// session에 보관 후 redirect 되어진 요청 처리시에 requestScope에 옮기고,
			// session의 message는 삭제
			rttr.addFlashAttribute("message", " 탈퇴 성공 ");
			session.invalidate();
		} else {
			model.addAttribute("message", " 탈퇴 실패 ");
			rttr.addFlashAttribute("message", " 탈퇴 실패 ");
		}
		return uri;
	} // Delete
}
// class
