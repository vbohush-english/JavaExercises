package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise11 {

	public static void main(String[] args) {
		System.out.print("Enter ten numbers: ");
		Scanner input = new Scanner(System.in);
		final int N = 10;
		double[] x = new double[N];
		for (int i = 0; i < N; i++) {
			x[i] = input.nextDouble();
		}	
		System.out.println("The mean is " + mean(x));
		System.out.println("The mean is " + deviation(x));
		input.close();
	}
	
	public static double deviation(double[] x) {
		double deviation = 0;
		double mean = mean(x);
		for(double data:x) {
			deviation += (data - mean) * (data - mean);
		}	
		return Math.sqrt((deviation) / (x.length - 1));
	}
	
	public static double mean(double[] x) {
		double mean = 0;
		for(double data:x) {
			mean += data;
		}
		return mean / x.length;
	}

}
