package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise18 {
	
	public static int numberOfMoves = 0;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number of disks: ");
		int n = input.nextInt();

		System.out.println("The moves are:");
		moveDisks(n, 'A', 'B', 'C');
		System.out.println("Number of moves: " + numberOfMoves );
	}


	public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
		numberOfMoves++;
		if (n == 1) {
			System.out.println("Move disk " + n + " from " + fromTower + " to "	+ toTower);
		} else {
			moveDisks(n - 1, fromTower, auxTower, toTower);
			System.out.println("Move disk " + n + " from " + fromTower + " to "	+ toTower);
			moveDisks(n - 1, auxTower, toTower, fromTower);
		}
	}
}