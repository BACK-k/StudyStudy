package aop03;

public class Girl implements Programmer {
	@Override
	public void doStudying() throws Exception {
		System.out.println("열심히 게시판을 만듭니다 => 핵심적 관심사항 ");
		// Test를 위해 늘 실패으로 처리
		// 항상 true가 되도록
//		if (new Random().nextBoolean()) {
		if (true) {
			// 실패
			throw new Exception(" Error 500 * 100 => 예외발생 ");
		}
	} // doStudying
} // class
