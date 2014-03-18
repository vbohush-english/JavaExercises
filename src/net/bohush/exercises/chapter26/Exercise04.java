package net.bohush.exercises.chapter26;

public class Exercise04 {

	public static void main(String[] args) {
		int count = 0;
		int number = 2;
		GenericStack<Integer> stack = new GenericStack<>();
		while (count < 50) {
			boolean isPrime = true;
			for (int divisor = 2; divisor <= (int) (Math.sqrt(number)); divisor++) {
				if (number % divisor == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				count++;
				stack.push(number);
			}
			number++;
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	static class GenericStack<E> {
		private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

		public int getSize() {
			return list.size();
		}

		public E peek() {
			return list.get(getSize() - 1);
		}

		public void push(E o) {
			list.add(o);
		}

		public E pop() {
			E o = list.get(getSize() - 1);
			list.remove(getSize() - 1);
			return o;
		}

		public boolean isEmpty() {
			return list.isEmpty();
		}

		@Override
		public String toString() {
			return "stack: " + list.toString();
		}
	}

}
