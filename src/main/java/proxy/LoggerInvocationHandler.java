package proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.stream.Stream;

@Slf4j
public class LoggerInvocationHandler implements InvocationHandler
{
	private Object original;

	public LoggerInvocationHandler(Object original)
	{
		this.original = original;
	}

	private boolean isArgsNotNull(Object[] args)
	{
		return args != null;
	}

	private Class[] getTypes(Object[] args)
	{
		if (isArgsNotNull(args))
		{
			return Stream.of(args).map(Object::getClass).toArray(Class[]::new);
		}
		return null;
	}

	private void writeLog(Method method, Object[] args)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Method ").append(method.getName()).append(" invoked with args: ");
		if (isArgsNotNull(args))
		{
			Stream.of(args).forEach((object) -> stringBuilder.append(object).append(" "));
		}
		log.info(stringBuilder.toString());

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		try
		{
			Class[] types = getTypes(args);
			Method originalMethod = original.getClass().getMethod(method.getName(), types);
			Loggable declaredAnnotation = originalMethod.getDeclaredAnnotation(Loggable.class);
			if (declaredAnnotation != null)
			{
				writeLog(method, args);
			}
			return method.invoke(original, args);
		} catch (NoSuchMethodException e)
		{
			return method.invoke(original, args);
		}
	}
}
