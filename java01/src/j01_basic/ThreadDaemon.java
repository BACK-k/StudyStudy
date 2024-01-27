package j01_basic;

class Th extends Thread {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Th : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("<Th완료>");
				System.out.println();
			}
		}
	}
}

public class ThreadDaemon {

	public static void main(String[] args) {
		Th th = new Th();

		th.setDaemon(true);
		th.start();

		for (int i = 0; i < 10; i++) {
			System.out.println("메인스레드 : " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			System.out.println("메인스레드완료");
		}
	}

}
