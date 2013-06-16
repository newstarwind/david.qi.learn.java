package david.qi.learn.java.effectivejava.generic;

import java.util.Arrays;
import java.util.Collection;
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
		//这是一种常见的创建泛型数组的方法, 但是编译期不能证明程序是类型安全的, 必须自己确保这一转换不会导致危害.
		//在该例子中, 因为push和pop都是用泛型参数, 则类型安全受到了保障.
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
	
	//利用受限制的通配符类型来提升API的灵活性: PECS, producer-extends
	public void pushAll(Iterable<? extends E> src) {
		for(E e : src)
			push(e);
	}
	
	//使用E的超类的集合来获得API的最大灵活性: PECS, comsumer-super
	public void popAll(Collection<? super E> dst) {
		while(!isEmpty()) {
			dst.add(pop());
		}
	}
	
	public boolean isEmpty() {
		return size == 0? true:false;
	}
	
	private void ensureCapacity() {
		if(size == DEFAULT_INITIAL_CAPACITY) {
			elements = Arrays.copyOf(elements, size * 2 + 1);
		}
	}

}
