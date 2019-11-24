package multithreading;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

@Data
public class Shared
{
	private Queue<Integer> queue;
	private Semaphore producerSemaphore;
	private Semaphore consumerSemaphore;
	public Shared(Semaphore producerSemaphore, Semaphore consumerSemaphore)
	{
		queue = new LinkedList<>();
		this.consumerSemaphore = consumerSemaphore;
		this.producerSemaphore = producerSemaphore;
	}

	public void add(Integer i) throws InterruptedException
	{
		producerSemaphore.acquire();
		synchronized (queue)
		{
			queue.add(i);
			System.out.println("Put in queue " + i);

		}
		consumerSemaphore.release();
	}

	public Integer fetch() throws InterruptedException
	{
		Integer result;
		consumerSemaphore.acquire();
		synchronized (queue)
		{
			result = queue.remove();
			System.out.println("Read from queue " + result);
		}
		producerSemaphore.release();
		return result;
	}
}
