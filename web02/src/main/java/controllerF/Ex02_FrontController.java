package controllerF;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** FrontController 패턴 2
// 나머지 컨트롤러는 일반 클래스로 작성 (2단계, Factory 패턴 적용)

//=> Factory 패턴 적용
//- ServiceFactory
//- 개별컨트롤러(일반클래스) : 일관성을 위해 강제성 부여 ( interface 사용 )

// 요청받는 클래스를 생성하고 실행하는 인스턴스(빈 객체)가 필요
// 팩토리에서 빈을 생성
// 각각의 서비스를 일반클래스(컨트롤러)로 작성 Factory로부터 제공받음

// 확장자 개념 사용, 모든 요청은 여기로 온다
@WebServlet(urlPatterns = { "*.fo" })
public class Ex02_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex02_FrontController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 요청 분석
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// url 분석 후 요청명을 확인
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));

		// 2 Service 실행
		// ServiceFactory 에 요청
		// uri 를 전달하면 해당 서비스컨트롤러 를 생성해서 인스턴스를 제공

		// 싱글턴 패턴, private으로 설정해 ServiceFactory의 외부 생성을 허용하지 않음
		// Ex03_ServiceFactory is not visible
//		Ex03_ServiceFactory sf = new Ex03_ServiceFactory();

		Ex03_ServiceFactory sf = Ex03_ServiceFactory.getInstance();
//		// 싱글턴 패턴 Test
//		Ex03_ServiceFactory sf1 = Ex03_ServiceFactory.getInstance();
//		Ex03_ServiceFactory sf2 = Ex03_ServiceFactory.getInstance();
//		System.out.printf(" 싱글턴 패턴 Tset : sf = %s, sf1 = %s, sf2 = %s \n", sf, sf1, sf2);

		// 컨트롤러에게 요청을 전달
		Ex04_Controller controller = sf.getController(uri);
		// View Name
		uri = controller.doUser(request, response);

		// 3 View
		request.getRequestDispatcher(uri).forward(request, response);

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // class
