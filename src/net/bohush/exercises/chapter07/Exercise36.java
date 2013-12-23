package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise36 {

	public static void main(String[] args) {
		System.out.print("Enter number n: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int size = input.nextInt();
		char[][] matrix = new char[size][size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = input.next().charAt(0);
			}
			char[] tmp = new char [matrix.length];
			java.lang.System.arraycopy(matrix[i], 0, tmp, 0, matrix[i].length);
			java.util.Arrays.sort(tmp);
			for (int j = 0; j < tmp.length; j++) {
				if (tmp[j] != 'A' + j) {
					System.out.println("Wrong input: the letters must be from A to " + (char)('A' +tmp.length - 1));
					return;
				}
			}
		}
		
		
		for (int i = 0; i < matrix.length; i++) {
			char[] tmp = new char [matrix.length];
			for (int j = 0; j < tmp.length; j++) {
				tmp[j] = matrix[j][i];
			}
			java.util.Arrays.sort(tmp);
			for (int j = 0; j < tmp.length; j++) {
				if (tmp[j] != 'A' + j) {
					System.out.println("Wrong input: the letters must be from A to " + (char)('A' +tmp.length - 1));
					return;
				}
			}
		}
		System.out.println("The input array is a Latin square");
	}
	
	

}
