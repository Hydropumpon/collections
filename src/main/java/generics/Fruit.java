package generics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class Fruit extends Food
{
	public Fruit(String name)
	{
		super(name);
	}
}
