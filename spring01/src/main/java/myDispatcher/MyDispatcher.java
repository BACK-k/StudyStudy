package myDispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*** Spring MVC2_ver01
//=> MyDispatcherServlet (FrontController 역할)
//  HandlerMapping, ViewResolver 를 활용해서
//  요청분석, Service, View 를 처리

// Url Mapping은 web.xml에서 처리
public class MyDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 필요한 전역변수 정의
	// HandlerMapping -> ServiceFactory
	private MyHandlerMapping hmappings;
	private MyViewResolver vresolver;

	// 맴버변수 초기화 : 생성자에서
	public MyDispatcher() {
		super();
		hmappings = MyHandlerMapping.getInstance();
		vresolver = new MyViewResolver();
		// 경로 중, 파일이름 앞에 위치한 부분
		vresolver.setPrefix("/WEB-INF/views/");
		// 확장자
		vresolver.setSuffix(".jsp");
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
		// MyHandlerMapping 에게 요청
		// 해당하는 서비스 컨트롤러의 인스턴스를 제공받고 해당 서비스 실행
		MyController controller = hmappings.getController(uri);
		if (controller != null) {
			uri = controller.handleRequest(request, response);
		} else {
			uri = "home";
			request.setAttribute("message", "없는 요청입니다");
		} // if

		// 3 View 처리
		// 최종 viewName 완성
		uri = vresolver.getViewName(uri);
		request.getRequestDispatcher(uri).forward(request, response);

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // class
