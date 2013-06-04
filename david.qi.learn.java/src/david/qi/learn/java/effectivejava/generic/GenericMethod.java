package david.qi.learn.java.effectivejava.generic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GenericMethod {
	
	public static void main(String[] args) {
		Set<String> list = new HashSet<String>();
		Set<String> list2 = new HashSet<String>();		
		list.add("One");
		list.add("Two");
		list.add("Three");
		list2.add("Four");
		list2.add("Five");
		System.out.println(max(union(list,list2)));
	}
	
	//一般的泛型方法
	public static <T> Set<T> union(Set<T> s1, Set<T> s2){
		Set<T> result = new HashSet<T>(s1);
		result.addAll(s2);
		return result;
	}
	
	//类型限制<T extends Comparable<T>>用于指代泛型接口Comparable
	public static <T extends Comparable<T>> T max(Set<T> set) {
		Iterator<T> i = set.iterator();
		T result = i.next();
		for(T e : set) {
			if(e.compareTo(result) > 0) {
				result = e;
			}
		}
		return result;
	}

}
