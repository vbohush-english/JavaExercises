package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise19 {

	public static void main(String[] args) {
		System.out.print("Enter list: ");
		Scanner input = new Scanner(System.in);
		int listLength = input.nextInt();
		int[] list = new int[listLength];
		for (int i = 0; i < list.length; i++) {
			list[i] = input.nextInt();
		}
		input.close();
		System.out.println("The list is " + (isSorted(list) ? "already" : "not") + " sorted");
	}
	
	public static boolean isSorted(int[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			if (list[i] > list[i + 1]) {
				return false;
			}
		}
		return true;
	}

}
