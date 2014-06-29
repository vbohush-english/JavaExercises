package net.bohush.exercises.chapter21;

import java.util.ArrayList;

public class Exercise08 {
	public static void main(String[] args) {
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("as");
		list1.add("sad");
		list1.add("as11");
		list1.add("sad");
		list1.add("sad");
		list1.add("sadww");
		shuffle(list1);
		System.out.println(list1);
		
		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(1);
		list2.add(2);
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(6);
		shuffle(list2);
		System.out.println(list2);
	}
	
	public static <E> void shuffle(ArrayList<E> list) {
		for (int i = 0; i < list.size(); i++) {
			int changeTo = (int)(Math.random() * list.size()); 
			E tmp = list.get(changeTo);
			list.set(changeTo, list.get(i));
			list.set(i, tmp);
		}
	}
}
