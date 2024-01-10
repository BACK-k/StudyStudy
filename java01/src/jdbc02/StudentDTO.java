//	Insert 문제점
//  입력에 필요한 컬럼을 모두 매개변수로 전달 받아야함
//		많으면 처리가 불편 -> 객체화
//		테이블 - entity, 컬럼 - attribute
//		~DTO, Vo, Entity(JPA)	
//			entity(Table) - > Java Class 로 객체화
//	MVC(DBModel view Control)

package jdbc02;
// ** DTO (Data Transfer Object)

//	=> 계층간 데이터 교환을 위한 객체
//	=> 가변객체 mutable object
// 로직을 포함하지않은 getter/setter 메소드만 가질수 있는 순수 Data 전달용
//	=> View 와 통신하며 request, response 처리위해 값의 변경이 유동적 (View Layer)
//	=> 네이밍: ~~DTO 

// ** 결론 
//	=> Spring MyBatis를 쓰는 경우에는 주로 VO라 표현하고 때로는 DTO라 표현하기도 하며
//	Spring JPA를 쓰는 경우에는 Entity 라고 표현한다.
//	그리고 DTO와 VO는 위의 내용처럼 분명한 차이가 있다.

// => 참고 DTO 와 VO 
// https://multifrontgarden.tistory.com/182?category=471239

//---------------------------------------------

// ** ~DTO 정의
//	=> 맴버변수 : private
//	=> 외부에서는 setter/getter 로 접근
//	=> 자료확인시 편리성을 위해 toString() 메서드 오버라이딩

public class StudentDTO {
	// Insert 문제점
	// 입력에 필요한 컬럼을 모두 매개변수로 전달 받아야함
	// 많으면 처리가 불편 -> 객체화
	// 테이블 - entity, 컬럼 - attribute
	// ~DTO, Vo, Entity(JPA)
	// entity(Table) - > Java Class 로 객체화
	// MVC(DBModel view Control)

	// 1. private 맴버변수 정의
	private int sno;
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;

// 객체 초기화 방
// 1. set
// 2. 생성자

// 생성자로 초기화
	// default 생성자, 상속받을 때
	// 생성자를 임의로 지정하지 않으면 프로그램에서 default 생성자를 만들지만
	// 생성자를 임의로 지정해서 만들면 defualt 생성자를 만들지 않는다
	public StudentDTO() {

	}

	// 모든값을 초기화 하는 생성자
	public StudentDTO(int sno, String name, int age, int jno, String info, double point) {
		this.sno = sno;
		this.name = name;
		this.age = age;
		this.jno = jno;
		this.info = info;
		this.point = point;
	}

	// 2. getter, setter
	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	// 3. toString
	// Object에 정의된 toStirng() 메서드를 활용해서
	// 객체의 값을 편리하게 확인하기위해 사용
	@Override
	public String toString() {
		return "StudentDTO [sno=" + sno + ", name=" + name + ", age=" + age + ", jno=" + jno + ", info=" + info
				+ ", point=" + point + "]";
	}

} // class