package generics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
public abstract class Food
{
	protected final String name;

	public Food(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

}
