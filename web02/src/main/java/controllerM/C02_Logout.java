package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class C02_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C02_Logout() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 요청분석
		String uri = "home.jsp";

		// 2 service 처리
		// session 무효화
		request.getSession().invalidate();
		System.out.println(" 로그아웃 되었습니다 ");

		// 3 View 처리
		// Response Forward
		response.sendRedirect(uri);

	} // doGet

} // class
