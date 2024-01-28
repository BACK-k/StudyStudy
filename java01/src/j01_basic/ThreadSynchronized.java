package j01_basic;

class Num {
	int sum = 0;
	int n = 0;
}

class Th11 extends Thread {
	Num num;

	public Th11(Num num) {
		this.num = num;
	}

	public void run() {
		for (int i = 1;; i++) {
			synchronized (num) {
				num.notify();
				if (i >= 51)
					break;
				num.n++;
				num.sum += num.n;

				System.out.println(i + " : " + num.n);
				try {
					num.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Th22 extends Thread {
	Num num;

	public Th22(Num num) {
		this.num = num;
	}

	public void run() {
		for (int i = -1;; i--) {
			synchronized (num) {
				num.notify();
				if (i <= -51)
					break;
				num.n++;
				num.sum += num.n;

				System.out.println(i + " : " + num.n);
				try {
					num.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

public class ThreadSynchronized {

	public static void main(String[] args) {
		Num num = new Num();
		Th11 th11 = new Th11(num);
		Th22 th22 = new Th22(num);

		th11.start();
		th22.start();

		try {
			th11.join();
			th22.join();
		} catch (InterruptedException e) {
		}

		System.out.println();
		System.out.println(num.sum);
	}

}
