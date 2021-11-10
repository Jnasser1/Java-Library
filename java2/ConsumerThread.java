package jb4;

import java.util.ArrayList;
import java.util.Random;

class ConsumerThread implements Runnable {
	private ArrayList<String> buffer;

	public ConsumerThread(ArrayList<String> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {

		while (true) {
			synchronized (buffer) {
				if (buffer.isEmpty()) {                            // Consumer waits until buffer is non-empty.
					continue;
				}
				if (buffer.get(0).equals(ProducerConsumer.EOF)) {  // End of data.
					System.out.println(Thread.currentThread().getName() + " exiting.");
					break;
				} else {
					System.out.println(Thread.currentThread().getName() + " removed " + buffer.remove(0));
					try {
						Random random = new Random();
						Thread.sleep(random.nextInt(1000));
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getName() + " interrupted.");
					}
				}
			}
		}
	}
}
