package net.bohush.exercises.chapter27;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise23 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of objects: ");
		int numberOfObjects = input.nextInt();
		ArrayList<Integer> objects = new ArrayList<>();
		System.out.print("Enter the weights of the objects: ");
		for (int i = 0; i < numberOfObjects; i++) {
			objects.add(input.nextInt());
		}
		int container = 1;
		while(!objects.isEmpty()) {
			System.out.println("Container " + container++ + " contains objects with weight " + getConteiner(objects, 10));
		}
	}
	
	static String getConteiner(ArrayList<Integer> objects, int max) {
		if(objects.isEmpty()) {
			return "";
		}
		String result = "";
		int min = Collections.min(objects);
		if(min <= max) {
			result += min + " ";
			objects.remove(new Integer(min));
			return result + getConteiner(objects, max - min);
		}
		return result;
	}

}
