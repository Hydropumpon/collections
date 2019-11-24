package generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GenericMain
{
	public static void main(String[] args)
	{
		List<Fruit> fruits = new ArrayList<>();
		fruits.add(new Fruit("Mango"));
		fruits.add(new Fruit("Banana"));

		List<Apple> apples = new ArrayList<>();
		apples.add(new Apple("Winesapp"));
		apples.add(new Apple("Golden"));

		Basket<Fruit> basket = new Basket<>();
		basket.insert(new Fruit("Orange"));
		basket.insert(new Fruit("Apple"));
		basket.insertAll(fruits);

		basket.insert(new Apple("apple"));
		basket.insertAll(apples);
		basket.forEach(System.out::println);

		Consumer<Food> foodConsumer = food -> System.out.println(food.getName());
		Predicate<Fruit> fruitPredicate = fruit -> fruit.getName().equals("Banana");

		basket.forEach(foodConsumer);
		basket.replaceWith(apples);
		basket.replaceWith(fruits);
		System.out.println("///////");
		basket.forEach(foodConsumer);

		System.out.println("///////");

		List<Fruit> filter = basket.filter(fruitPredicate);
		filter.forEach(fruit -> System.out.println(fruit.getClass()));
		System.out.println("///////");

		Supplier<Apple> supplier = () -> apples.get(1);
		basket.fill(()->new Apple("Dared"),2);
		basket.fill(supplier, 1);

		basket.sort(Comparator.comparing(Food::getName));
		basket.forEach(fruit -> System.out.println(fruit.getName()));

		List<String> names = basket.map(Food::getName);
		names.forEach(System.out::println);
	}
}
