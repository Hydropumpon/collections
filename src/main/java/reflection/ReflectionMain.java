package reflection;

import collections.List;
import collections.impl.ArrayList;

public class ReflectionMain
{
	public static void main(String[] args)
	{
		List<Integer> list = new ArrayList<>();
		Reflection reflection = new Reflection(list);
		System.out.println(reflection.getData());
	}
}

