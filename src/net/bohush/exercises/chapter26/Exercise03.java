package net.bohush.exercises.chapter26;

import java.util.Iterator;

public class Exercise03 {

	public static void main(String[] args) {
		MyTwoWayLinkedList<String> list = new MyTwoWayLinkedList<>();
		list.add("asdf");
		list.add("1234");
		list.add("fffff");
		list.add("44556699");
		list.add("1234");
		list.add("ccsdcd");
		list.add("1234");
		list.add("1234sssss");
		System.out.println(list);
		list.remove(3);
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
		System.out.println(list.contains("asd"));
		System.out.println(list.contains("fffff"));
		System.out.println();
		System.out.println(list.get(3));
		System.out.println(list.get(5));
		System.out.println();
		System.out.println(list.indexOf("44556699"));
		System.out.println(list.indexOf("asdf"));
		System.out.println(list.indexOf("123"));
		System.out.println(list.indexOf("1234"));
		System.out.println();
		System.out.println(list.lastIndexOf("44556699"));
		System.out.println(list.lastIndexOf("1234"));
		System.out.println(list.lastIndexOf("1234sssssssss"));
		System.out.println();
		System.out.println(list.set(0, "987654321"));
		System.out.println(list.set(5, "tratata"));
		System.out.println(list);
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			iterator.remove();
		}
		System.out.println(list);
	}

}

class MyTwoWayLinkedList<E> extends MyAbstractList<E> {
	private Node<E> head, tail;

	/** Create a default list */
	public MyTwoWayLinkedList() {
	}

	/** Create a list from an array of objects */
	public MyTwoWayLinkedList(E[] objects) {
		super(objects);
	}

	/** Return the head element in the list */
	public E getFirst() {
		if (size == 0) {
			return null;
		} else {
			return head.element;
		}
	}

	/** Return the last element in the list */
	public E getLast() {
		if (size == 0) {
			return null;
		} else {
			return tail.element;
		}
	}

	/** Add an element to the beginning of the list */
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e); // Create a new node
		newNode.next = head; // link the new node with the head
		newNode.previous = null;
		head = newNode; // head points to the new node
		size++; // Increase list size

		if (tail == null) // the new node is the only node in list
			tail = head;
	}

	/** Add an element to the end of the list */
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e); // Create a new for element e

		if (tail == null) {
			head = tail = newNode; // The new node is the only node in list
		} else {
			tail.next = newNode; // Link the new with the last node
			newNode.previous = tail;
			newNode.next = null;
			tail = tail.next; // tail now points to the last node
		}

		size++; // Increase size
	}

	@Override
	/** Add a new element at the specified index 
	 * in this list. The index of the head element is 0 */
	public void add(int index, E e) {
		if (index == 0) {
			addFirst(e);
		} else if (index >= size) {
			addLast(e);
		} else {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node<E> temp = current.next;
			current.next = new Node<E>(e);
			(current.next).previous = current;
			(current.next).next = temp;
			size++;
		}
	}

	/**
	 * Remove the head node and return the object that is contained in the
	 * removed node.
	 */
	public E removeFirst() {
		if (size == 0) {
			return null;
		} else {
			Node<E> temp = head;
			head = head.next;
			size--;
			if (head == null) {
				tail = null;
			} else {
				head.previous = null;
			}
			return temp.element;
		}
	}

	/**
	 * Remove the last node and return the object that is contained in the
	 * removed node.
	 */
	public E removeLast() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		} else {
			Node<E> temp = tail;
			tail = tail.previous;
			tail.next = null;
			size--;
			return temp.element;
		}
	}

	@Override
	/** Remove the element at the specified position in this 
	 *  list. Return the element that was removed from the list. */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node<E> previous = head;

			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}

			Node<E> current = previous.next;
			previous.next = current.next;
			previous.next.previous = previous;
			size--;
			return current.element;
		}
	}

	@Override
	/** Override toString() to return elements in the list */
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null) {
				result.append(", "); // Separate two elements with a comma
			} else {
				result.append("]"); // Insert the closing ] in the string
			}
		}

		return result.toString();
	}

	@Override
	/** Clear the list */
	public void clear() {
		size = 0;
		head = tail = null;
	}

	@Override
	/** Return true if this list contains the element e */
	public boolean contains(E e) {
		if(size == 0) {
			return false;
		} else {
			Node<E> tmp = head;
			while(tmp != null) {
				if(tmp.element.equals(e)) {
					return true;
				} else {
					tmp = tmp.next;
				}
			}
		}
		return false;
	}

	@Override
	/** Return the element at the specified index */
	public E get(int index) {
		checkIndex(index);
		Node<E> result = head;
		for (int i = 0; i < index; i++) {
			result = result.next;
		}
		return result.element;
	}

	@Override
	/** Return the index of the head matching element in 
	 *  this list. Return -1 if no match. */
	public int indexOf(E e) {
		if(size == 0) {
			return -1;
		} else {
			Node<E> tmp = head;
			int result = 0;
			while(tmp != null) {
				if(tmp.element.equals(e)) {
					return result;
				} else {
					tmp = tmp.next;
					result++;
				}
			}
		}
		return -1;
	}

	@Override
	/** Return the index of the last matching element in 
	 *  this list. Return -1 if no match. */
	public int lastIndexOf(E e) {
		if(size == 0) {
			return -1;
		} else {
			Node<E> tmp = tail;
			int result = size - 1;
			while(tmp != null) {
				if(tmp.element.equals(e)) {
					return result;
				} else {
					tmp = tmp.previous;
					result--;
				}
			}
		}
		return -1;
	}

	@Override
	/** Replace the element at the specified position 
	 *  in this list with the specified element. */
	public E set(int index, E e) {
		checkIndex(index);
		Node<E> tmp = head;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next;
		}
		tmp.element = e;
		return e;
	}

	@Override
	/** Override iterator() defined in Iterable */
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
	}

	private class LinkedListIterator implements java.util.Iterator<E> {
		private Node<E> current = head; // Current index

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			return e;
		}

		@Override
		public void remove() {
			if(current != null) {
				Node<E> tmp = current;
				current = current.next;
				size--;
				if(tmp.next != null)
					tmp.next.previous = tmp.previous;
				if(tmp.previous != null)
					tmp.previous.next = tmp.next;
			}
			
		}
	}

	private static class Node<E> {
		E element;
		Node<E> next;
		Node<E> previous;

		public Node(E e) {
			element = e;
		}
	}
}