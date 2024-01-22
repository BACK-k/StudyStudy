package controllerF;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class Ex05_mDetail implements Ex04_Controller {
	@Override
	public String doUser(HttpServletRequest request, HttpServletResponse response) {
		MemberService service = new MemberService();
		String id = (String) request.getSession().getAttribute("loginID");
		request.setAttribute("apple", service.selectOne(id));
		// View name 반환
		return "member/memberDetail.jsp";
	} // doUser
} // class
