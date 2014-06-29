package net.bohush.exercises.chapter06;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final int NUMBER_OF_SCORES  = 100;
		int[] scores = new int[NUMBER_OF_SCORES];
		Arrays.fill(scores, -1);
		System.out.print("Enter scores: ");
		double average = 0;
		int count = 0;
		for (int i = 0; i < NUMBER_OF_SCORES; i++) {
			scores[i] = input.nextInt();
			if (scores[i] < 0) {
				break;
			}
			average += scores[i];
			count++;
		}
		average /= count;
		
		input.close();
		
		int aboveAverage = 0;
		int belowAverage = 0;
		for (int i = 0; i < count; i++) {
			if (scores[i] >= average) {
				aboveAverage++;
			} else {
				belowAverage++;
			}
		}
		
		System.out.println("Average " + average);
		System.out.println("Count " + count);
		System.out.println("Above or equal to the average " + aboveAverage);
		System.out.println("Below the average " + belowAverage);

	}

}
