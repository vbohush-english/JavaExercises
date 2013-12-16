package ua.pp.viktor.chapter06;

import java.util.Arrays;

public class Exercise07 {

	public static void main(String[] args) {
		int[] counts = new int[10];
		Arrays.fill(counts, 0);
		for (int i = 0; i < 100; i++) {
			int number = (int)(Math.random() * 10);
			counts[number]++;
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(i + " - " + counts[i]);
		}
	}

}
