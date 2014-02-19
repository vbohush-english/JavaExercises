package net.bohush.exercises.chapter21;

import java.util.ArrayList;

public class Exercise10 {
	public static void main(String[] args) {
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("as");
		list1.add("sad");
		list1.add("as11");
		list1.add("sad");
		list1.add("sad");
		list1.add("sadww");		
		System.out.println(max(list1));
		
		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(12);
		list2.add(32);
		list2.add(213);
		list2.add(24);
		list2.add(56);
		list2.add(76);
		System.out.println(max(list2));
	}
	
	public static <E extends Comparable<E>> E max(ArrayList<E> list) {
		E max = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).compareTo(max) > 0) {
				max = list.get(i);		
			}
		}
		return max;
	}
}
