package net.bohush.exercises.chapter22;

import java.util.PriorityQueue;

public class Exercise12 {

	public static void main(String[] args) throws CloneNotSupportedException {
		MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>();
		queue1.offer(1);
		queue1.offer(2);
		queue1.offer(45);
		@SuppressWarnings("unchecked")
		MyPriorityQueue<Integer> queue2 = (MyPriorityQueue<Integer>)(queue1.clone());
		System.out.println(queue1);
		System.out.println(queue2);
		System.out.println(queue1 == queue2);
	}
}

class MyPriorityQueue<A> extends PriorityQueue<A> implements Cloneable {
	private static final long serialVersionUID = 1L;
	@Override
	protected Object clone() throws CloneNotSupportedException {
		MyPriorityQueue<A> result = new MyPriorityQueue<>();
		for (A a : this) {
			result.offer(a);
		}
		return result;
	}
}