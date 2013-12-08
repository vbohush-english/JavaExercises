package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise35 {

	public static void main(String[] args) {
		System.out.print("Enter a decimal value (0 to 15): ");
		Scanner input = new Scanner(System.in);
		int decimalValue = input.nextInt();
		input.close();
		if ((decimalValue < 0) || (decimalValue > 15)) {
			System.out.println("Invalid input");	
		} else if ((decimalValue >= 0) && (decimalValue < 10)) {
			System.out.println("The hex value is " + decimalValue);	
		} else {
			System.out.println("The hex value is " + (char)((decimalValue - 10) + 'A'));
		}
		
	}

}
