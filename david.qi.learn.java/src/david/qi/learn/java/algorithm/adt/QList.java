package david.qi.learn.java.algorithm.adt;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QList<E> implements List<E> {
	Node<E> head;
	Node<E> tail;
	int size = 0;

	public QList() {
		this.head = new Node<E>();
		this.tail = new Node<E>();
		this.head.setNextNode(this.tail);
		this.tail.setPreNode(this.head);
	}

	@Override
	public void add(int index, E element) {
		if (index > size) {
			throw new IndexOutOfBoundsException("Out of boundry, size = "
					+ size + " and index = " + index);
		}
		Node<E> position = head;
		for (int i = 0; i < index; i++) {
			position = position.getNextNode();
		}
		Node<E> e = new Node<E>();
		e.setValue(element);
		position.setNextNode(e);
		e.setPreNode(position);
		e.setNextNode(position.getNextNode());
		position.getNextNode().setPreNode(e);
		size++;
	}

	@Override
	public boolean add(E e) {
		Node<E> current = new Node<E>();
		current.setValue(e);
		Node<E> last = this.tail.getPreNode();
		last.setNextNode(current);
		current.setPreNode(last);
		current.setNextNode(this.tail);
		this.tail.setPreNode(current);
		size++;
		return true;
	}

	@Override
	public void clear() {
		this.tail.setPreNode(this.head);
		this.head.setNextNode(this.tail);
		this.size = 0;
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException("Donot support this method!");

	}

	@Override
	public E get(int index) {
		if (index >= size) {
			throw new RuntimeException("Out of boundary: " + index);
		}
		Node<E> position = this.head;
		for (int i = 0; i <= index; i++) {
			position = position.getNextNode();
		}
		return position.getValue();
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			throw new RuntimeException("Wrong parameter: null");
		}
		Node position = this.head;
		int i = 0;
		while (position.getNextNode() != this.tail) {
			position = position.getNextNode();
			if (o == position.getValue())
				return i;
			i++;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		if (this.size == 0)
			return true;
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Node<E> position = head;

			@Override
			public boolean hasNext() {
				if (position.getNextNode() != tail) {
					return true;
				}
				return false;
			}

			@Override
			public E next() {
				position = position.getNextNode();
				return position.getValue();
			}

			@Override
			public void remove() {
				if (position == head || position == tail) {
					throw new RuntimeException(
							"It's at begin or at end, can't remove.");
				} else {
					Node<E> pre = position.getPreNode();
					Node<E> next = position.getNextNode();
					pre.setNextNode(next);
					next.setPreNode(pre);
					position = next;
				}
			}
		};
	}

	@Override
	public ListIterator listIterator() {
		throw new UnsupportedOperationException("Donot support this method!");
	}

	@Override
	public ListIterator listIterator(int index) {
		throw new UnsupportedOperationException("Donot support this method!");
	}

	@Override
	public E remove(int index) {
		Node<E> position = head;
		for (int i = 0; i <= index; i++) {
			position = position.getNextNode();
		}
		Node<E> pre = position.getPreNode();
		Node<E> next = position.getNextNode();
		pre.setNextNode(next);
		next.setPreNode(pre);
		this.size--;
		return position.getValue();
	}

	@Override
	public boolean remove(Object o) {
		Node<E> position = head;
		for(int i = 0; i< size; i++){
			position = position.getNextNode();
			if (position.getValue() == o){
				this.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		throw new UnsupportedOperationException("Donot support this method!");
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException("Donot support this method!");
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public E[] toArray() {
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		return null;
	}

	private static final class Node<E> {
		Node<E> nextNode;
		Node<E> preNode;
		E value;

		Node() {
		}

		void setNextNode(Node<E> next) {
			this.nextNode = next;
		}

		Node<E> getNextNode() {
			return this.nextNode;
		}

		void setPreNode(Node<E> pre) {
			this.preNode = pre;
		}

		Node<E> getPreNode() {
			return this.preNode;
		}

		void setValue(E value) {
			this.value = value;
		}

		E getValue() {
			return this.value;
		}
	}

	@Override
	public boolean addAll(Collection arg0) {
		throw new UnsupportedOperationException("Donot support this method!");
	}

	@Override
	public boolean addAll(int arg0, Collection arg1) {
		throw new UnsupportedOperationException("Donot support this method!");
	}

	@Override
	public boolean containsAll(Collection arg0) {
		throw new UnsupportedOperationException("Donot support this method!");
	}

	@Override
	public int lastIndexOf(Object arg0) {
		throw new UnsupportedOperationException("Donot support this method!");
	}

	@Override
	public List subList(int arg0, int arg1) {
		throw new UnsupportedOperationException("Donot support this method!");
	}
}
