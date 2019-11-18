package collections.impl;

import org.junit.Assert;
import org.junit.Test;

public class HashMapTest
{
	private static final String TEST_KEY = "key_1";
	private static final Integer TEST_VALUE = 10;


	@Test
	public void insert()
	{
		HashMap<String, Integer> hashMap = new HashMap<>();
		Assert.assertTrue(hashMap.insert(TEST_KEY, TEST_VALUE));
		Assert.assertEquals(1, hashMap.getSize());
	}

	@Test
	public void get()
	{
		HashMap<String, Integer> hashMap = new HashMap<>();
		hashMap.insert(TEST_KEY, TEST_VALUE);
		Integer result = hashMap.get(TEST_KEY);
		Assert.assertEquals(result, TEST_VALUE);
	}

	@Test
	public void delete()
	{
		HashMap<String, Integer> hashMap = new HashMap<>();
		hashMap.insert(TEST_KEY, TEST_VALUE);
		Assert.assertTrue(hashMap.delete(TEST_KEY));
		Assert.assertEquals(0, hashMap.getSize());
	}
}