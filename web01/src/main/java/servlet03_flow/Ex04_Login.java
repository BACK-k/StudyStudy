package servlet03_flow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 요청분석
		// 한글, request의 Parameter 처리
		request.setCharacterEncoding("UTF-8");
		int sno = Integer.parseInt(request.getParameter("sno"));
		String name = request.getParameter("name");
		String uri = "index.html";

		// 2 Service
		// -> StudentService, StudentDTO 인스턴스 필요
		// -> Service의 selectOne을 이용, sno 확인
		// sno의 성공이면 name 확인
		// -> 성공 : index.html
		// -> 실패 : ~~LoginForm.jsp 재로그인 유도
		StudentService service = new StudentService();
		StudentDTO dto = new StudentDTO();

		dto = service.selectOne(sno);

		if (dto.getName().equals(name)) {
			System.out.println(" 로그인 성공 ");
		} else {
			uri = "/web01/servletTestForm/flowEx04_LoginForm.jsp";
		}

		// 3 View(Response) : Redirect
		request.getRequestDispatcher(uri).forward(request, response);

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // class
