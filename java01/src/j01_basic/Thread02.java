package j01_basic;

class TestThread implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("여기는?");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
	}
}

public class Thread02 {

	public static void main(String[] args) {
		Thread testThread = new Thread(new TestThread());

		testThread.start();

		for (int i = 0; i < 10; i++) {
			System.out.println("출력");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
	}

}
