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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
// 개별적인 @Autowired 생략 가능
@AllArgsConstructor
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	MemberService service;
	PasswordEncoder passwordEncoder;

	// 2) Member_Jo Join List
	// => ver01) @Query("...") 에 JPQL, LEFT_JOIN 구문, MemberDTO return
	// => MemberDTO 는 JoDTO 상속
	@GetMapping("/mjoinList")
	public void mjoinList(Model model) {
		model.addAttribute("banana", service.findMemberJoin());
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

	@GetMapping("/loginForm")
	public void loginForm() {
	} // loginForm

	// login
	@PostMapping("/login")
	public String login(HttpSession session, Model model, Member entity) {

		// 1 요청분석
		String password = entity.getPassword();
		String uri = "redirect:/home";

		// 2 서비스 & 결과 처리
		entity = service.selectOne(entity.getId());
		if (entity != null && passwordEncoder.matches(password, entity.getPassword())) {
			session.setAttribute("loginID", entity.getId());
			session.setAttribute("loginName", entity.getName());
		} else {
			uri = "memeber/loginForm";
			model.addAttribute("message", " 아이디, 비밀번호를 확인해주세요 ");
		} // if
		return uri;
	} // login

	// logout
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/";
	} // logout

	// Member Detail
	@GetMapping("/detail")
	public String datail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		// 1 요청분석
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
	@GetMapping("/memberList")
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	} // mList

	// JoinForm
	@GetMapping("/joinForm")
	public void joinForm() {
	} // JoinForm

	// Join
	@PostMapping("/join")
	public String join(HttpServletRequest request, Model model, Member entity) throws IOException {
		// 1 요청 분석
		String uri = "member/loginForm";

		// 1) 물리적 실제 저장위치 확인
		// 1.1) 현재 웹어플리케이션의 실행 위치 확인
		String realPath = request.getRealPath("/");
		// Spring Boot : C:\MTest\MyWork\demoJPA\src\main\webapps\
		System.out.println("realPath => " + realPath);
		realPath += "resources\\uploadImages\\";

		// 1.2 폴더 만들기(없을수도 있음을 가정, File)
		File file = new File(realPath);
		if (!file.exists()) {
			// 저장 폴더가 존재하지 않는 경우 폴더를 만들어준다
			file.mkdir();
		}
		// ** File Copy 하기 (IO Stream)
		// => 기본이미지(basicman4.png) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
		file = new File(realPath + "KarinaFlower1.jpg"); // uploadImages 폴더에 화일존재 확인을 위함
		if (!file.isFile()) { // 존재하지않는 경우
			String basicImagePath = "C:\\MTest\\StudyStudy\\demoJPA\\src\\main\\webapp\\resources\\images\\KarinaFlower1.jpg";
			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			// => basicImage 읽어 파일 입력바이트스트림 생성
			FileOutputStream fo = new FileOutputStream(file);
			// => 목적지 파일(realPath+"basicman4.png") 출력바이트스트림 생성
			FileCopyUtils.copy(fi, fo);
		}

		// 1.3) 저장경로 완성
		String file1 = "", file2 = "KarinaFlower1.jpg";
		// image_File을 선택
		MultipartFile uploadfilef = entity.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// 1.4.1 물리적위치 저장(file1)
			// 저장경로(realpath + 파일명) 완성
			file1 = realPath + uploadfilef.getOriginalFilename();
			// 해당 경로에 저장(붙여넣기)
			uploadfilef.transferTo(new File(file1));

			// 1.4.2 Table 저장경로 완성(file2)
			file2 = uploadfilef.getOriginalFilename();
		}
		entity.setUploadfile(file2);

		// 2 Service
		// passwordEncoder 적용
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));

		// JPA에선 if가 아니고 try-catch 처리해줘야함
		try {
			log.info(" member insert 성공 => " + service.save(entity));
			model.addAttribute("message", " 회원가입 성공 ");
		} catch (Exception e) {
			uri = "member/joinForm";
			log.info(" member insert Exception => " + e.toString());
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
	public String pwUpdate(HttpSession session, Member entity, Model model) {
		// 1 요쳥분석
		// id를 세션에서 가져오기
		entity.setId((String) session.getAttribute("loginID"));
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		String uri = "member/loginForm";

		// 2 Service
		try {
			service.updatePassword(entity.getId(), entity.getPassword());
			log.info(" Password Update 성공 ID => " + entity.getId());
			session.invalidate();
			model.addAttribute("message", " 비밀번호 변경 성공, 재로그인하세요 ");
		} catch (Exception e) {
			log.info(" Password Update Exception => " + e.toString());
			model.addAttribute("message", " 비밀번호 변경 실패, 다시 시도하세요 ");
			uri = "member/pwUpdate";
		}
		return uri;
	} // pwUpdate

	// Update
	@PostMapping("/update")
	public String update(HttpServletRequest request, HttpSession session, Model model, Member entity)
			throws IOException {
		// 1 요청 분석
		String uri = "member/memberDetail";
		model.addAttribute("apple", entity);

		// uploadFile 처리
		// newImage 선택 여부
		// 선택 -> oldImage 삭제, newImage 저장 : uploadfilef 사용
		// 선택하지않음 -> oldImage가 uploadfile로 전달되었으므로 그냥 사용하면 됨
		MultipartFile uploadfilef = entity.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// newImage를 선택함
			// 1 물리적위치 저장(file1)
			String realPath = request.getRealPath("/");
			String file1;
			realPath += "resources\\uploadImages\\";

			// 3 oldFile 삭제
			// oldFile Name : dto.getUploadfile()
			// 삭제경로 : realPath + dto.getUploadfile()
			File delFile = new File(realPath + entity.getUploadfile());
			// 파일이 존재하면 삭제
			if (delFile.isFile())
				delFile.delete();

			// 4 newFile 저장
			file1 = realPath + uploadfilef.getOriginalFilename();
			uploadfilef.transferTo(new File(file1));

		}

		// 2 Service
		try {
			log.info(" member Update 성공 => " + service.save(entity));
			session.setAttribute("loginName", entity.getName());
			model.addAttribute("message", " 회원정보수정 성공 ");
		} catch (Exception e) {
			log.info(" member Update Exception => " + e.toString());
			uri = "member/updateForm";
			model.addAttribute("message", " 회원정보수정 실패 ");
		}

		return uri;
	} // Update

	// Delete
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
		// 1 요청분석
		String id = (String) session.getAttribute("loginID");
		String uri = "/home";

		// 2 Service 처리
		try {
			service.deleteById(id);
			log.info(" member Delete 성공 => " + id);
			rttr.addFlashAttribute("message", " 탈퇴 성공 ");
			session.invalidate();
		} catch (Exception e) {
			log.info(" Delete Exception => " + e.toString());
			rttr.addFlashAttribute("message", " 탈퇴 실패 ");
		}
		return uri;
	} // Delete
}
// class
