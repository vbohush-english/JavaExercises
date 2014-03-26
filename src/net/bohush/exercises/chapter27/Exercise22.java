package net.bohush.exercises.chapter27;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise22 {

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
		String result = "";
		for (int i = 0; i < objects.size(); i++) {
			if(objects.get(i) < max) {
				int tmp = objects.get(i);
				result += tmp + " ";
				objects.remove(i);
				return result + getConteiner(objects, max - tmp);
			}
		}
		return result;
	}

}
