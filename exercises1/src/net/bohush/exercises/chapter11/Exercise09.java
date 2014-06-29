package net.bohush.exercises.chapter11;

import java.util.Scanner;
import java.util.ArrayList;

public class Exercise09 {

	public static void main(String[] args) {
		System.out.print("Enter the array size n: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int size = input.nextInt();
		System.out.println("The random array is");
		int[][] array = new int[size][size];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = (int)(Math.random() * 2);
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
		
		ArrayList<Integer> rows = new ArrayList<>();
		ArrayList<Integer> columns = new ArrayList<>();
		
		for (int i = 0; i < array.length; i++) {
			rows.add(0);
			columns.add(0);
			for (int j = 0; j < array[i].length; j++) {
				rows.set(i, rows.get(i) + array[i][j]);
				columns.set(i, columns.get(i) + array[j][i]);
			}
		}
		
		int maxRow = max(rows);
		int maxColumn = max(columns);
		
		
		System.out.print("The largest row index: ");
		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i) == maxRow) {
				System.out.print(i + " ");
			}
		}
		System.out.print("\nThe largest column index: ");
		for (int i = 0; i < columns.size(); i++) {
			if (columns.get(i) == maxColumn) {
				System.out.print(i + " ");
			}
		}
		
	}
	
	public static Integer max(ArrayList<Integer> list) {
		int max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (max < list.get(i)) {
				max = list.get(i);
			}
		}
		return max;
	}

}
