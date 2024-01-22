package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

// Spring에서 지원하는 Controller 사용
public class C01_mList implements Controller {
	@Override
	// Spring에서 지원하는 Controller의 return 타입은 ModelAndView
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// Member List
		MemberService service = new MemberService();
		ModelAndView mv = new ModelAndView();
		mv.addObject("banana", service.selectList());
		mv.setViewName("member/memberList");
		return mv;
	} // handleRequest
} // class
