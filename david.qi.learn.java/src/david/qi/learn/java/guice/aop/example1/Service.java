package david.qi.learn.java.guice.aop.example1;

import com.google.inject.ImplementedBy;

@ImplementedBy(ServiceImpl.class)
public interface Service {
	void sayHello();

}
