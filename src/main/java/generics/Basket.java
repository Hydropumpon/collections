package generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

import static java.util.Collections.addAll;

public class Basket<T extends Food>
{
	private List<T> foods;

	public void replaceWith(List<? extends T> list)
	{
		final int listSize = list.size();
		final int size = foods.size();
		foods.subList(0, Math.min(listSize, size)).clear();
		foods.addAll(0, list);
	}

	public List<T> filter(Predicate<? super T> predicate)
	{
		List<T> result = new ArrayList<>();
		for (T food : foods)
		{
			if (predicate.test(food))
			{
				result.add(food);
			}
		}
		return result;
	}


	public Basket()
	{
		this.foods = new ArrayList<>();
	}

	public T get(int index)
	{
		return this.foods.get(index);
	}

	public void insert(T food)
	{
		this.foods.add(food);
	}

	public void insertAll(List<? extends T> list)
	{
		this.foods.addAll(list);
	}

	public void forEach(Consumer<? super T> consumer)
	{
		for (T food: foods)
		{
			consumer.accept(food);
		}
	}

	public void fill(Supplier<? extends T> supplier, int count)
	{
		for(int i=0; i<count;i++)
		{
			foods.add(supplier.get());
		}
	}

	public void merge(Basket<? extends T> basket)
	{
		foods.addAll(basket.foods);

	}

	public <U> List<U> map(Function<? super T, ? extends U> function)
	{
		List<U> result = new ArrayList<>();
		for (T food : foods)
		{
			result.add(function.apply(food));
		}
		return result;
	}

	public <U extends T> void addIf(List<? extends U> list, BiPredicate<? super  T, ? super U> predicate)
	{
		List<U> candidates = new ArrayList<>();
		Iterator<? extends U> it1 = list.iterator();
		Iterator<? extends T> it2 = foods.iterator();
		while (it1.hasNext() && it2.hasNext())
		{
			U u = it1.next();
			T t = it2.next();
			if (predicate.test(t, u))
			{
				candidates.add(u);
			}

		}
		foods.addAll(candidates);

	}


	public void sort(Comparator<? super T> comparator)
	{
		foods.sort(comparator);

	}

}
