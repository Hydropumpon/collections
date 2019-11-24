package multithreading;

import java.util.concurrent.Semaphore;

public class ThreadMain
{
	public static void main(String[] args)
	{
		Shared shared = new Shared();
		Semaphore producerSemaphore = new Semaphore(16, true);
		Semaphore consumerSemaphore = new Semaphore(0, true);
		Consumer consumer = new Consumer(consumerSemaphore,producerSemaphore,shared);
		Producer producer = new Producer(producerSemaphore,consumerSemaphore,shared);

		Thread consumerThread1 = new Thread(consumer, "consumer1");
		Thread consumerThread2 = new Thread(consumer, "consumer2");
		Thread producerThread1 = new Thread(producer, "producer1");
		Thread producerThread2 = new Thread(producer, "producer2");

		consumerThread1.start();
		consumerThread2.start();
		producerThread1.start();
		producerThread2.start();

	}


}
