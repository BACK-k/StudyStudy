package j01_basic;

class Data implements Runnable {
	String ar = "0123456789";
	int idx = -1;

	public synchronized void run() {
		for (;;) {
			this.notify();
			if (idx >= ar.length() - 1) {
				break;
			}

			idx++;
			System.out.println(ar.charAt(idx) + " ");
			System.out.println(Thread.currentThread().getName());

			try {
				this.wait(0);
			} catch (InterruptedException e) {
			}
		}
	}

}

public class ThreadSyncMethod {

	public static void main(String[] args) {
		Data data = new Data();
		Thread thread1 = new Thread(data);
		Thread thread2 = new Thread(data);

		thread1.start();
		thread2.start();
	}

}
