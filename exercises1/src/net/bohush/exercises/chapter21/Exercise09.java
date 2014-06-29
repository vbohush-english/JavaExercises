package net.bohush.exercises.chapter21;

import java.util.ArrayList;

public class Exercise09 {
	public static void main(String[] args) {
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("as");
		list1.add("sad");
		list1.add("as11");
		list1.add("sad");
		list1.add("sad");
		list1.add("sadww");
		sort(list1);
		System.out.println(list1);
		
		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(12);
		list2.add(32);
		list2.add(213);
		list2.add(24);
		list2.add(56);
		list2.add(76);
		sort(list2);
		System.out.println(list2);
	}
	
	public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).compareTo(list.get(j)) > 0) {
					E tmp = list.get(j);
					list.set(j, list.get(i));
					list.set(i, tmp);					
				}
			}
		}
	}
}
