package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise33 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter weight and price for package 1: ");
		double weight1 = input.nextDouble();
		double price1 = input.nextDouble();
		System.out.print("Enter weight and price for package 2: ");
		double weight2 = input.nextDouble();
		double price2 = input.nextDouble();
		input.close();
		System.out.print("Package " + (weight1 / price1 > weight2 / price2 ? 1 : 2) + " has a better price.");
	}

}
