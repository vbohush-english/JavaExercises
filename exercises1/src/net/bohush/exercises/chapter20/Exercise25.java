package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise25 {
	public static int count = 0;

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter string: ");
		String s = input.next();
		long time1 = System.currentTimeMillis();
		displayPermutation(s);
		long time2 = System.currentTimeMillis();
		System.out.println("count = " + count);
		System.out.println("time = " + (time2 - time1) + " ms.");
	}

	public static void displayPermutation(String s) {
		displayPermutation("", s);
	}
	
	public static void displayPermutation(String s1, String s2) {
		if (s2.length() == 2) {
			count+=2;
			System.out.println(s1 + s2.charAt(0) + s2.charAt(1));
			System.out.println(s1 + s2.charAt(1) + s2.charAt(0));
		} else {
			for (int i = 0; i < s2.length(); i++) {
				String newS2 = "";
				for (int j = 0; j < s2.length(); j++) {
					if (j != i) {
						newS2 += s2.charAt(j);
					}
				}
				displayPermutation(s1 + s2.charAt(i), newS2);
			}
		}
	}
}
