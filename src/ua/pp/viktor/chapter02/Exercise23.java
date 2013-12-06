package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise23 {

	public static void main(String[] args) {
		System.out.print("Enter an ASCII code: ");
		Scanner input = new Scanner(System.in);
		int asciiCode = input.nextInt();
		input.close();
		System.out.println("The character is " + (char)asciiCode);
	}

}
