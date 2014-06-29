package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise24 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		String binaryString = input.next();
		System.out.println(hexToDecimal(binaryString));
	}
	
	public static int hexToDecimal(String hexString) {
		return hexToDecimal(hexString, 1);
	}

	public static int hexToDecimal(String hexString, int step) {
		if (hexString.length() == 0) {
			return 0;
		} else {
			char tmp = Character.toLowerCase(hexString.charAt(hexString.length() - 1));
			if ((tmp >= '0') && (tmp <= '9')) {
				return (int)(tmp - '0') * step + hexToDecimal(hexString.substring(0, hexString.length() - 1), step * 16);
			} else if ((tmp >= 'a') && (tmp <= 'f')) {
				return (int)(tmp - 'a' + 10) * step + hexToDecimal(hexString.substring(0, hexString.length() - 1), step * 16);
			}  else {
				return 0;
			}
		}
	}

}
