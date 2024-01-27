package j01_basic;

class Thread1 extends Thread {
	public Thread1() {
	}

	public Thread1(String name) {
		super(name);
	}
}

class Thread2 extends Thread {
	public Thread2() {
	}

	public Thread2(String name) {
		super(name);
	}
}

public class ThreadMul {

	public static void main(String[] args) {
		Thread1 thread1 = new Thread1();
		Thread2 thread2 = new Thread2();

		System.out.println(thread1.getName());
		System.out.println(thread2.getName());
		System.out.println(Thread.currentThread().getName());
		System.out.println();

		thread1.setName("스레드1");
		thread2.setName("스레드2");
		Thread.currentThread().setName("메인스레드");
		System.out.println(thread1.getName());
		System.out.println(thread2.getName());
		System.out.println(Thread.currentThread().getName());
		System.out.println();

		Thread1 thread1_2 = new Thread1("스레드1-2");
		Thread2 thread2_2 = new Thread2("스레드2-2");
		System.out.println(thread1_2.getName());
		System.out.println(thread2_2.getName());
	}

}
