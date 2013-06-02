package david.qi.learn.java.guice.aop.example1;

import static java.lang.System.out;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggerMethodInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		long startTime = System.nanoTime();
		out.println(String.format("before method[%s] at %s", methodName, startTime));
		Object ret = null;
		try {
			ret = invocation.proceed();
		} finally {
			long endTime = System.nanoTime();
			out.println(String.format(" after method[%s] at %s, cost(ns):%d", methodName, endTime,
			        (endTime - startTime)));
		}
		return ret;
	}
}
