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
import com.example.demo.entity.Testkey;
import com.example.demo.entity.TestkeyId;
import com.example.demo.service.GuestbookService;
import com.example.demo.service.TestkeyService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

	GuestbookService service;
	TestkeyService tservice;

	@GetMapping("/home")
	public void home(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

	} // home

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ** JPA 복합키 실습 (@IdClass 방법)
	@GetMapping("/tinsert")
	public String tinsert() {
		Testkey entity = Testkey.builder().id("green").no(1).name("김그린").count(1) // JPA save 에서는 MySql에서 정의한 default 1
																					// 적용안됨.
				.build();
		try {
			tservice.save(entity);
			System.out.println("** Testkey SAVE => " + entity);
		} catch (Exception e) {
			System.out.println("** SAVE Exception => " + e.toString());
		}
		return "redirect:home";
	}

	// => Update
	@GetMapping("/tupdate")
	public String tupdate() {
		// => Test Data 작성
		String id = "green";
		int no = 1;
		int count = 10;
		try {
			tservice.updateCount(id, no, count);
			System.out.println("** Testkey Update count값 누적=> " + id + no + ", " + count);
		} catch (Exception e) {
			System.out.println("** UPDATE Exception => " + e.toString());
		}
		return "redirect:home";
	}

	// => DUPLICATE KEY UPDATE (장바구니 응용)
	// 없으면 Save 있으면 Update
	@GetMapping("/tdupupdate")
	public String tdupupdate() {
		// => Test Data 작성
		String id = "banana";
		int no = 2;
		String name = "바나나";
		int count = 1;
		try {
			tservice.dupUpdateCount(id, no, name, count);
			System.out.println("** Testkey Update count값 누적=> " + id + no + ", " + count);
		} catch (Exception e) {
			System.out.println("** DupUpdate Exception => " + e.toString());
		}
		return "redirect:home";
	}

	// ** default 메서드 활용 update
	@GetMapping("/tcalcCount")
	public String tcalcCount() {
		// => Test Data 작성
		String id = "green";
		int no = 1;
		int count = 10;
		try {
			tservice.calcCount(id, no, count);
			System.out.println("** calcCount count+no+100 => " + id + no + ", " + count);
		} catch (Exception e) {
			System.out.println("** calcCount Exception => " + e.toString());
		}
		return "redirect:home";
	}

	@GetMapping("/testlist")
	public String testlist() {

		List<Testkey> list = tservice.selectList();
		for (Testkey t : list) {
			System.out.println(t);
		}
		return "redirect:home";
	}

	@GetMapping("/tdetail")
	// => 퀴리스트링으로 Test : /tdetail?id=apple&no=1
	public String tdetail(TestkeyId testid) {
		System.out.println("tdetail => " + tservice.selectOne(testid));
		return "redirect:home";
	}

	@GetMapping("/tdelete")
	// => 퀴리스트링으로 Test : /tdelete?id=green&no=1
	public String tdelete(TestkeyId testid) {
		try {
			tservice.delete(testid);
			System.out.println("** tdelete 삭제성공 **");
		} catch (Exception e) {
			System.out.println("** tdelete Exception => " + e.toString());
		}
		return "redirect:home";
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@GetMapping("/axtestform")
	public String axTestForm() {
		return "axTest/axTestForm";
	} // axTestForm

	// GuestBook Test
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
