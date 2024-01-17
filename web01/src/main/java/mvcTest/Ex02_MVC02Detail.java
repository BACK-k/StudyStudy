package mvcTest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex02_MVC02Detail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청분석 & service
		// 검색 대상의 id(sno) 필요, 로그인시에 보관해뒀음
		// session에서 getAttribute
		StudentService service = new StudentService();
		StudentDTO info = service.selectOne((Integer) request.getSession().getAttribute("loginID"));

		// view 준비
		// 결과를 view가 인식가능하도록 setAttribute
		// Forward로 전달
		request.setAttribute("myInfo", info);
		String uri = "mvcTestJsp/ex03_MVC02Detail.jsp";
		request.getRequestDispatcher(uri).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
