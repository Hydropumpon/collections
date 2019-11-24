package multithreading;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class Shared
{
	private Queue<Integer> queue;

	public Shared()
	{
		queue = new LinkedList<>();
	}

	public void add(Integer i)
	{
		queue.add(i);
	}

	public Integer fetch()
	{
		return queue.remove();
	}
}
