package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping(value = "/jo")
public class JoController {
	JoService service;
	MemberService serviceM;

	// joList
	@GetMapping("/joList")
	public void jList(Model model) {
		model.addAttribute("banana", service.selectList());
	} // joList

	// joDetail
	@GetMapping("/joDetail")
	public String jDetail(HttpServletRequest request, Model model, Jo entity, @RequestParam("jCode") String jCode) {
		String uri = "/jo/joDetail";
		model.addAttribute("apple", service.selectOne(entity.getJno()));

		if ("U".equals(jCode)) {
			uri = "jo/joUpdate";
		}

		if ("D".equals(jCode)) {
			model.addAttribute("banana", serviceM.findByJno(entity.getJno()));
		}
		return uri;
	} // joDetail

	// insertForm
	@GetMapping("/joInsert")
	public void joinForm() {
	} // insertForm

	// JoInsert
	@GetMapping("/insert")
	public String joinsert(Model model, Jo entity, RedirectAttributes rttr) {
		// 1 요청 분석
		String uri = "redirect:joList";

		// 2 Service
		try {
			log.info(" Jo Insert 성공 => " + service.save(entity));
			model.addAttribute("banana", service.selectList());
			rttr.addFlashAttribute("message", " 조 추가 성공 ");
		} catch (Exception e) {
			log.info(" Jo Insert 실패 => " + e.toString());
			uri = "jo/joInsert";
			rttr.addFlashAttribute("message", " 조 추가 실패 ");
		}
		return uri;
	} // JoInsert

	// joUpdate
	@GetMapping("/update")
	public String update(Model model, Jo entity) {
		// 1 요청 분석
		String uri = "jo/joDetail";
		model.addAttribute("apple", entity);

		// 2 Service
		try {
			log.info(" Jo Update 성공 => " + service.save(entity));
			model.addAttribute("message", " 조 정보수정 성공 ");
		} catch (Exception e) {
			log.info(" Jo Update 실패 => " + e.toString());
			uri = "jo/joUpdate";
			model.addAttribute("message", " 조 정보수정 실패 ");
		}
		return uri;
	} // joUpdate

	// joDelete
	@GetMapping("/joDelete")
	public String datail(@RequestParam("jno") int jno, Model model, RedirectAttributes rttr) {
		String uri = "redirect:joList";

		// 2 Service 처리
		try {
			service.deleteById(jno);
			log.info(" Jo Delete 성공 => " + jno);
			model.addAttribute("banana", service.selectList());
			rttr.addFlashAttribute("message", " 삭제 성공 ");
		} catch (Exception e) {
			log.info(" Jo Delete 실패 => " + e.toString());
			rttr.addFlashAttribute("message", " 삭제 실패 ");
		}
		return uri;
	} // joDelete

}
// class
