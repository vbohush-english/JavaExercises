package net.bohush.exercises.chapter23;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;

public class Exercise06 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int nextInteger = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		while((nextInteger = input.nextInt()) != 0) {
			Integer value = map.get(nextInteger);
			if(value == null) {
				value = 0;
			}
			map.put(nextInteger, value + 1);
		}
		Set<Integer> set = map.keySet();
		int maxKey = 0;
		int maxValue = 0;
		for (Integer integer : set) {
			int value = map.get(integer);
			if(value > maxValue) {
				maxValue = value;
				maxKey = integer;
			}
		}
		System.out.println("Value: " + maxKey);
		System.out.println("Number of occurrences: " + maxValue);
	}

}
