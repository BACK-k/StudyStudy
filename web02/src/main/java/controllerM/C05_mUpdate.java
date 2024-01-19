package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mupdate")
public class C05_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C05_mUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 요청분석
		// request의 한글(post 요청시 필수), Parameter처리
		// 성공 -> MyInfo로 이동 memberDetail.jsp
		// 실패 -> 재수정 유도 updateForm.jsp
		// 출력객체 apple가 필요하기때문에
		// Redirect를 사용하거나 전달된 값들을 apple에 보관
		request.setCharacterEncoding("UTF-8");
		String uri = "member/memberDetail.jsp";

		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setJno(Integer.parseInt(request.getParameter("jno")));
		dto.setInfo(request.getParameter("info"));
		dto.setPoint(Double.parseDouble(request.getParameter("point")));
		dto.setBirthday(request.getParameter("birthday"));
		dto.setRid(request.getParameter("rid"));

		// 결과 출력을 위해 apple에 보관
		request.setAttribute("apple", dto);

		// 2 Service처리
		// Service 객체 생성
		MemberService service = new MemberService();
		if (service.update(dto) > 0) {
			// 성공
			request.setAttribute("message", " 회원정보가 변경되었습니다 ");
			// 성공 후 session에 보관한 이름을 변경
			request.getSession().setAttribute("loginName", dto.getName());
		} else {
			// 실패
			request.setAttribute("message", " 회원정보 변경에 실패했습니다 ");
			uri = "member/updateForm.jsp";
		}

		// 3 View 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // class
