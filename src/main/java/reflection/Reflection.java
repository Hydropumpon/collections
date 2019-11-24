package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;

public class Reflection
{
	private static final String WHITE_SPACE = " ";
	private Class aClass;
	private StringBuilder data;

	private String getClassDescription()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getAnnotationDescription(aClass.getDeclaredAnnotations()))
					 .append(Modifier.toString(this.aClass.getModifiers())).append(WHITE_SPACE)
					 .append(this.aClass.getName()).append(System.lineSeparator());


		return stringBuilder.toString();
	}

	private <T extends AccessibleObject> String getDescription(List<? extends AccessibleObject> list)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (AccessibleObject classElement : list)
		{
			stringBuilder.append(getAnnotationDescription(classElement.getDeclaredAnnotations()))
						 .append(classElement.toString()).append(System.lineSeparator());
		}
		return stringBuilder.toString();
	}

	private String getAnnotationDescription(Annotation[] annotations)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (Annotation annotation : annotations)
		{
			stringBuilder.append(annotation).append(System.lineSeparator());
		}
		return stringBuilder.toString();
	}

	public String getData()
	{
		return data.toString();
	}


	public Reflection(Object classObject)
	{
		this.aClass = classObject.getClass();
		this.data = new StringBuilder();
		data.append(getClassDescription()).append(getDescription(List.of(aClass.getDeclaredConstructors())))
			.append(getDescription(List.of(aClass.getDeclaredFields())))
			.append(getDescription(List.of(aClass.getDeclaredMethods())));
	}

}
