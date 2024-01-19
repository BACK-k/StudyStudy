package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C06_mDelete() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 요청분석
		String uri = "home.jsp";

		// 2 Service 처리
		MemberService service = new MemberService();
		if (service.delete((String) (request.getSession().getAttribute("loginID"))) > 0) {
			request.setAttribute("message", "탈퇴 성공");
			request.getSession().invalidate();
			response.sendRedirect(uri);
		}

		// 3 View 처리
	}
}
