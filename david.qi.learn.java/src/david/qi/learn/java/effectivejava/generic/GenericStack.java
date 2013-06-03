package david.qi.learn.java.effectivejava.generic;

import java.util.Arrays;
import java.util.EmptyStackException;

public class GenericStack <E> {
	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	public static void main(String[] args) {
	    GenericStack<String> stack = new GenericStack<String>();
	    stack.push("One");
	    stack.push("Two");
	    System.out.println(stack.pop());
	    System.out.println(stack.pop());
	    
    }
	
	@SuppressWarnings("unchecked")
	public GenericStack(){
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}
	
	public E pop() {
		if(size == 0) throw new EmptyStackException();
		E result = elements[--size];
		elements[size] = null;
		return result;
	}
	
	private void ensureCapacity() {
		if(size == DEFAULT_INITIAL_CAPACITY) {
			elements = Arrays.copyOf(elements, size * 2 + 1);
		}
	}

}
