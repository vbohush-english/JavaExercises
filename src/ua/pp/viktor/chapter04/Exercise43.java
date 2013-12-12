package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise43 {

	public static void main(String[] args) {
		System.out.print("Enter the number of seconds: ");
		Scanner input = new Scanner(System.in);
		int seconds = input.nextInt();
		input.close();
		while (seconds > 0) {
			long nextSecond = System.currentTimeMillis() + 1000;	
			while (System.currentTimeMillis() < nextSecond) {				
			}
			seconds--;
			if (seconds > 0)
				System.out.println(seconds + " seconds remaining");
		}
		System.out.println("Stopped");
	}

}
