package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/select")
public class Ex04_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Select() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 요청 분석
		// request 처리 : 한글, Parameter
		request.setCharacterEncoding("UTF-8");
		String job = request.getParameter("job");
		String[] interest = request.getParameterValues("interest");

		// 2 Service, 결과 처리
		// response 한글처리, 출력객체 생성
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<br>&nbsp;" + job + "<br>");
		for (String i : interest) {
			out.print("&nbsp;" + i + "&nbsp;");
		}
	} // doGet

} // class
