package net.bohush.exercises.chapter14;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

public class Exercise23 {

	public static void main(String[] args) throws MalformedURLException, IOException {
		System.out.print("Enter URL: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String urlName = input.nextLine();
		Scanner inputUrl = new Scanner(new java.net.URL(urlName).openStream());
		int sum = 0;
		int count = 0;
		while (inputUrl.hasNextInt()) {
			sum += inputUrl.nextInt();
			count++;
		}
		inputUrl.close();
		System.out.println("Total: " + sum);
		System.out.println("Count: " + count);
		System.out.println("Average: " + sum / (double)count);
	}

}