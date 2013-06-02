package david.qi.learn.java.guice.aop.example2;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.LONG;
import static java.util.Locale.ENGLISH;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class WeekendBlocker implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Calendar today = new GregorianCalendar();
		if (today.getDisplayName(DAY_OF_WEEK, LONG, ENGLISH).startsWith("S")) {
			throw new IllegalStateException(invocation.getMethod().getName() + " not allowed on weekends!");
		}
		return invocation.proceed();
	}

}
