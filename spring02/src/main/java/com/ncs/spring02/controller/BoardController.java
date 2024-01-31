package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
	BoardService service;

	// Reply Insert
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) {
		// 답글처리를 위해 부모글의 root, step, indent를 인자로 전달받으면,
		// 이 인자에 담겨진 값은 requestScope와 동일
		// 그러므로 response 전송 전까지는 서버(Jsp)에서 사용가능
		// 단, 객체명의 첫알파벳문자를 소문자로 해서 접근가능( ${boardDTO.--} )
	}

	// Method명과 요청명이 위의 메서드와 동일하지만
	// post요청이고 인자가 다르기때문에 허용됨
	@PostMapping("/replyInsert")
	public String replyInsert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		// 답글등록 성공시 : boardList 에서 입력완료 확인
		// 실패시 : replyInsert 재입력유도
		String uri = "redirect:boardList";

		// service 처리를 위해 전달된 dto 값 확인
//			id, title, content : 그대로 사용 가능
//			부모글의 root : 그대로 사용 가능
//			부모글의 step, indent : 1씩 증가
		// sql 처리
//			replyInsert, step의 update
		dto.setStep(dto.getStep() + 1);
		dto.setIndent(dto.getIndent() + 1);

		if (service.rinsert(dto) > 0) {
			rttr.addFlashAttribute("message", " 답글 등록 성공 ");
		} else {
			uri = "board/replyInsert";
			model.addAttribute("message", " 답글 등록 실패 ");
		}

		return uri;
	} // replyInsert

	// Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana", service.selectList());
	} // Board List

	// Board Detail
	// 글 요청 처리중, 글을 읽기 전 상태
	// 조회수 증가
	// loginID와 board의 id가 다른 경우
	@GetMapping("/detail")
	public String boardDetail(HttpSession session, Model model, @RequestParam("jCode") String jCode,
			@RequestParam("seq") int seq) {
		String uri = "board/boardDetail";
		// 조회수 증가 : service.selectOne(seq)의 결과를 보관해야함
		BoardDTO dto = service.selectOne(seq);

		if ("U".equals(jCode)) {
			uri = "board/boardUpdate";

		} // update 요청이 아니고, loginID와 글쓴이의 id가 다른 경우
		else if (!dto.getId().equals((String) session.getAttribute("loginID"))) {
			// 조회수 증가 조건 만족
			dto.setCnt(dto.getCnt() + 1);
			service.update(dto);
		}

		model.addAttribute("apple", dto);

		return uri;
	} // Board Detail

	// Board Insert
	@GetMapping("/boardInsert")
	public void boardInsert() {

	} // Board Insert

	// Insert
	@GetMapping("/insert")
	public String insert(HttpSession session, Model model, BoardDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		dto.setId((String) session.getAttribute("loginID"));
		if (service.insert(dto) > 0) {
			rttr.addFlashAttribute("banana", service.selectList());
			rttr.addFlashAttribute("message", " 게시글 등록 성공 ");
		} else {
			uri = "board/boardInsert";
			model.addAttribute("message", " 게시글 등록 실패 ");
		}
		return uri;
	}

	// Update
	@PostMapping("/update")
	public String update(Model model, BoardDTO dto) {
		// 1 요청 분석
		String uri = "board/boardDetail";
		model.addAttribute("apple", dto);

		// 2 Service
		if (service.update(dto) > 0) {
			model.addAttribute("message", " 조 정보수정 성공 ");
		} else {
			uri = "board/boardUpdate";
			model.addAttribute("message", " 조 정보수정 실패 ");
		}
		return uri;
	}

	// Delete
	@GetMapping("/boardDelete")
	public String datail(BoardDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		if (service.delete(dto) > 0) {
			rttr.addFlashAttribute("message", " 삭제 성공 ");
		} else {
			rttr.addFlashAttribute("message", " 삭제 실패 ");
		}
		return uri;
	}

} // class
