package proxy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User implements UserInterface
{
	private String firstName;
	private String lastName;
	private Integer salary;

	@Override
	public String getFirstName()
	{
		return firstName;
	}

	@Override
	public String getLastName()
	{
		return lastName;
	}

	@Override
	@Loggable
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	@Loggable
	@Override
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Loggable
	@Override
	public Integer getSalary()
	{
		return salary;
	}

	@Loggable
	@Override
	public void setSalary(Integer salary)
	{
		this.salary = salary;
	}
}
