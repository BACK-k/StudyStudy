package jdbc02;

import java.util.List;

// service의 역할
// Controller의 요청에 해당하는 DAO의 메서드를 실행
// Controller와 DAO의 중간에 위치하면서 이 둘의 의존성(필요성)을 낮춤
// 의존성 : 다른 클래스를 수정할 때 영향을 받는 정도

public class StudentService {
	// 전역변수 정의
	StudentDAO dao = new StudentDAO();

	// selectList
	public List<StudentDTO> selectList() {
		return dao.selectList();
	}

	// selectOne
	public StudentDTO selectOne(int sno) {
		return dao.selectOne(sno);
	}

	// insert
	// 클래스를 매개변수로 전달해 클래스의 주소를 전달함
	public int insert(StudentDTO dto) {
		return dao.insert(dto);
	}

	// update
	public int update(StudentDTO dto) {
		return dao.update(dto);
	}

	// delete
	public int delete(int sno) {
		return dao.delete(sno);
	}
} // class