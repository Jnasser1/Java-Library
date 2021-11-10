package jb4;

public class MyThread2 implements Runnable {

	public Object object1;
	public Object object2;

	public MyThread2(Object object1, Object object2) {
		this.object1 = object1;
		this.object2 = object2;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (object2) {
				System.out.println("Thread2  <-> object2");
				synchronized (object1) {
					System.out.println("Thread2 <-> object1");
				}
			}
		}

	}

}