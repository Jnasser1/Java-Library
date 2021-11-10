package jb4;

public class MyThread1 implements Runnable {

	public Object object1;
	public Object object2;

	public MyThread1(Object object1, Object object2) {
		this.object1 = object1;
		this.object2 = object2;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (object1) {
				System.out.println("Thread1 <-> object1");
				synchronized (object2) {
					System.out.println("Thread1 <-> object2");
				}
			}
		}

	}

}