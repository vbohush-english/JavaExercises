package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise47 {

	public static void main(String[] args) {
		System.out.print("Enter ten numbers: "); //1 2 3 4,5 5,6 6 7 8 9 10
		Scanner input = new Scanner(System.in);
		double mean = 0;
		double deviation = 0;
		int N = 10;
		for (int i = 0; i < N; i++) {
			double tmp = input.nextDouble();
			deviation += tmp * tmp;
			mean += tmp;
		}
		deviation = Math.sqrt((deviation - (mean * mean) / N) / (N - 1));
		mean /= N;
		System.out.println("The mean is " + mean);
		System.out.println("The mean is " + deviation);
		input.close();
	}

}
