package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter binary string: \n");
		String string = "0001111110100110111111111100110";//input.next();
		System.out.print(binaryToDecimal(string));
	}

	public static String binaryToDecimal(String binaryString) {
		long intRresult = 0;
		String result = "";
		long twoPow = 1;
		long hexPow = 1;
		for (int i = binaryString.length() - 1; i >= 0; i--) {
			if (binaryString.charAt(i) != '0') {
				System.out.println("- " + intRresult + "\t" + twoPow + "\t" + hexPow + "\t" + result);
				intRresult += twoPow;
				System.out.println("* " + intRresult + "\t" + twoPow + "\t" + hexPow + "\t" + result);
				if (intRresult > hexPow * 16) {
					result = "-" + (intRresult - twoPow)/hexPow + result;
					hexPow *= 16;
					System.out.println("+ " + intRresult + "\t" + twoPow + "\t" + hexPow + "\t" + result);
				}
			}
			if (i == 0) {
				result = "---" + (intRresult - twoPow)/hexPow + result;			
			}
			twoPow *= 2;
		}
		System.out.println("fin\n" + intRresult + "\n" + twoPow + "\n" + hexPow + "\n" + result);
		return result;
	}
}
