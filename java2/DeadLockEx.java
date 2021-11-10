package jb4;

// Creates a deadlock using opposite synchronization.
// Thread1 locks object1, Thread2 locks object2,
// They wait forever on the next resource, object2 and object1 resp.
public class DeadLockEx {
	public static void main(String[] args) throws InterruptedException {
		Object object1 = new Object();
		Object object2 = new Object();

		MyThread2 runner2 = new MyThread2(object1, object2);
		MyThread1 runner1 = new MyThread1(object1, object2);

		Thread thread2 = new Thread(runner2); // Constructor Thread(Runnable r)
		Thread thread1 = new Thread(runner1);

		thread1.start();
		thread2.start();

		// While thread2 is alive or thread1 is alive there is no deadlock.
		while (!thread2.isAlive() || !thread1.isAlive()) {
			System.out.println("No deadlock occuring.");
		}

	}
}