package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class C02_mDetail implements MyController {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// Member Detail
		MemberService service = new MemberService();
		String id = (String) request.getSession().getAttribute("loginID");
		request.setAttribute("apple", service.selectOne(id));
		return "member/memberDetail";
	} // handleRequest
} // class
