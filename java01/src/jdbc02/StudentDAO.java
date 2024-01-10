package jdbc02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc01.DBConnection;

// DAO(Data Access Object)
//	SQL 구문 처리
//	CRUD 구현 
//	Create(Insert), Read(selectList, selectOne), Update, Detete

// 예제1. DBStart와 -DAO와 차이점
// 결과를 직접 처리하지않고 요청자에게 제공해야됨
// 메서드 역할별로 처리결과를 return 해야함
// 그러므로 특히 select 결과를 잘 전달하기위해 결과를 객체화해야함

public class StudentDAO {
	// 1. JDBC API 에 정의된 필요한 객체들을 전역변수로 정의
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	// 결과를 처리
	private static ResultSet rs;
	// 모든 SQL문이 java 안에선 string
	private static String sql;

	// selectList
	// 컬렉션 : 자료를 담아놓는 형태
	// List : 순차적으로 처리할 수 있도록 순서가 있는 형태로 데이터를 보관
	// ArrayList : 배열과 유사, 연속된 공간을 차례대로 할당
	// LinkedList : 앞뒤를 엮어 서로의 주소를 기억해, 중간에 자료를 삽입하기에 유리
	// Map : key와 value가 존재해, key를 이용해 value를 가져온다, key가 다르면 중복허용
	// Set : value 값만 존재, 중복을 허용하지 않음
	public List<StudentDTO> selectList() {
		sql = "select * from student";
		// 좌측에 조상 우측에 후손을 정의해 다용성을 확보
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// 결과의 존재여부 확인
			// 결과가 존재하면 list에 담아주기
			// 결과가 없으면 return null
			if (rs.next()) {
				do {
					// 기본 자료형 : int, char, boolean...
					// 선언을 했을 때 소문자로 시작, 자바의 컴파일러에 정의된 예약어
					// 할당된 정해진 크기의 공간에 값을 저장
					// 참조 자료형 : String
					// 모든 클래스는 참조 자료형, 선언시 대문자로 시작
					// 할당된 공간에 주소를 전달
					// 할당된 공간에 있던 주소에 값을 변경해 덮어씌우는 수정이 아니라
					// 새로운 값을 만들고, 그 주소를 할당된 공간에 가져온다
					// StringBuffer, Builder는 참조 자료형이지만
					// 자기 전용으로 할당받은 주소에 연결된 공간에 값을 저장하고 계속해서 수정하는 형식
//					StudentDTO dto = new StudentDTO();
//					dto.setSno(rs.getInt(1));
//					dto.setName(rs.getString(2));
//					dto.setAge(rs.getInt(3));
//					dto.setJno(rs.getInt(4));
//					dto.setInfo(rs.getString(5));
//					dto.setPoint(rs.getInt(6));

					// 생성자 이용
					StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5), rs.getInt(6));
					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		} // try_catch
	} // selectList

	// selectOne
	// 기본자료형 매개변수 (Call By Value)
	public StudentDTO selectOne(int sno) {
		sql = "select * from student where sno = ?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, sno);
			rs = pst.executeQuery();

			if (rs.next()) {
				StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6));
				return dto;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	}

	// insert
	public int insert(StudentDTO dto) {
		sql = "insert into student(name, age, jno, info) values(?, ?, ?, ?)";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	}

	// update
	public int update(StudentDTO dto) {
		sql = "update student set name = ?, age = ?, jno = ?, info = ? where sno = ?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			pst.setInt(5, dto.getSno());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	}

	// delete
	public int delete(int sno) {
		sql = "delete from student where sno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, sno);
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
		}
		return 0;
	}

} // class