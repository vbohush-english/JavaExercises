package net.bohush.exercises.chapter09;

public class Exercise13 {

	/** Main method */
	public static void main(String[] args) {
		if (args.length > 0) {
			String s = args[0];
	
			// Display result
			System.out.println("Ignoring non-alphanumeric characters, \nis " + s + " a palindrome? " + isPalindrome(s));
		} else {
			System.out.println("Enter string");
		}
	}

	/** Return true if a string is a palindrome */
	public static boolean isPalindrome(String s) {
		// Create a new string by eliminating non-alphanumeric chars
		String s1 = filter(s);

		// Create a new string that is the reversal of s1
		String s2 = reverse(s1);

		// Compare if the reversal is the same as the original string
		return s2.equals(s1);
	}

	/** Create a new string by eliminating non-alphanumeric chars */
	public static String filter(String s) {
		// Create a string builder
		StringBuilder stringBuilder = new StringBuilder();

		// Examine each char in the string to skip alphanumeric char
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetterOrDigit(s.charAt(i))) {
				stringBuilder.append(s.charAt(i));
			}
		}

		// Return a new filtered string
		return stringBuilder.toString();
	}

	/** Create a new string by reversing a specified string */
	public static String reverse(String s) {
		StringBuilder stringBuilder = new StringBuilder(s);
		stringBuilder.reverse(); // Invoke reverse in StringBuilder
		return stringBuilder.toString();
	}

}
