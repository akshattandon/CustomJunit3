package junit.framework;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortMethods {


	private static final char[] METHOD_SEPARATORS = {1, 7};

	public Method[] getSeqTestNames(Method[]  methods, Class theClass)throws Throwable{
		final List<MethodPosition> listMethodPos = new ArrayList<MethodPosition>();

		for(Method test : methods){
			MethodPosition methodPos  = getIndexOfMethodPosition(theClass, test.getName(),test);
			if(isTestMethod(test))
				listMethodPos.add(methodPos);
		}
		Collections.sort(listMethodPos); 
		Method[] methods1 = new Method[listMethodPos.size()];
		for(int i=0;i<listMethodPos.size();i++)
		{
			methods1[i]=listMethodPos.get(i).getTestMethod();
		}

		return methods1;

	}

	private MethodPosition getIndexOfMethodPosition(final Class aClass, final String methodName, Method method) throws Throwable{
		MethodPosition methodPosition;
		for (final char methodSeparator : METHOD_SEPARATORS) {
			methodPosition = getIndexOfMethodPosition(aClass, methodName, methodSeparator,method);
			return methodPosition;

		}
		return new  MethodPosition(-1, -1,methodName, method);
	}
	private MethodPosition getIndexOfMethodPosition(final Class aClass, final String methodName, final char methodSeparator,Method method) throws Throwable{
		final InputStream inputStream = aClass.getResourceAsStream(aClass.getSimpleName() + ".class");
		final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
		final String methodNameWithSeparator = methodName ;
	
			try {
				String line;
				while ((line = lineNumberReader.readLine()) != null) {
					if (line.contains(methodNameWithSeparator)) {
						return new MethodPosition(lineNumberReader.getLineNumber(), line.indexOf(methodNameWithSeparator),methodName ,method);
					}
				}
			} catch(Exception e){
				throw new Throwable(e); 
			}
			finally {
				lineNumberReader.close();
			}
		 
		return  new MethodPosition(-1, -1,methodName,method);
	}


	private boolean isTestMethod(Method m) {
		String name= m.getName();
		Class[] parameters= m.getParameterTypes();
		Class returnType= m.getReturnType();
		return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE);
	}


}
