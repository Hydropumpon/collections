package multithreading;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable
{
	private Semaphore producerSemaphore;
	private Semaphore consumerSemaphore;
	private Shared shared;

	public Consumer(Semaphore consumerSemaphore, Semaphore producerSemaphore, Shared shared)
	{
		this.producerSemaphore = producerSemaphore;
		this.consumerSemaphore = consumerSemaphore;
		this.shared = shared;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				System.out.println("Consumer is trying to acquire semaphore");
				consumerSemaphore.acquire();
				System.out.println("Consumer acquired semaphore");
				synchronized (shared)
				{
					System.out.println("Read from queue" + shared.fetch());
				}
				producerSemaphore.release();
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}
}
