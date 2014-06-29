package net.bohush.exercises.chapter26;

public class Exercise05 {
	public static void main(String[] args) {
		// Create a queue
		GenericQueue<String> queue = new GenericQueue<String>();

		// Add elements to the queue
		queue.enqueue("Tom"); // Add it to the queue
		System.out.println("(7) " + queue);

		queue.enqueue("Susan"); // Add it to the queue
		System.out.println("(8) " + queue);

		queue.enqueue("Kim"); // Add it to the queue
		queue.enqueue("Michael"); // Add it to the queue
		System.out.println("(9) " + queue);

		// Remove elements from the queue
		System.out.println("(10) " + queue.dequeue());
		System.out.println("(11) " + queue.dequeue());
		System.out.println("(12) " + queue);
	}

	static class GenericQueue<E> extends java.util.LinkedList<E> {
		private static final long serialVersionUID = 1L;

		public void enqueue(E e) {
			addLast(e);
		}

		public E dequeue() {
			return removeFirst();
		}

		public int getSize() {
			return size();
		}

		@Override
		public String toString() {
			return "Queue: " + super.toString();
		}
	}
}
