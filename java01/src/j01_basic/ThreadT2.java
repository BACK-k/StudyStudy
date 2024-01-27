package j01_basic;

public class ThreadT2 {

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("여기는?");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}
		}).start();

		for (int i = 0; i < 10; i++) {
			System.out.println("출력");
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
		}
	}
}
