package david.qi.learn.java.guice.aop.example2;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class NotOnWeekendsModule extends AbstractModule {

	@Override
	/**
	 * Finally, we configure everything. This is where we create matchers for 
	 * the classes and methods to be intercepted. In this case we match any 
	 * class, but only the methods with our @NotOnWeekends annotation:
	 */
	protected void configure() {
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(NotOnWeekends.class), new WeekendBlocker());
		bind(BillingService.class).to(RealBillingService.class);
	}
}
