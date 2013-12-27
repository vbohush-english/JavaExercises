package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise19 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter string1: ");
		String string1 = input.next();
		System.out.print("Enter string2: ");
		String string2 = input.next();
		System.out.print("Longest common prefix - " + prefix(string1, string2));
	}
	
	public static String prefix(String s1, String s2) {
		String result = "";
		int minLenght = s1.length() < s2.length() ? s1.length() : s2.length();
		for (int i = 0; i < minLenght - 1; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return result;
			} else {
				result += s1.charAt(i);
			}
		}
		return s1;
	}
}
