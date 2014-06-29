package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise12 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter string1: ");
		String string1 = input.next();
		System.out.print("Enter string2: ");
		String string2 = input.next();
		if (isAnagram(string1, string2)) {
			System.out.println("two strings are anagrams");
		} else {
			System.out.println("two strings are not anagrams");
		}
	}
	
	public static boolean isAnagram(String s1, String s2) {
		s1 = sort(s1);
		s2 = sort(s2);
		return s1.equals(s2);
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
