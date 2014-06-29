package net.bohush.exercises.chapter11;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a sequence of numbers ending with 0: ");
		int tmp = input.nextInt();
		while (tmp != 0) {
			list.add(tmp);
			tmp = input.nextInt();
		}
		System.out.println("Max: " + max(list));
	}

	
	public static Integer max(ArrayList<Integer> list) {
		int max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (max < list.get(i)) {
				max = list.get(i);
			}
		}
		return max;
	}
}
