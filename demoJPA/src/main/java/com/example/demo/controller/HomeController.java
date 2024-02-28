package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.service.GuestbookService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

	GuestbookService service;

	@GetMapping("/home")
	public void home(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

	} // home

	@GetMapping("/axtestform")
	public String axTestForm() {
		return "axTest/axTestForm";
	} // axTestForm

	@GetMapping("/ginsert")
	public String ginsert() {
		GuestbookDTO dto = GuestbookDTO.builder().title("JPA Insert").content("입력 성공").writer("admin").build();
		System.out.println(" Guest Insert => " + service.register(dto));
		return "redirect:home";
	}

	@GetMapping("/glist")
	public String glist() {
		List<Guestbook> list = service.selectList();
		for (Guestbook g : list) {
			System.out.print(g + ", regDate =" + g.getRegDate() + ", modDate = " + g.getModDate());
			System.out.println();
		}
		return "redirect:home";
	}

	@GetMapping("/gupdate")
	public String gupdate() {
		GuestbookDTO dto = GuestbookDTO.builder().gno(5L).title("JPA Update").content("수정 성공").writer("banana").build();
		System.out.println(" Guest Update => " + service.register(dto));
		return "redirect:home";
	}

	@GetMapping("/gdetail")
	// QueryString으로 test : /gdetail?gno=2
	public String gdetail(Long gno) {
		System.out.println(" gdetail 성공 => " + service.selectOne(gno));

		return "redirect:home";
	}

	@GetMapping("/gdelete")
	// QueryString으로 test : /gdelete?gno=5
	public String gdelete(Long gno) {
		try {
			service.delete(gno);
			System.out.println(" 삭제 성공 => " + gno);
		} catch (Exception e) {
			// => JPA는 자료가 없으면 org.springframework.dao.EmptyResultDataAccessException 발생확인
			System.out.println(" gDelete Excpetion => " + e.toString());
		}
		return "redirect:home";
	}

	// JPA Paging & Sort
	@GetMapping("/gpage")
	public String gpage() {
		// 출력할 pageNo, page 당 출력할 row 갯수 입력
		PageRequestDTO requestDTO = PageRequestDTO.builder().page(2).size(5).build();
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.pageList(requestDTO);

		System.out.println(" Page List => " + requestDTO.getPage());
		for (GuestbookDTO g : resultDTO.getDtoList()) {
			System.out.println(g);
		}

		return "redirect:home";
	}
} // class
