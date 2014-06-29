package net.bohush.exercises.chapter27;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise24 {

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
		int min = Collections.min(objects);
		if(min <= max) {
			String result = "";
			for (int i = 0; i < objects.size(); i++) {
				if((objects.get(i) > min) && (objects.get(i) <= max)) {
					min = objects.get(i);
				}
			}
			result += min + " ";
			objects.remove(new Integer(min));
			return result + getConteiner(objects, max - min);			
		} else {
			return "";
		}
	}

}
