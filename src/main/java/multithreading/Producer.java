package multithreading;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable
{
	private Shared shared;

	public Producer(Shared shared)
	{

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
				int randomValue = random.nextInt(100);
				shared.add(randomValue);
				Thread.sleep(1000);

			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}
}
