package multithreading;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable
{
	private Semaphore producerSemaphore;
	private Semaphore consumerSemaphore;
	private Shared shared;

	public Producer(Semaphore producerSemaphore, Semaphore consumerSemaphore, Shared shared)
	{
		this.producerSemaphore = producerSemaphore;
		this.consumerSemaphore = consumerSemaphore;
		this.shared = shared;
	}

	@Override
	public void run()
	{
		Random random = new Random();
		while (true)
		{
			try
			{
				System.out.println("Producer is trying to acquire semaphore");
				producerSemaphore.acquire();
				System.out.println("Producer acquired semaphore");
				int randomValue = random.nextInt(100);
				System.out.println("Put in queue" + randomValue);
				synchronized (shared)
				{
					shared.add(randomValue);
				}
				consumerSemaphore.release();
				Thread.sleep(1000);

			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}
}
