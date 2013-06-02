package david.qi.learn.java.guice.aop.example2;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class DemoEntry {
	public static void main(String[] args) {
		Injector inj = Guice.createInjector(new NotOnWeekendsModule());
		inj.getInstance(BillingService.class).chargeOrder("order", "creditcard");
	}
}
