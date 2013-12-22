package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise29 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final int LENGTH = 3;
		
		System.out.print("Enter list1: ");		
		int[][] list1 = new int[LENGTH][LENGTH];
		for (int i = 0; i < list1.length; i++) {
			for (int j = 0; j < list1[i].length; j++) {
				list1[i][j] = input.nextInt();	
			}			
		}
		
		System.out.print("Enter list2: ");		
		int[][] list2 = new int[LENGTH][LENGTH];
		for (int i = 0; i < list2.length; i++) {
			for (int j = 0; j < list2[i].length; j++) {
				list2[i][j] = input.nextInt();	
			}			
		}
		
		input.close();
		System.out.println("The two arrays are " + (equals(list1, list2) ? "" : "not ") + "identical");
	}
	
	public static boolean equals(int[][] m1, int[][] m2) {
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[i].length; j++) {
				if (!isNumber(m1[i][j], m2)) {
					return false;
				}
			}			
		}
		
		return true;
	}
	
	public static boolean isNumber(int number, int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}

}
