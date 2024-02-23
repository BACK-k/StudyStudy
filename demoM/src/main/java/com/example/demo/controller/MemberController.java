package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

// Spring Boot는 Log4j 대신 Log4j2 사용
@Log4j2
// 개별적인 @Autowired 생략 가능
@AllArgsConstructor
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	// @Autowired(required = false)
	MemberService service;
	// @Autowired(required = false)
	JoService jservice;
	// @Autowired
	// DemoConfig에 설정
	PasswordEncoder passwordEncoder;

	// axmlist
	@GetMapping("/aximlist")
	public String axiMemberList(Model model) {
		model.addAttribute("banana", service.selectList());
		log.info(" axMemberList 성공 ");
		return "axTest/axMemberList";
	}

	// Member Check List
	@GetMapping("/mCheckList")
	public String mCheckList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		String uri = "member/mPageList";
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		// 1 Criteria 처리
		cri.setSnoEno();

		// 2 Service 처리
		if (cri.getCheck() != null && cri.getCheck().length < 1) {
			cri.setCheck(null);
		}
		model.addAttribute("banana", service.mCheckList(cri));

		// 3 View 처리
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);

		return uri;
	} // mCheckList

	// Member Paging
	@GetMapping("/mPageList")
	public void mPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		// 1 Criteria 처리
		cri.setSnoEno();

		// 2 Service 처리
		model.addAttribute("banana", service.mPageList(cri));

		// 3 View 처리
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);

	}

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

	@RequestMapping(value = { "/loginForm" }, method = RequestMethod.GET)
	public void loginForm() {
	} // loginForm

	// login
	@PostMapping("/login")
	public String login(HttpSession session, Model model, MemberDTO dto) {
		String password = dto.getPassword();
		String uri = "redirect:/home"; // 성공시 uri

		dto = service.selectOne(dto.getId());
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
	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
	public String datail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		String id = (String) session.getAttribute("loginID");
		String uri = "member/memberDetail";

		if ("U".equals(jCode)) {
			uri = "member/updateForm";
		}

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
	public String join(HttpServletRequest request, Model model, MemberDTO dto) throws IOException {
		String uri = "member/loginForm";

		String realPath = request.getRealPath("/");
		// Spring Boot realPath
		// C:\MTest\MyWork\demoM\src\main\webapp
		System.out.println("realPath => " + realPath);

		if (!realPath.contains("-tomcat-"))
			realPath += "resources\\uploadImages\\";
		else
			realPath += "C:\\MTest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";

		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdir();
		}

		file = new File(realPath + "KarinaFlower1.jpg"); // uploadImages 폴더에 화일존재 확인을 위함
		if (!file.isFile()) { // 존재하지않는 경우
			String basicImagePath = "C:\\MTest\\StudyStudy\\demoM\\src\\main\\webapp\\resources\\images\\KarinaFlower1.jpg";
			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			FileOutputStream fo = new FileOutputStream(file);
			FileCopyUtils.copy(fi, fo);
		}

		String file1 = "", file2 = "KarinaFlower1.jpg";
		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			file1 = realPath + uploadfilef.getOriginalFilename();
			uploadfilef.transferTo(new File(file1));

			file2 = uploadfilef.getOriginalFilename();
		}
		dto.setUploadfile(file2);
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
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, MemberDTO dto, Model model) {
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
	public String update(HttpServletRequest request, HttpSession session, Model model, MemberDTO dto)
			throws IOException {
		String uri = "member/memberDetail";
		model.addAttribute("apple", dto);

		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			String realPath = request.getRealPath("/");
			String file1, file2 = dto.getUploadfile();

			if (!realPath.contains("-tomcat-"))
				realPath += "resources\\uploadImages\\";
			else
				realPath = "C:\\MTest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";

			File delFile = new File(realPath + dto.getUploadfile());
			if (delFile.isFile())
				delFile.delete();

			file1 = realPath + uploadfilef.getOriginalFilename();
			uploadfilef.transferTo(new File(file1));

			file2 = uploadfilef.getOriginalFilename();
			dto.setUploadfile(file2);
		}

		if (service.update(dto) > 0) {
			model.addAttribute("message", " 회원정보수정 성공 ");
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
		String id = (String) session.getAttribute("loginID");
		String uri = "/home";

		if (service.delete(id) > 0) {
			rttr.addFlashAttribute("message", " 탈퇴 성공 ");
			session.invalidate();
		} else {
			rttr.addFlashAttribute("message", " 탈퇴 실패 ");
		}
		return uri;
	} // Delete

}// class
