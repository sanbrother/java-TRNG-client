package org.random.rjgodoy.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;


public class LogFactory {
	private static Class  klazz;
	private static Method method;

	private static Constructor<Proxy> proxyConstructor;

	static {
		ClassLoader classLoader = LogFactory.class.getClassLoader();
		try {proxyConstructor= (Constructor<Proxy>) Proxy.getProxyClass(classLoader, Log.class).getConstructor(InvocationHandler.class);}
		catch (NoSuchMethodException e) {throw new ExceptionInInitializerError(e);}
	}

	public static Log getLog(String name) {

		if (klazz==null&&method==null) try {
			klazz=Class.forName("org.apache.commons.logging.LogFactory");
			method = klazz.getMethod("getLog", String.class);
		} catch (Exception e) {klazz=Void.class;}

		try {
			InvocationHandler handler;
			if (method==null)
				return new SimpleLog(name);
			else {
				Object log = method.invoke(null, name);
				handler = new LogInvocationHandler(log);
			}

			return (Log) proxyConstructor.newInstance(handler);
		}
		catch (IllegalAccessException    e) {throw (Error) new IllegalAccessError().initCause(e);}
		catch (InstantiationException    e) {throw (Error) new InstantiationError().initCause(e);}
		catch (InvocationTargetException e) {throw new UndeclaredThrowableException(e);}
	}
}
