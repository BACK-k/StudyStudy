package j01_basic;

import jdbc02.JoDTO;

// 정하지 않은 타입을 사용하는 클래스임을 표시 <T>
class StoreG<T> {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
} // StoreG

// 배열 출력 Generic
class GenArray<T> {
	private T[] arr;

	public T[] getData() {
		return arr;
	}

	public void setData(T[] arr) {
		this.arr = arr;
	}

	public void arrayPrint() {
		for (T a : arr) {
			System.out.println(a);
		} // for
	} // arrPrint
} // GenArray

// Number의 후손들만 올 수 있음
class Box<T extends Number> {
	private T data;

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}
}

public class Gn01_StoreTest {

	public static void main(String[] args) {
		// Generic
		// 자료를 담을 수 있는 자료 구조를 만듦
		// Generic Type을 생략하면 Object로 지정됨
		StoreG g1 = new StoreG();

		// 제네릭 클래스의 타입 인자 제한
		Box<Integer> b1 = new Box();
		b1.setData(12345);

		// 제네릭 클래스의 타입 인자가 Number로 제한되어 컴파일 오류
//		Box<String> b2 = new Box();

		// 생성시 Type 제한의 경우는 확인이 필요
		// 타입에 ? 를 넣어주면 모든 클래스나 인터페이스 타입 가능
//		StoreG<?> g11 = new StoreG();
//		g11.setData(111);
		// JoDTO의 자손들만 올 수 있다
//		StoreG<? extends JoDTO> g12 = new StoreG();
//		g12.setData(new JoDTO());

		StoreG<String> g2 = new StoreG<String>();
		g2.setData("스트링만 가능");
//		g2.setData(12345); // Type이 일치하지 않으면 컴파일 오류 발생

		StoreG<Integer> g3 = new StoreG<Integer>();
		g3.setData(12345);
//		g3.setData(123.456); // 컴파일 오류 발생

		// GenArray Test
		// 1. String
		String[] ss = { "가", "나", "Do", "Re", "Mi" };
		GenArray<String> ga1 = new GenArray<String>();
		ga1.setData(ss);
		ga1.arrayPrint();

		// 2. Integer
		Integer[] ii = { 1, 2, 3, 4, 5 };
		GenArray<Integer> ga2 = new GenArray<Integer>();
		ga2.setData(ii);
		ga2.arrayPrint();

		// 3. Character(char)
		Character[] cc = { 'A', 'B', 'C', '디', '이' };
		GenArray<Character> ga3 = new GenArray<Character>();
		ga3.setData(cc);
		ga3.arrayPrint();

		// 4. 객체
		JoDTO[] jj = { new JoDTO(), new JoDTO(), new JoDTO() };
		GenArray<JoDTO> ga4 = new GenArray<JoDTO>();
		ga4.setData(jj);
		ga4.arrayPrint();

	} // main

} // class