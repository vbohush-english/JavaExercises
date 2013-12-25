package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise02 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a sub-string: ");
		String subString = input.next();
		System.out.print("Enter a sub-string: ");
		String string = input.next();
		int position = indexOf(subString, string);
		if (position == -1) {
			System.out.print("The first string is not a substring of the second");
		}else {
			System.out.print("The first string is a substring of the second in position " + position);
		}
	}
	
	public static int indexOf(String subString, String string) {
		for (int i = 0; i <= string.length() - subString.length(); i++) {
			boolean isEqual = true;
			for (int j = i; j < i + subString.length(); j++) {
				if (string.charAt(j) !=  subString.charAt(j - i)) {
					isEqual = false;
					break;
				}
			}
			if (isEqual) {
				return i;
			}
		}
		return -1;
	}

}
