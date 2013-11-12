package david.qi.learn.java.algorithm.adt;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QList<E> implements List<E>{
	Node<E> head;
	Node<E> tail;
	int size = 0;
	
	public QList(){
		this.head = new Node();
		this.tail = new Node();
		this.head.setNextNode(this.tail);
		this.tail.setPreNode(this.head);
	}
	
	@Override
	public void add(int index, E element) {
		if(index > size){
			throw new RuntimeException("Out of boundry, size = " + size +" and index = " + index);
		}
		Node position = this.head;
		for(int i = 0;i < index; i++){
			position = position.getNextNode(); 			
		}
		Node follower = position.getNextNode();
		Node e = new Node();
		e.setValue(element);
		position.setNextNode(e);
		e.setPreNode(position);
		e.setNextNode(follower);
		follower.setPreNode(e);
		size++;
	}

	@Override
	public boolean add(E e) {
		Node current = new Node();
		current.setValue(e);
		Node last = this.tail.getPreNode();
		last.setNextNode(current);
		current.setPreNode(last);
		current.setNextNode(this.tail);
		this.tail.setPreNode(current);
		size++;
		return true;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public E get(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator iterator() {
		return null;
	}

	@Override
	public ListIterator listIterator() {
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		return null;
	}

	@Override
	public E remove(int index) {
		return null;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		return false;
	}

	@Override
	public E set(int index, E element) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public E[] toArray() {
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		return null;
	}

	private static final class Node<E>{
		E element;
		Node nextNode;
		Node preNode;
		E value;
		Node(){}
		void setNextNode(Node next){
			this.nextNode = next;
		}
		Node getNextNode(){
			return this.nextNode;
		}
		void setPreNode(Node pre){
			this.preNode = pre;
		}
		Node getPreNode(){
			return this.preNode;
		}
		void setValue(E value){
			this.value = value;
		}
		E getValue(){
			return this.value;
		}
	}

	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
