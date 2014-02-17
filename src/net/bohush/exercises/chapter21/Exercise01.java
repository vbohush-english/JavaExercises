package net.bohush.exercises.chapter21;

public class Exercise01 {

	public static void main(String[] args) {
		GenericStack<String> stack1 = new GenericStack<String>();
		stack1.push("London");
		stack1.push("Paris");
		stack1.push("Berlin");
		stack1.push("1");
		stack1.push("2");
		stack1.push("3");
		stack1.push("444");
		stack1.push("555");
		stack1.push("6");
		stack1.push("6666");
		stack1.push("777777");
		stack1.push("777");
		stack1.push("77");
		System.out.println(stack1);
		while(!stack1.isEmpty()) {
			System.out.println(stack1.pop());
		}

	}

}

class GenericStack<E> {
	int size;
	private E[] list;
	
	@SuppressWarnings("unchecked")
	public GenericStack(int capacity) {
		list = (E[])new Object[capacity];
		size = 0;
	}
	
	public GenericStack() {
		this(3);
	}
	
	public int getSize() {
		return size;
	}

	public E peek() {
		return list[getSize() - 1];
	}

	public void push(E o) {
		if(size == list.length) {
			@SuppressWarnings("unchecked")
			E[] newList = (E[])new Object[list.length * 2];
			for (int i = 0; i < list.length; i++) {
				newList[i] = list[i];
			}
			list = newList;
		}
		list[size] = o;
		size++;
	}

	public E pop() {
		E o = list[getSize() - 1];
		size--;
		return o;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Stack: [");
		for (int i = 0; i < size - 1; i++) {
			result.append(list[i].toString());
			result.append(", ");
		}
		result.append(list[size - 1].toString());
		result.append("]");
		return result.toString();
	}
}