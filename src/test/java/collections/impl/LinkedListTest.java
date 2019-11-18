package collections.impl;

import collections.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest
{
	private static final Integer[] TEST = {10, 20 ,30};
	@Test
	public void add()
	{
		LinkedList<Integer> list = new LinkedList<>();
		Assert.assertTrue(list.add(TEST[0]));
		Assert.assertEquals(1,list.getSize());

	}

	@Test
	public void addIndex()
	{
		LinkedList<Integer> list = new LinkedList<>();
		list.add(TEST[0]);
		Assert.assertTrue(list.add(TEST[1], 0));
		Assert.assertEquals( 2,list.getSize());
	}

	@Test
	public void remove()
	{
		LinkedList<Integer> list = new LinkedList<>();
		list.add(TEST[0]);
		Integer remove = list.remove(0);
		Assert.assertEquals(TEST[0],remove);
	}

	@Test
	public void get()
	{
		LinkedList<Integer> list = new LinkedList<>();
		for (Integer integer : TEST)
		{
			list.add(integer);
		}
		Integer result = list.get(1);
		Assert.assertEquals( TEST[1], result);
	}

	@Test
	public void iterator()
	{

		LinkedList<Integer> list = new LinkedList<>();
		list.add(TEST[0]);
		Iterator<Integer> iterator = list.iterator();
		Integer result = null;
		int elements = 0;
		while (iterator.hasNext())
		{
			result = iterator.next();
			elements++;
		}
		Assert.assertEquals(TEST[0],result);
		Assert.assertEquals(1, elements);
	}


}
