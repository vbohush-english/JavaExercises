package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise14 {

	public static void main(String[] args) {
		System.out.print("Enter 5 numbers: ");
		Scanner input = new Scanner(System.in);
		final int N = 5;
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = input.nextInt();
		}
		input.close();
		System.out.print("gcd = " + gcd(numbers));
	}
	
	public static int gcd(int... numbers) {
		int gcd = 1;
		int k = 1;
		while (k <= min(numbers)) {			
			boolean isOk = true;			
			for (int data:numbers) {
				if (data % k != 0) {
					isOk = false;
					break;
				}
			}			
			if (isOk)
				gcd = k;			
			k++;
		}
		return gcd;
	}
	
	public static int min(int[] array) {
		int min = array[0];
		for (int data:array) {
			if (data < min) {
				min = data;
			}
		}
		return min;		
	}
	
}
