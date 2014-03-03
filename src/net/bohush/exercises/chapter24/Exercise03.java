package net.bohush.exercises.chapter24;

import java.util.Scanner;

public class Exercise03 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string s1: ");
		String s1 = input.nextLine();
		System.out.print("Enter a string s2: ");
		String s2 = input.nextLine();
		boolean finished = false;
		for (int i = 0, k = 0; i < s1.length(); i++) {
			if(s1.charAt(i) == s2.charAt(k)) {
				k++;
			} else {
				k = 0;
			}
			if(k == s2.length()) {
				System.out.println("matched at index " + (i - k + 1));
				finished = true;
				break;
			}
		}
		if(!finished) {
			System.out.println("not matched");
		}

	}

}
