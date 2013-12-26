package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise11 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter string: ");
		String string = input.next();
		System.out.print(sort(string));
	}

	public static String sort(String s) {
		char[] list = s.toCharArray();
		for(int i = 0; i < list.length - 1; i++) {
			for(int j = 0; j < list.length - 1; j++) {
				if (list[j] > list[j + 1]) {
					char tmp = list[j];
					list[j] = list [j + 1];
					list[j + 1] = tmp;
				}
			}
		}
		return new String(list);
	}
}
