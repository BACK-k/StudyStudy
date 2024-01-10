package jdbc02;

import java.util.List;
//** Controller

//=> 요청 : 요청분석 -> 담당 Service -> Service 는 DAO 
//=> 결과 : DAO -> Service -> Controller
//=> View : Controller -> View 담당 (Java:Console // Web:JSP, Html.., React)

public class StudentController {
	// 전역변수 정의
	StudentService service = new StudentService();

	// view 역할 메서드 정의
	// selectList
	// 출력할 내용을 매개변수로 전달받아야한다
	public void printList(List<StudentDTO> list) {
		System.out.println("** Student List **");
		// 출력자료 존재 확인
		if (list != null) {
			// List 출력
			for (StudentDTO s : list) {
				System.out.println(s);
			}
		} else {
			System.out.println("** selectList 결과가 1건도 없음 **");
		}
	}

	public void selectOne(StudentDTO dto) {
		System.out.printf("** Student %d **\n", dto.getSno());
		if (dto != null) {
			System.out.println(dto);
		} else {
			System.out.println("** selectOne 결과가 1건도 없음 **");
		}
	}

	public void insert(int dto) {
		System.out.println("** Insert **");
		if (dto > 0) {
			System.out.println("** insert 성공 **");
		} else {
			System.out.println("** insert 실패 **");
		}
	}

	public void update(int dto) {
		System.out.println("** Update");
		if (dto > 0) {
			System.out.println("** update 성공 **");
		} else {
			System.out.println("** update 실패 **");
		}
	}

	public void delete(int dto) {
		System.out.println("** Delete **");
		if (dto > 0) {
			System.out.println("** delete 성공 **");
		} else {
			System.out.println("** delete 결과가 1건도 없음 **");
		}
	}

	public static void main(String[] args) {
		// static이 아니라서 생성해줘야함
		StudentController sc = new StudentController();

		// selectList 요청
		// 요청에 해당하는 service의 메서드를 호출
		// 처리 결과(sc.service.selectList())를 view(printList)에게 전달해서 출력함
		sc.printList(sc.service.selectList());

		// selectOne
		sc.selectOne(sc.service.selectOne(5));

		// insert
		sc.insert(sc.service.insert(new StudentDTO(0, "추가용", 19, 19, "insert", 0)));

		// update
		sc.update(sc.service.update(new StudentDTO(24, "변경용", 24, 24, "update", 0)));

		// delete
		sc.delete(sc.service.delete(19));

	} // main
}