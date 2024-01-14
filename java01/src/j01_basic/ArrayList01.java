package j01_basic;

import java.util.ArrayList;

public class ArrayList01 {

	public static void main(String[] args) {
		// ArrayList : 재할당이 가능한 배열
		// 읽는 속도는 빠르지만, 삽입과 삭제 속도가 느리다
		ArrayList<String> arName = new ArrayList<String>();
		ArrayList<String> arName1 = new ArrayList<String>(5);
		
		arName.add("A");
		arName.add("B");
		arName.add("C");

		arName1.add("A");
		arName1.add("B");
		arName1.add("C");
		arName1.add("D");
		
		// 전체 요소의 수
		System.out.println(arName.size());
		System.out.println(arName1.size());
		System.out.println();
		
		// get(int index) :  해당 인덱스 위치의 요소값 리턴
		for(int i = 0; i < arName.size(); i++) {
			System.out.println(arName.get(i) + " ");
		}
		System.out.println();
		System.out.println();
		
		// 동적 배열이므로 foreach
		for(String s: arName1) {
			System.out.println(s + " ");
		}
		System.out.println();
		System.out.println();
		
		// 해당 인덱스 위치에 요소값 추가 삽입, 생략하면 가장 마지막 위치에 추가
		arName.add(1, "B");
		// toString() 메서드를 통해 루프 이용없이 전체 요소값 리턴
		System.out.println(arName1);
	}
	

}