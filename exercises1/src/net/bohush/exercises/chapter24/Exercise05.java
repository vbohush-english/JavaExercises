package net.bohush.exercises.chapter24;

import java.util.LinkedList;
import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		LinkedList<Integer> list = new LinkedList<>();
		System.out.print("Enter a series of numbers ending with 0: ");
		int nextInt = 0;
		while((nextInt = input.nextInt()) != 0) {
			list.add(nextInt);
		}
		int number = list.get(0);
		int count = 1;
		int index = 0;
		int maxNumber = 0;
		int maxCount = 0;
		int maxIndex = 0;
		for (int i = 1; i < list.size(); i++) {
			if(list.get(i) == number) {
				count++;				
			} else {
				if(count > maxCount) {
					maxCount = count;
					maxNumber = number;
					maxIndex = index;
				}
				count = 1;
				number = list.get(i);
				index = i;
			}
		}
		if(count > maxCount) {
			maxCount = count;
			maxNumber = number;
			maxIndex = index;
		}
		System.out.println("The longest same number sequence starts at index " + maxIndex + " with " + maxCount + " values of " + maxNumber);
	}

}
