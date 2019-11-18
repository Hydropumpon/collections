package collections.impl;

import collections.Iterator;
import collections.List;
import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest
{
	private static final String TEST = "test";

	@Test
	public void add()
	{
		List<String> list = new ArrayList<>();
		Assert.assertTrue(list.add(TEST));
	}

	@Test
	public void remove()
	{
		List<String> list = new ArrayList<>();
		list.add(TEST);
		Assert.assertEquals(TEST,list.remove(0));
	}

	@Test
	public void getSize()
	{
		ArrayList<String> list = new ArrayList<>();
		list.add(TEST);
		Assert.assertEquals(1,list.getSize());
	}

	@Test
	public void get()
	{
		List<String> list = new ArrayList<>();
		list.add(TEST);
		Assert.assertEquals( TEST,list.get(0));
	}

	@Test
	public void iterator()
	{
		List<String> list = new ArrayList<>();
		list.add(TEST);
		Iterator<String> iterator = list.iterator();
		int elements = 0;
		String result = null;
		while (iterator.hasNext())
		{
			result = iterator.next();
			elements++;
		}
		Assert.assertEquals(TEST,result);
		Assert.assertEquals(1,elements);

	}
}
