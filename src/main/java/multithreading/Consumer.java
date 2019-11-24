package multithreading;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable
{
	private Shared shared;

	public Consumer(Shared shared)
	{
		this.shared = shared;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				shared.fetch();
				Thread.sleep(700);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}
}
