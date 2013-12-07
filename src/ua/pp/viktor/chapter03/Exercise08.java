package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise08 {

	public static void main(String[] args) {
		System.out.print("Enter 3 numbers: ");
		Scanner input = new Scanner(System.in);
		int num1 = input.nextInt();
		int num2 = input.nextInt();
		int num3 = input.nextInt();
		input.close();
		if (num1 > num2) {
			int tmp = num2;
			num2 = num1;
			num1 = tmp;
		}
		if (num3 < num2) {
			int tmp = num2;
			num2 = num3;
			num3 = tmp;
		}
		if (num1 > num2) {
			int tmp = num2;
			num2 = num1;
			num1 = tmp;
		} 
		System.out.println("Sorted numbers: " + num1 + ", " + num2 + ", " + num3);
	}

}
