package net.bohush.exercises.chapter14;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise25 {

	public static void main(String[] args) throws MalformedURLException, IOException {
		//String urlName = "http://cs.armstrong.edu/liang/data/Salary.txt";
		//Scanner input = new Scanner(new java.net.URL(urlName).openStream());
		String fileName = "tmp/salary.txt";
		Scanner input = new Scanner(new File(fileName));
		ArrayList<Double> assistant = new ArrayList<>();
		ArrayList<Double> associate = new ArrayList<>();
		ArrayList<Double> full = new ArrayList<>();
		while (input.hasNext()) {
			input.next(); input.next();
			String type = input.next();
			if (type.equals("assistant")) {
				assistant.add(input.nextDouble());
			} else if (type.equals("associate")) {
				associate.add(input.nextDouble());
			} else {
				full.add(input.nextDouble());
			}
			
		}
		input.close();

		double assistantSum = sum(assistant);
		double associateSum = sum(associate);
		double fullSum = sum(full);
		
		System.out.printf("Summary of assistants: %.2f\n", assistantSum);
		System.out.printf("Summary of associates: %.2f\n", associateSum);
		System.out.printf("Summary of full: %.2f\n", fullSum);
		System.out.printf("Summary of all faculty: %.2f\n\n", assistantSum + associateSum + fullSum);
		
		System.out.printf("Average of assistants: %.2f\n", assistantSum / assistant.size());
		System.out.printf("Average of associates: %.2f\n", associateSum / associate.size());
		System.out.printf("Average of full: %.2f\n", fullSum / full.size());
		System.out.printf("Average of all faculty: %.2f\n\n", (assistantSum + associateSum + fullSum) / (assistant.size() + associate.size() + full.size()));

	}

	public static double sum(ArrayList<Double> array) {
		double sum = 0;
		for (Double value : array) {
			sum += value;
		}return sum;
	}

}