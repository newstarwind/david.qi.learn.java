package david.qi.learn.java.effectivejava.collection;

import java.lang.Iterable;

public class MyArrayList<T> implements Iterable<T> {
	private static final int DEFAULT_CAPACITY = 10;
	
	private int theSize; 	//Real size of elements stored
	private T[] theItems; //Capacity of storage
	
	public MyArrayList() {
		clear();
	}
	
	public static void main(String[] args) {
	    MyArrayList<String> list = new MyArrayList<String>();
	    list.add("Hello");
	    list.add("World!");
	    for(int i = list.size(); i < 12; i ++)
	    	list.add("...");
	    list.remove(1);
	    for(String s : list)
	    	System.out.println(s);
	    System.out.println(list.size());
	    
    }
	
	public void clear() {
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public int size() {
		return theSize;
	}
	public boolean isEmpty()	{
		return size()==0;
	}
	public void trimToSize() {
		ensureCapacity(size());
	}	
	public void ensureCapacity(int newCapacity) {
		if(newCapacity < theSize) {
			return;
		}
		T[] old = theItems;
		theItems = (T[])new Object[newCapacity];
		for(int i =0; i< size(); i++) {
			theItems[i] = old[i];
		}		
	}
	public boolean add(T x) {
		add(size(),x);
		return true;
	}
	public void add(int idx, T x) {
		if(theItems.length == size())
			ensureCapacity(size() * 2 +1);
		for(int i = theSize; i > idx; i --)
			theItems[i] = theItems[i - 1]; //idx 之后的element往后移动一格
		theItems[idx] = x;
		theSize++;
	}
	public T remove(int idx) {
		T removedItem = theItems[idx];
		for(int i = idx; i < size() -1; i++)
			theItems[i] = theItems[i + 1]; //idx 之后的element往前移动一格
		theSize--;
		return removedItem;
	}
	public java.util.Iterator<T> iterator(){
		return new ArrayListIterator();
	}
	private class ArrayListIterator implements java.util.Iterator<T>{
		private int current = 0;
		public boolean hasNext() {
			return current < size();
		}
		public T next() {
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			return theItems[current ++];
		}
		public void remove() {
			MyArrayList.this.remove(--current);
		}
	}	
}
