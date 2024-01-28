package j01_basic;

class Th1 extends Thread {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Th : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("<Th1완료>");
		System.out.println();
	}
}

class Th2 extends Thread {
	public void run() {
		Th1 th1 = new Th1();

		th1.setDaemon(false);
		th1.start();

		for (int i = 0; i < 10; i++) {
			System.out.println("Th2 : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("<Th2완료>");
		System.out.println();
	}
}

public class ThreadDaemon {

	public static void main(String[] args) {
		Th2 th2 = new Th2();

		th2.setDaemon(true);
		th2.start();

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
