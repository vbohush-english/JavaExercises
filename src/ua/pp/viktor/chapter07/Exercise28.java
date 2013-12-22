package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise28 {

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
		System.out.println("The two arrays are " + (equals(list1, list2) ? "" : "not ") + "strictly identical");
	}

	public static boolean equals(int[][] m1, int[][] m2) {
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[i].length; j++) {
				if (m1[i][j] != m2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
