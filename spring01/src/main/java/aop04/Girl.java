package aop04;

public class Girl implements Programmer {
	@Override
	public String doStudying(int n) throws Exception {
		System.out.printf("열심히 게시판을 %d개 만듭니다 => 핵심적 관심사항 \n", n);
		// Test를 위해 늘 실패으로 처리
		// 항상 true가 되도록
//		if (new Random().nextBoolean()) {
		if (true) {
			// 실패
			throw new Exception(" Error 500 * 100 => 예외발생 ");
		}

		return "취업성공 시급2500만원";
	} // doStudying
} // class
