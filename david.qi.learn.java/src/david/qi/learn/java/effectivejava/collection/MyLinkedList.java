package david.qi.learn.java.effectivejava.collection;

public class MyLinkedList<T> implements Iterable<T> {
	
	public static void main(String[] args) {
	    MyLinkedList<String> list = new MyLinkedList<String>();
	    list.add("Hello");
	    list.add("World!");
	    list.remove(1);
	    for(int i = list.size(); i < 10; i ++) {
	    	list.add("...");
	    }
	    for(String s : list) {
	    	System.out.println(s);
	    }
	    System.out.println(list.size());
    }
	
	private static class Node<T>{
		public Node(T d, Node<T> p, Node<T> n) {
			data = d;
			prev = p;
			next = n;
		}
		public T data;
		public Node<T> prev;
		public Node<T> next;
	}
	
	public MyLinkedList() {
		clear();				
	}
	public void clear()	{
		beginMarker = new Node<T>(null,null,null);
		endMarker = new Node<T>(null,beginMarker,null);
		beginMarker.next = endMarker;
		theSize = 0;
		modCount++;
	}
	public int size() {
		return theSize;
	}
	public boolean isEmpty() {
		return size() == 0;
	}
	public boolean add(T x) {
		add(size(), x);
		return true;
	}
	public void add(int idx, T x) {
		addBefore(getNode(idx), x);
	}
	public T get(int idx) {
		return getNode(idx).data;
	}
	public T set(int idx, T newVal) {
		Node<T> p = getNode(idx);
		T oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	public T remove(int idx) {
		return remove(getNode(idx));
	}
	/**
	 * Adds an item to this collection at specified position p.
	 * Items at or after the idx are moved one position higher.
	 * @param p Node to add before.
	 * @param x Any object.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size().
	 */
	private void addBefore(Node<T> p, T x) {
		Node<T> newNode = new Node<T>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}
	private T remove(Node<T> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next; 
		theSize--;
		modCount++;
		return p.data;
	}
	/**
	 * Gets the Node at position idx, which must range from 0 to size().
	 * @param idx index of node being obtained.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundException if idx is not between 0 and size().
	 */
	private Node<T> getNode(int idx){
		Node<T> p;
		if (idx < 0 || idx > size())
			throw new IndexOutOfBoundsException();
		if(idx <size()/2) { // If idx < size()/2, iterate from the begin to middle
			p = beginMarker.next;
			for(int i = 0; i < idx; i ++)
				p = p.next;			
		}else { // If idx >= size()/2, iterate from the end to middle
			p = endMarker;
			for ( int i = size(); i > idx ; i --)
				p = p.prev;
		}
		return p;
	}
	public java.util.Iterator<T> iterator(){
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements java.util.Iterator<T>{
		private Node<T> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		public boolean hasNext() {
			return current != endMarker;
		}
		public T next() {
			if(modCount !=expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			
			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}
		public void remove() {
			if(modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if( !okToRemove)
				throw new IllegalStateException();
			
			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
			expectedModCount ++;
		}
	}
	private int theSize;  //Number of elements in the list
	private int modCount = 0; //Every time add or remove will make modCount +1, used for checking excepton of concurrent modify
	private Node<T> beginMarker;
	private Node<T> endMarker;
	

}
