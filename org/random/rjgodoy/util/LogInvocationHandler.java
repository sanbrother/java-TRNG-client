package org.random.rjgodoy.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogInvocationHandler implements InvocationHandler {
	protected final Object object;

	public LogInvocationHandler(Object object) {
		this.object=object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (object==null) {
			return method.getDeclaringClass()==Object.class?method.invoke(object, args)
			      :method.getReturnType()==Void.TYPE?null
					  :method.getReturnType()==Boolean.TYPE?false
					  :fail();
		} else {
			Class klass = object.getClass();
			NoSuchMethodException e=null;

			try {method = klass.getDeclaredMethod(method.getName(), method.getParameterTypes());}
			catch (NoSuchMethodException _) {e=_;}

			//FIXME check signature compatibility instead of this:
			if (e!=null) {
				Class<?> param_types[] = method.getParameterTypes();
			  for (int i=0;i<param_types.length;i++) if (param_types[i]==String.class) param_types[i]=Object.class;
				try {method = klass.getDeclaredMethod(method.getName(), param_types); e=null;}
				catch (NoSuchMethodException _) {;}
			}

			if (e!=null) throw e;
			return method.invoke(object, args);
		}
	}

	private Void fail() {
		throw new UnsupportedOperationException();
	}
}
