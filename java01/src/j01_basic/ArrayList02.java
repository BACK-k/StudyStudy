package j01_basic;

import java.util.ArrayList;

public class ArrayList02 {
	public static void main(String[] args) {
	ArrayList<String> arName = new ArrayList<String>();
	
	arName.add("A");
	arName.add("B");
	arName.add("A");
	arName.add("D");
	arName.add("A");
	
	// 배열이 비었는지 확인
	System.out.println(arName.isEmpty());
	
	// 해당 인덱스 위치 요소값 변경
	arName.set(2, "C");
	
	// 전달된 인수 객체와 일치하는 배열 요소 인덱스 검색, 없으면 -1
	System.out.println(arName.indexOf("C"));
	
	// 역순 검색
	System.out.println(arName.lastIndexOf("C"));
	
	// 해당 인덱스 위치 요소값 삭제
	arName.remove(arName.lastIndexOf("D"));
	System.out.println(arName.toString());
	
	// 모든 요소 값 삭제
	arName.clear();
	System.out.println(arName.toString());
	System.out.println(arName.isEmpty());
	}
}