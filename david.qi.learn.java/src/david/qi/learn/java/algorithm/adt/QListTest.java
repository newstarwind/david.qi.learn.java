package david.qi.learn.java.algorithm.adt;

import static org.junit.Assert.*;

import org.junit.Test;

public class QListTest {
	
	
	@Test
	public void testAdd() {
		QList<String> list = new QList<String>();
		list.add("hello");
		list.add("world");
		list.add("This");
		list.add("is");
		list.add("Shanghai");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.indexOf("world"));
		System.out.println(list.indexOf("test"));
		for(String e : list){
			System.out.println(e);
		}
	}

}
