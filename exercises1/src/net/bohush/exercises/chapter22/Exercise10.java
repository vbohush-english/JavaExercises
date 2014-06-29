package net.bohush.exercises.chapter22;

import java.util.Arrays;
import java.util.PriorityQueue;


public class Exercise10 {

	public static void main(String[] args) {
		PriorityQueue<String> queue1 = new PriorityQueue<>(Arrays.asList(new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"}));
		PriorityQueue<String> queue2 = new PriorityQueue<>(Arrays.asList(new String[]{"George", "Katie", "Kevin", "Michelle", "Ryan"}));
		queue1.addAll(queue2);
		System.out.println(queue1);
		
		queue1 = new PriorityQueue<>(Arrays.asList(new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"}));
		queue1.removeAll(queue2);
		System.out.println(queue1);
		
		queue1 = new PriorityQueue<>(Arrays.asList(new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"}));
		queue1.retainAll(queue2);
		System.out.println(queue1);
		
		
	}

}
