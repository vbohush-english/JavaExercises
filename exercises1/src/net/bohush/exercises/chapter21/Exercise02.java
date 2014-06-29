package net.bohush.exercises.chapter21;

public class Exercise02 {

	public static void main(String[] args) {
		GenericStack2<String> stack2 = new GenericStack2<String>();
		stack2.push("London");
		stack2.push("Paris");
		stack2.push("Berlin");
		stack2.push("1");
		stack2.push("2");
		stack2.push("3");
		stack2.push("444");
		stack2.push("555");
		stack2.push("6");
		stack2.push("6666");
		stack2.push("777777");
		stack2.push("777");
		stack2.push("77");
		System.out.println(stack2);
		while (!stack2.isEmpty()) {
			System.out.println(stack2.pop());
		}

	}

}

class GenericStack2<E> extends java.util.ArrayList<E> {
	private static final long serialVersionUID = 1L;

	public int getSize() {
		return size();
	}

	public E peek() {
		return get(getSize() - 1);
	}

	public void push(E o) {
		add(o);
	}

	public E pop() {
		E o = get(getSize() - 1);
		remove(getSize() - 1);
		return o;
	}
}
