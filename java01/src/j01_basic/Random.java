package j01_basic;

import java.util.Random;

public class Random01 {

	public static void main(String[] args) {
		Random ranIdx = new Random();
		int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		// 원본 배열의 위치를 바꾸기
		
		for(int i = 0, shakeIdx, t; i < data.length; i++) { // 난수를 입력받고
			shakeIdx = ranIdx.nextInt(data.length);
			// 스왑을 통해 위치 변경
			// i번째 있는 값을 t에 저장
			t = data[i];
			System.out.print(i + " ");
			System.out.print(data[i] + " ");
			System.out.print(t + " /");
			// shakeIdx번째에 있는 값을 i번째에 넣어주기
			data[i] = data[shakeIdx];
			System.out.print(shakeIdx + " ");
			System.out.print(data[shakeIdx] + " /");
			// 인덱스 i번째에 있던 값을 shakeIdx번째에 넣어주기
			data[shakeIdx] = t;
			System.out.print(t+ " ");
			System.out.print(shakeIdx + " ");
			System.out.println(data[shakeIdx] + " ");
		}
		
		for(int i: data) {
			System.out.print(i + " ");
		}
		
	}

}