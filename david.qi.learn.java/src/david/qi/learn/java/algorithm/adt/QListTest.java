package david.qi.learn.java.algorithm.adt;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class QListTest {

	@Test
	public void testAdd() {
		QList<String> list = new QList<String>();
		list.add("hello");
		list.add("world");
		list.add("This");
		list.add("is");
		list.add("David");
		list.add("in");
		list.add("Shanghai");

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String s = it.next();
			System.out.println(s);
			if (s.equals("This")) {
				it.remove();
			}
		}
		for (String e : list) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testRemove(){
		QList<String> list = new QList<String>();
		list.add("hello");
		list.add("world");
		list.add("This");
		list.add("is");
		list.add("David");
		list.add("in");
		list.add("Shanghai");
		list.remove("This");
		list.remove(3);
		for(String s : list){
			System.out.println(s);
		}
	}
}
