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

	// Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana", service.selectList());
	} // Board List

	// Board Detail
	@GetMapping("/detail")
	public String boardDetail(Model model, @RequestParam("jCode") String jCode, @RequestParam("seq") int seq) {
		String uri = "board/boardDetail";
		System.out.println("U".equals(jCode));
		if ("U".equals(jCode)) {
			uri = "board/boardUpdate";

		}

		model.addAttribute("apple", service.selectOne(seq));

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
	public String datail(@RequestParam("seq") int seq, Model model, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		if (service.delete(seq) > 0) {
			model.addAttribute("banana", service.selectList());
			rttr.addFlashAttribute("message", " 삭제 성공 ");
		} else {
			rttr.addFlashAttribute("message", " 삭제 실패 ");
		}
		return uri;
	}

} // class
