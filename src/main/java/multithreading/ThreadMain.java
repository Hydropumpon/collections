package multithreading;

import java.util.Timer;
import java.util.concurrent.Semaphore;

public class ThreadMain
{
	public static void main(String[] args)
	{

		Semaphore producerSemaphore = new Semaphore(16, true);
		Semaphore consumerSemaphore = new Semaphore(0, true);
		Shared shared = new Shared(producerSemaphore,consumerSemaphore);
		Consumer consumer = new Consumer(shared);
		Producer producer = new Producer(shared);

		for (int i=0; i<5;i++)
		{
			Thread thread = new Thread(producer);
			thread.start();
		}
		for (int i=0; i<3;i++)
		{
			Thread thread = new Thread(consumer);
			thread.start();
		}



	}


}
