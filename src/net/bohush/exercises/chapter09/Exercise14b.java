package net.bohush.exercises.chapter09;

public class Exercise14b {

	public static void main(String[] args) {
		int sum = 0;
		String tmpString = "";
		for (int i = 0; i < args[0].length(); i++) {
			if (args[0].charAt(i) != ' ') {
				tmpString += args[0].charAt(i);
			} else {
				sum += Integer.parseInt(tmpString);
				tmpString = "";
			}			
		}
		sum += Integer.parseInt(tmpString);
		System.out.println("Total is " + sum);
	}

}
