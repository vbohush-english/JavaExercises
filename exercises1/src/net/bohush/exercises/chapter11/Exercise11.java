package net.bohush.exercises.chapter11;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise11 {

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
		
		sort(list);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}

	
	public static void sort(ArrayList<Integer> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j) < list.get(i)) {
					int tmp = list.get(j);
					list.set(j, list.get(i));
					list.set(i, tmp);
				}
			}
		}
	}
}
