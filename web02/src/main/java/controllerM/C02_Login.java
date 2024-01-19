package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C02_Login() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 요청분석
		// request의 Parameter 처리
		// id, password 처리
		String id = null;
		String password = null;
		if (request.getParameter("id") != null && request.getParameter("id").length() > 0) {
			id = request.getParameter("id");
		}
		if (request.getParameter("password") != null && request.getParameter("password").length() > 0) {
			password = request.getParameter("password");
		}
		String uri = "home.jsp";

		// 2 service 처리
		// Service, DTO 객체 생성
		// id를 일치여부 확인 : Service -> selectOne
		// id가 일치하면 password 일치여부 확인
		// 성공하면 id, password를 세션에 보관하고 homs.jsp로 이동
		// 실패하면 다시 loginForm으로 이동, 재로그인 유도
		MemberService service = new MemberService();
		MemberDTO dto = service.selectOne(id);

		if (dto != null && dto.getPassword().equals(password)) {
			request.getSession().setAttribute("loginID", id);
			request.getSession().setAttribute("loginName", dto.getName());
			response.sendRedirect(uri);
		} else {
			request.getRequestDispatcher("member/loginForm.jsp").forward(request, response);
		}

		// 3 View 처리
		// Response Forward

	} // doPost

} // class
