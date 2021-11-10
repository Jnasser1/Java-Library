package jb4;

import java.util.ArrayList;

public class ProducerConsumer {
	// A constant to display end of data.
	public static final String EOF = "No more data";

	public static void main(String[] args) {
		ArrayList<String> buffer = new ArrayList<>();

		Thread producerThread = new Thread(new ProducerThread(buffer));
		producerThread.setName("producerThread");

		Thread consumerThread1 = new Thread(new ConsumerThread(buffer));
		consumerThread1.setName("consumerThread1");

		Thread consumerThread2 = new Thread(new ConsumerThread(buffer));
		consumerThread2.setName("consumerThread2");

		producerThread.start();
		consumerThread1.start();
		consumerThread2.start();
	}
}