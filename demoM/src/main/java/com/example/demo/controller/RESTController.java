package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.JoDTO;
import com.example.demo.domain.MemberDTO;
import com.example.demo.domain.UserDTO;
import com.example.demo.service.BoardService;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest")
@Log4j2
@AllArgsConstructor
public class RESTController {
	MemberService service;
	JoService jservice;
	BoardService bservice;
	// DemoConfig에 bean 생성 설정
	PasswordEncoder passwordEncoder;

	@GetMapping("/hello")
	// 메뉴없이 직접 요청 : http://localhost:8080/spring02/rest/hello
	// return 한 String 값이 response에 담겨져 전송되어 출력
	public String hello() {
		log.info(" Rest API Test ");
		return " Hello Spring MVC REST API ! 안녕 REST ! ";
	}

// RESTController 의 다양한 return Type
// 1 Text return
	// http://localhost:8080/spring02/rest/gettext
	// => produces 속성
	// - 해당 메서드 결과물의 MIME Type을 의미 ( UI Content-Type 에 표시됨 )
	// - 위처럼 문자열로 직접 지정 할수도 있고, 메서드내의 MediaType 클래스를 이용할 수도 있음
	// - 필수속성은 아님 ( 기본값은 text/html, 그러므로 적용하지 않은 경우 아래 <h1></h1> 적용됨 )
//	텍스트를 그대로 표현
//	@GetMapping(value = "/gettext", produces = "text/plain; charset=UTF-8")
//	@GetMapping(value = "/gettext", produces = { MediaType.TEXT_PLAIN_VALUE })
	@GetMapping("/gettext")
	public String getText() {
		log.info(" MIME Type, MediaType 클래스적용 => " + MediaType.TEXT_PLAIN_VALUE);
		return " <h2> 안녕 REST ! 오점뭐 ? </h2> ";
	}

	// ** 객체 주의사항
	// => Java 의 객체를 UI 가 인식가능한 형태의 객체로 변환후 전송
	// => xml 또는 JSON 포맷
	// => 즉, Java <-> JSON 변환을 지원하는 API 필요함
	// 여기부터는 pom 에 dependency 추가 해야함

// 2) 사용자 정의 객체
	// 2.1 객체 return1 (produces 속성을 지정하는 경우)
	// http://localhost:8080/spring02/rest/getdot1
	// => produces
	// - JSON 과 XML 방식의 데이터를 생성할 수 있도록 설정(
	// - Response Data Type을 제한 함으로 오류를 줄임
	// - 입력값을 제한할때는 "consumes" 속성 사용
	// => 요청 url의 확장자에 따라 다른 타입으로 서비스
	// - Test1) 브라우져에서 /rest/getdto1 호출 -> 위 둘중 XML 전송(default)
	// - Test2) 브라우져에서 /rest/getdto1.json 호출 -> JSON 전송
	// 단, SpringBoot 에서는 요청Data가 없는 경우 Test 불가함.
	// produces 속성 지정하지 않은 getDTO2 만 정상적으로 실행됨.
	@GetMapping(value = "/getdto1", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public JoDTO getDTO1() {
		return new JoDTO(9, "Rest조", "banana", "Rest API", "화이팅", "바나나");
	}

	// 2.2 객체 return2 (produes 속성을 지정하지 않는 경우)
	// produces 솔성을 지정하지 않는 경우 : xml
	// getdto2.json : json
	@GetMapping("/getdto2")
	public JoDTO getDTO2() {
		return new JoDTO(99, "Rest_2 조", "apple", "Rest API 2", "화이팅", "사과나무");
	}

// 3 Collection return
	// 3.1 Map
	// => XML로 Return하는 경우 Key값 주의 (변수명 규칙)
	// UI(브라우져) 에서 Tag명이 되므로 반드시 문자로 한다.
	// ( 첫글자 숫자, 특수문자 모두 안됨 주의, 단 json Type 은 무관함 )
	// -> 222, -Second, 2nd, ..... 등등, 그러나 한글은 허용
	// -> This page contains the following errors:
	// error on line 1 at column 109: StartTag: invalid element name...
	// => rest/getmap , rest/getmap.json 모두 Test
	// => map 은 출력 순서 무관
	@GetMapping("/getmap")
	public Map<String, JoDTO> getMap() {
		Map<String, JoDTO> map = new HashMap<String, JoDTO>();
		map.put("one", new JoDTO(1, "Rest_1 조", "apple", "Rest API", "화이팅", "홍길동"));
		map.put("two", new JoDTO(2, "Rest_2 조", "apple", "Rest API", "화이팅", "홍길동"));
		map.put("삼3", new JoDTO(3, "Rest_3 조", "apple", "Rest API", "화이팅", "홍길동"));
		// xml으로 출력할 때 숫자가 먼저나오면 태그값으로 인식해서 안된다
//		map.put("4사", new JoDTO(4, "Rest_4 조", "apple", "Rest API", "화이팅", "홍길동"));
		return map;
	}

	// 3.2 List
	@GetMapping("/getlist")
	public List<JoDTO> getList() {
		return jservice.selectList();
	}

	// ResponseEntity
	// => Status (200, 404 등 응답 상태 코드) , Headers, Body 등을 함께 전송할수있음.
	// => status : 200(OK), 502(BAD_GATEWAY) , 500(INTERNAL_SERVER_ERROR)
	// => 즉, 직접 status code 지정 가능.
	// => 사용법
	// - Builder Pattern (권장)

	// ** Parameter 를 쿼리스트링으로 전달하는 경우 서버에서 처리방법
	// 1) params 속성으로 처리
	// - URL Query_String Param Parsing, "key=value" 형식으로 전달된 파라미터 매핑

	// 2) @RequestParam 으로 처리
	// - @RequestParam("jno") int jno -> Spring02의 MemberController, /dnload 참고
	// => params 와 @RequestParam 비교 해보세요.
	// parameter 오류시 400
	// - params : Parameter conditions "jno, id" not met for actual request
	// parameters: jno2={11}, id={banana}
	// - @RequestParam : Required request parameter 'jno' for method parameter type
	// int is not present
	// ( Mapper interface의 @Param 과는 구별 )

	// 3) @PathVariable
	// 4) @RequestBody

	// ** params 속성
	// => 값에 상관없이 파라미터에 params 속성으로 정의한 "jno", "id" 이 반드시 있어야 호출됨
	// 만약 하나라도 전달받지 못하면 "400–잘못된 요청" 오류 발생
	// => Parameter name 과 매개변수는 이름으로 매핑함. (즉, 같아야함)
	// => Spring02 의 MemberController의 상단 주석 params 참고

	// 4) ResponseEntity Test
	// => 실습
	// 전달된 jno값의 조건에 의하여 502(BAD_GATEWAY) 또는 200(OK) 상태코드와 데이터를 함께 전송하므로
	// 요청 User가 이 응답결과(body값)의 정상/비정상 여부를 알수있도록 해준다
	// => 200 Test: http://localhost:8080/spring02/rest/incheck?jno=7&captain=admin
	// http://localhost:8080/spring02/rest/incheck.json?jno=7&captain=admin
	// => 502 Test: http://localhost:8080/spring02/rest/incheck?jno=5&captain=banana

	// 4.1 incheck : parmas
	@GetMapping(value = "/incheck", params = { "jno", "captain" })
	public ResponseEntity<JoDTO> inCheck(int jno, String captain) {
		// 1 준비
		ResponseEntity<JoDTO> result = null;
		JoDTO dto = new JoDTO(1, "Rest 1조", "apple", "Rest API", "화이팅", "홍길동");
		dto.setJno(jno);

		// 2 Service & return
		// jno로 selectOne, 성공시 captain 비교해서 일치하면 성공, 아니면 오류
		dto = jservice.selectOne(jno);
		if (dto != null && dto.getCaptain().equals(captain)) {
			// 성공
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info(" inCheck Test HttpStatus.OK => " + HttpStatus.OK);
			log.info(" inCheck Test dto => " + dto);
		} else {
			// 실패
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			log.info(" inCheck Test HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}

		return result;
	} // incheck

	// 4.2 incheck2 : @RequestParam Test
	@GetMapping("/incheck2")
	// 매핑을 엄격하게 하기위해 @RequestParam, @Params 사용
// http://localhost:8080/spring02/rest/incheck2?jno=7&id=admin
//	public ResponseEntity<JoDTO> inCheck2(@RequestParam("jno") int jno, @RequestParam("id") String captain) {
// http://localhost:8080/spring02/rest/incheck2?jno=7&captain=admin
	// @RequestParam : parameter와 매개변수명이 동일한 경우 생략 가능
	// 단, 생략된 경우 parameter가 없으면 null로 통과
	public ResponseEntity<JoDTO> inCheck2(int jno, String captain) {
		// 1 준비
		ResponseEntity<JoDTO> result = null;
		JoDTO dto = new JoDTO(1, "Rest 1조", "apple", "Rest API", "화이팅", "홍길동");
		dto.setJno(jno);

		// 2 Service & return
		// jno로 selectOne, 성공시 captain 비교해서 일치하면 성공, 아니면 오류
		dto = jservice.selectOne(jno);
		if (dto != null && dto.getCaptain().equals(captain)) {
			// 성공
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info(" inCheck Test HttpStatus.OK => " + HttpStatus.OK);
			log.info(" inCheck Test dto => " + dto);
		} else {
			// 실패
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			log.info(" inCheck Test HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}

		return result;
	} // incheck2

	// 5 @PathVariable
	// => URL 경로의 일부를 파라미터로 사용할때 이용
	// http://localhost:8080/spring02/rest/order/outer/노랑
	// => 요청 URI 매핑에서 템플릿 변수를 설정하고 이를 매핑메서드 매개변수의 값으로 할당 시켜줌.
	// 이때 파라미터가 1개이면 @PathVariable 과 같이 name을 생략할수 있다
	@GetMapping("/order/{test1}/{test2}")
	public String[] order(@PathVariable("test1") String category, @PathVariable("test2") String color) {
		return new String[] { "category : " + category, "color : " + color };
	} // order

	// 6 @RequestBody
	// => JSON 형식으로 전달된 Data를 컨트롤러에서 사용자정의 객체(DTO) _Java객체 로 변환할때 사용
	// => 요청 url : http://localhost:8080/rest/convert
	// => Payload : {"jno":33, "jname":"삼삼오오", "captain":"victory",
	// "project":"RequestBody Test 중"}
	@PostMapping("/convert")
	public ResponseEntity<?> convert(@RequestBody JoDTO dto) {
		ResponseEntity<JoDTO> result = null;
		log.info(" convert JSON 으로 전달된 dto => " + dto);
		if (dto != null) {
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info("** convert Test HttpStatus.OK => " + HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			log.info("** convert Test HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}
		return result;
	} // convert

	// ** Ajax: 비동기 통신 fetch 요청 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// 1) Login1
	// => Request: JSON, Response: Text
	// => MediaType
	// Mapping 시 받는 데이터를 강제를 함으로 오류상황을 줄일 수 있다.
	// 이것을 위해 사용하는것중 하나가 MediaType 이며,
	// 받는 데이터를 제한할때 consumes (위에서는 Json 임을 강제함)
	// 나가는 데이터를 제한할때 produces (위에서는 String을 Return 함을 강제함)
	// => consumes를 설정하면 Request Header에 보내는 Data가 JSON 임을 명시해야함.
	// => @RequestBody : Json -> Java 객체로 파싱
	@PostMapping(value = "/rslogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> rslogin(HttpSession session, @RequestBody MemberDTO dto) {
		ResponseEntity<String> result = null;
		// 1 password 보관
		String password = dto.getPassword();

		// 2 Service 처리
		// => 성공: login 정보를 session에 보관후, status OK, body="성공 message"
		// 실패: status INTERNAL_BAD_GATEWAY 502, body="실패 message"
		dto = service.selectOne(dto.getId());
		if (dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
			result = ResponseEntity.status(HttpStatus.OK).body(" 로그인 성공 ");
			log.info(" reLogin HttpStatus.OK => " + HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(" 로그인 실패 ");
			log.info(" reLogin HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}
		return result;
	} // rslogin

	// 2 Login2
	// request : JSON, reponse : JSON
	// UserDTO 사용, login 정보를 담아서 전송
	@PostMapping(value = "/rsloginjj", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> rsloginjj(HttpSession session, @RequestBody MemberDTO dto) {
		ResponseEntity<UserDTO> result = null;
		// 1 password 보관
		String password = dto.getPassword();

		// 2 Service 처리
		dto = service.selectOne(dto.getId());
		if (dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
			// response로 전송할 객체 생성, 담기
			// UserDTO 활용, builder 패턴 적용
			// UserDTO의 값에 대한 변경을 예방하기 위해 fianl 사용하기도 함
			final UserDTO userDTO = UserDTO.builder().id(dto.getId()).username(dto.getName()).build();

			result = ResponseEntity.status(HttpStatus.OK).body(userDTO);
			log.info(" reLogin HttpStatus.OK => " + HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
			log.info(" reLogin HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}
		return result;
	} // rsloginjj

	// 3 join
	// => image 포함, "multipart/form-data" Type 으로 요청
	// => consumes, produces 설정
	@PostMapping(value = "/rsjoin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	// formData가 JS의 객체 형태로 POST되어 @RequestBody가 없어도 됨
	public ResponseEntity<?> rsjoin(MemberDTO dto) throws Exception {
		ResponseEntity<String> result = null;
		// join Service 처리
		// MultipartFile, passwordEncoder(암호화)
		String realPath = "C:\\MTest\\MyWork\\demoM\\src\\main\\webapp\\resources\\uploadImages\\";
		String file1 = "", file2 = "KarinaFlower1.jpg";
		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			file1 = realPath + uploadfilef.getOriginalFilename();
			uploadfilef.transferTo(new File(file1));

			file2 = uploadfilef.getOriginalFilename();
		}
		dto.setUploadfile(file2);

		// passwordEncoder 적용
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		if (service.insert(dto) > 0) {
			result = ResponseEntity.status(HttpStatus.OK).body(" 회원가입 성공! 로그인 후 이용하세요 ");
			log.info(" rsJoin HttpStatus.OK => " + HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(" 회원가입 실패! ");
			log.info(" rsJgin HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}

		return result;
	} // rsjoin

	// Ajax, 반복문에 이벤트 적용하기
	// 1 idbList(id별 boardList)
	@GetMapping("/idblist/{id}")
	public ResponseEntity<?> idbList(@PathVariable("id") String id) {
		ResponseEntity<?> result = null;

		// service 처리
		List<BoardDTO> list = bservice.idbList(id);

		// 출력 데이터 유무확인
		if (list != null && list.size() > 0) {
			result = ResponseEntity.status(HttpStatus.OK).body(list);
			log.info(" idbList HttpStatus.OK => " + HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(" 출력할 자료가 없습니다 ");
			log.info(" idbList HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}

		return result;
	} // idbList

	@DeleteMapping("/axidelete/{ii}")
	public ResponseEntity<?> axiDelete(@PathVariable("ii") String id) {
		if (service.delete(id) > 0) {
			log.info(" axiDelete HttpStatus.OK => " + HttpStatus.OK);
			return new ResponseEntity<String>(" 삭제 성공 ", HttpStatus.OK);
		} else {
			log.info(" axiDelete HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
			ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(" 삭제 실패 ");
			return new ResponseEntity<String>(" 삭제 실패, Data_NotFound ", HttpStatus.BAD_GATEWAY);
		}

	} // axiDelete

	@GetMapping("/jodetail/{jno}")
	public ResponseEntity<?> showJoDetail(@PathVariable("jno") int jno, JoDTO dto) {
		ResponseEntity<?> result = null;

		// service 처리
		dto = jservice.selectOne(jno);

		// 출력 데이터 유무확인
		if (jservice.selectOne(jno) != null) {
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info(" axiJoDetail HttpStatus.OK => " + HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(" 조회 실패 ");
			log.info(" axiJoDetail HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}
		return result;
	} // axiJoDetail

}
// class
