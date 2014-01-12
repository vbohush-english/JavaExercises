package net.bohush.exercises.chapter15;

import java.util.ArrayList;

public class Exercise03 {

	public static void main(String[] args) {
		ArrayList<Number> list = new ArrayList<>();
		list.add(123);
		list.add(34.23);
		list.add(23123);
		list.add(0.01);
		list.add(999.999);
		list.add(1);
		shuffle(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public static void shuffle(ArrayList<Number> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			int nextPosition = (int)(Math.random() * list.size());
			Number tmp = list.get(nextPosition);
			list.set(nextPosition, list.get(i));
			list.set(i, tmp);
		}
	}
}
