package proxy;

import java.lang.reflect.Proxy;

public class LoggerMain
{

	public static void main(String[] args)
	{
		UserInterface original = new User("Alex", "Shirokov", 1000000);
		UserInterface proxyInstance = (UserInterface) Proxy.newProxyInstance(
				LoggerMain.class.getClassLoader(),
				new Class[] { UserInterface.class },
				new LoggerInvocationHandler(original));

		System.out.println(proxyInstance.getFirstName());
		System.out.println(proxyInstance.getLastName());
		System.out.println(proxyInstance.getSalary());
		proxyInstance.setSalary(10000);
		proxyInstance.setFirstName("Igor");
		proxyInstance.setLastName("Zubov");
	}

}
