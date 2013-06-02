package david.qi.learn.java.guice.aop.example1;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

public class AopDemo {
	@Inject
	private Service service;

	public static void main(String[] args) {
		Injector inj = Guice.createInjector(new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Names.named("log")),
				        new LoggerMethodInterceptor());
			}
		});
		inj.getInstance(AopDemo.class).service.sayHello();
		inj.getInstance(AopDemo.class).service.sayHello();
		inj.getInstance(AopDemo.class).service.sayHello();
	}
}