package jb4;

import java.util.ArrayList;
import java.util.Random;

class ProducerThread implements Runnable {

	private ArrayList<String> buffer;

	public ProducerThread(ArrayList<String> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		String numbers[] = { "2", "2", "4", "2", "4", "7", "8" };

		for (String number : numbers) {
			synchronized (buffer) { // Only one thread per buffer can execute the block
				buffer.add(number);
				try {
					Random random = new Random();
					Thread.sleep(random.nextInt(1000));
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " was interrupted.");
				}
			}
			System.out.println(Thread.currentThread().getName() + " added " + number);
		}
		System.out.println(Thread.currentThread().getName() + " added " + ProducerConsumer.EOF);
		synchronized (buffer) {
			buffer.add(ProducerConsumer.EOF);
		}
	}
}




