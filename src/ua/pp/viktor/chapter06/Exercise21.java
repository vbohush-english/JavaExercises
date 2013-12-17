package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise21 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of balls to drop: ");
		int numberOfBalls = input.nextInt();
		System.out.print("Enter the number of slots in the bean machine: ");
		int numberOfSlots = input.nextInt();
		input.close();
		
		int[] slots = new int[numberOfSlots + 1];
		
		System.out.println();
		for (int i = 0; i < numberOfBalls; i++) {
			int slot = 0;
			for (int j = 0; j < numberOfSlots; j++) {
				if ((int)(Math.random() * 2) == 1) {
					System.out.print("R");	
					slot++;
				} else {
					System.out.print("L");
				}
			}
			slots[slot]++;
			System.out.println();
		}
		
		int maxBalls = slots[0];
		for (int i = 1; i < slots.length; i++) {
			if (maxBalls < slots[i]) {
				maxBalls = slots[i];
			}
		}
		
		System.out.println();
		for (int i = maxBalls; i > 0; i--) {
			for (int j = 0; j < slots.length; j++) {
				System.out.print((slots[j] >= i) ? "0" : " ");
			}
			System.out.println();
		}
		
		
	}

}
