package controllerF;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

//** FrontController 패턴 1.
//=> 대표 컨트롤러 1개만 서블릿으로 작성
//	모든 요청을 이 대표컨트롤러(FrontController) 가 받도록 함.
// 나머지 컨트롤러는 일반 클래스로 작성 (2단계, Factory 패턴 적용)

//=> 요청에 대한 서비스를 if문으로 서블릿내에서 모두 처리
// 코드가독성, 모듈의 재사용성 떨어짐 (1단계)

//=> Factory 패턴 적용
// 요청받는 클래스를 생성하고 실행하는 인스턴스(빈 객체)가 필요
// 팩토리에서 빈을 생성
// 각각의 서비스를 일반클래스(컨트롤러)로 작성 Factory로부터 제공받음

// 확장자 개념 사용, 모든 요청은 여기로 온다
@WebServlet(urlPatterns = { "*.do" })
public class Ex01_FrontBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex01_FrontBasic() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 요청 분석
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// url 분석 후 요청명을 확인
		String uri = request.getRequestURI();

		// url과 uri 비교
		// URL = http://localhost:8080/web02/test.do
		// URI = /web02/test.do
		System.out.println(" ~URL => " + request.getRequestURL());
		System.out.println(" ~URI => " + request.getRequestURI());

		uri = uri.substring(uri.lastIndexOf("/"));
		// uri = /test.do
		System.out.println(" ~uri => " + uri);

		// 2 Service 실행
		MemberService service = new MemberService();
		MemberDTO dto = new MemberDTO();

		// 요청에 대한 서비스를 if문으로 서블릿내에서 모두 처리
		if ("/mlist.do".equals(uri)) {
			// Member List
			request.setAttribute("banana", service.selectList());
			uri = "member/memberList.jsp";
		} else if ("/mdetail.do".equals(uri)) {
			// Member Detail : Login 후 Test
			String id = (String) request.getSession().getAttribute("loginID");
			request.setAttribute("apple", service.selectOne(id));
			uri = "member/memberDetail.jsp";
		} else {
			request.setAttribute("message", "요청에 해당하는 서비스는 없습니다");
			uri = "home.jsp";
		}

		// 3 View
		request.getRequestDispatcher(uri).forward(request, response);

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // class
