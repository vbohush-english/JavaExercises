package net.bohush.exercises.chapter21;

import java.util.ArrayList;

public class Exercise03 {
	public static void main(String[] args) {
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("as");
		list1.add("sad");
		list1.add("as11");
		list1.add("sad");
		list1.add("sad");
		list1.add("sadww");
		list1 = removeDuplicates(list1);
		System.out.println(list1);
		
		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(1);
		list2.add(123);
		list2.add(123);
		list2.add(321);
		list2.add(2);
		list2.add(321);
		list2 = removeDuplicates(list2);
		System.out.println(list2);
	}
	
	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		ArrayList<E> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (!result.contains(list.get(i))) {
				result.add(list.get(i));
			}
		}
		return result;
	}
}
