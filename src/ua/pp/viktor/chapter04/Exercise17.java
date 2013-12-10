package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise17 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of lines: ");
		//int number = input.nextInt();
		System.out.println();int number = 7;
		input.close();

		for (int i = 0; i < number; i++) {
			for (int j = 7, count = 1; count < number*2; count++) {
				System.out.print("\t");
				if (j <=  (i + 1)) {
					 System.out.print(j);
				}
				if (count < number) {
					j--;
				} else {
					j++;
				}
			}
			System.out.println();
		}
	}

}
