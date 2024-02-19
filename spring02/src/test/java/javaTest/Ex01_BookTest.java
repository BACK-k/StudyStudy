package javaTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

// Book class
// 맴버 필드 3개(author, title, price) 정의, 이들을 모두 초기화
// 접근 범위 default
class Book {
	String author;
	String title;
	int price;

	Book(String author, String title, int price) {
		this.author = author;
		this.title = title;
		this.price = price;
	}

	public boolean isBook(boolean b) {
		return b;
	}
} // Book

//** 테스트 레벨 4단계
//=> 단위테스트 -> 통합테스트 -> 시스템테스트 -> 인수테스트

//** 테스트 주도 개발 (Test-Driven Development , TDD)
//=> JUnit 활용
// Java 개발시 가장 많이 이용되는 단위테스트 프레임
// 오픈 소스 형태로 개발되며 계속 업그레이드 되고 있음.
// JUnit4 부터 에너테이션 적용 ( Java 가 5 부터 언어적 개선이 이루어짐에 따른 변화임 )

//** @ 종류
//=> @Before - @Test - @After
// -> 하나의 클래스에서 @ 들을 반복사용하면 오류는 안나지만, 앞쪽 @이 실행됨
//=> @ 적용 테스트 메서드 : non static, void 로 정의 해야 함.

//** org.junit.Assert 가 지원하는 다양한 Test용 Method 
//1) assertEquals(a,b) : a와 b의 값(Value) 이 같은지 확인
//2) assertSame(a,b) : 객체 a와b가 같은 객체임(같은 주소) 을 확인
//3) assertTrue(a) : a가 참인지 확인
//4) assertNotNull(a) : a객체가 Null 이 아님을 확인
//5) assertArrayEquals(a,b) : 배열 a와b가 일치함을 확인

//=> 자동 import 가 안되는경우
// -> 프로젝트 우클릭 -> Build Path -> Configure Build Path.. 
//       -> Libraries -> Add Library  -> JUnit4
// -> @Test: import org.junit.Test 확인

//=> pom.xml
// -> junit version : 4.12 로 수정
// -> dependency 추가 ( Spring MVC Mybatis Test )

public class Ex01_BookTest {
//	@Test
	// 1 assertEquals(a,b) : a와 b의 값(value)이 같은지 확인
	public void equalsTest() {
		Book b1 = new Book("엄미현", "죄와벌", 9900);

		// => 값의 일치성 확인 -> true: green 라인 / false: red 라인
//		assertEquals(b1.author, "톨스토이");
		assertEquals(b1.author, "엄미현");
	}

//	@Test
	// 2 assertSame(a,b) : 객체 a와 b가 같은 객체(같은 주소)임을 확인
	public void sameTest() {
		Book b1 = new Book("엄미현", "죄와벌", 9900);
		Book b2 = new Book("엄미현", "죄와벌", 9900);
		Book b3 = new Book("톨스토이", "홍길동전", 9900);

		// assertSame(b1, b2); // false
		b3 = b1;
		assertSame(b1, b3);
	}

//	@Test
	// 3 assertTrue(a) : a가 참인지 확인
	public void trueTest() {
		Book b1 = new Book("엄미현", "죄와벌", 9900);

//		assertTrue(b1.isBook(false)); // false
		assertTrue(b1.isBook(true));
	}

//	@Test
	// 4 assertNotNull(a) : a 객체가 Null이 아님을 확인
	public void nullTest() {
		// => 인스턴스를 정의만 하고 생성은 하지않음
		// 지역변수는 초기화 하지 않으며 오류, 사용시 오류발생
		Book b1 = null;
		Book b2 = new Book("엄미현", "죄와벌", 9900);

//		assertNotNull(b1); // false
		assertNotNull(b2);
	}

	@Test
	// 5 assertArrayEquals(a,b) : 배열 a와 b가 일치함을 확인
	public void arrayEqualsTest() {
		String[] a1 = { "가", "나", "다" };
		String[] a2 = { "가", "나", "다" };
		String[] a3 = { "가", "다", "나" };
		String[] a4 = { "가", "다", "라" };

		// 5.1 두 배열의 값, 순서 모두 동일, 주소값은 다름
//		assertArrayEquals(a1, a2);

		// 5.2 두 배열의 값 동일, 순서, 주소값은 다름
//		assertArrayEquals(a1, a3); // false

		// 5.3 두 배열의 값, 순서, 주소값 모두 다름
//		assertArrayEquals(a1, a4); // false

		// 5.4 Book Type 배열을 2개 만들고 비교
		// 각 배열의 Data는 3개씩
		Book b1 = new Book("엄미현", "응용역학", 9900);
		Book b2 = new Book("엄미현", "유체역학", 19900);
		Book b3 = new Book("톨스토이", "열역학", 29900);
		Book[] ba1 = { b1, b2, b3 };
		Book[] ba2 = { b1, b2, new Book("톨스토이", "열역학", 29900) };
		Book[] ba3 = { b1, b2, b3 };

		// ba2의 세번째가 같은 인스턴스가 아니고 새로 생성한 인스턴스이기 때문에 false
//		assertArrayEquals(ba1, ba2);
		assertArrayEquals(ba1, ba3);
	}
}
