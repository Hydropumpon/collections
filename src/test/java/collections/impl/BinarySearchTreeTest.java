package collections.impl;

import collections.Iterator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest
{
	private static final String[] TEST = {"a","b","c"};
	private static final String IN_ORDER = "abc";



	@Test
	public void iterator()
	{
		BinarySearchTree<String> tree = new BinarySearchTree<>();
		for(String str: TEST)
		{
			tree.add(str);
		}
		StringBuilder result = new StringBuilder();
		Iterator<String> iterator = tree.iterator();
		while(iterator.hasNext())
		{
			result.append(iterator.next());
		}
		Assert.assertEquals(IN_ORDER, result.toString());
	}

	@Test
	public void add()
	{
		BinarySearchTree<String> tree = new BinarySearchTree<>();
		for(String str: TEST)
		{
			tree.add(str);
		}
		Assert.assertEquals(IN_ORDER, tree.show());
	}

	@Test
	public void reverse()
	{
		BinarySearchTree<String> tree = new BinarySearchTree<>();
		for(String str: TEST)
		{
			tree.add(str);
		}
		tree.reverse();
		Assert.assertEquals(new StringBuilder(IN_ORDER).reverse().toString(), tree.show());
	}
}