package david.qi.learn.java.algorithm.adt;

import static org.junit.Assert.*;

import org.junit.Test;

public class QListTest {
	
	
	@Test
	public void testAdd() {
		QList<String> list = new QList<String>();
		list.add("hello");
		list.add("world");
	}

}
