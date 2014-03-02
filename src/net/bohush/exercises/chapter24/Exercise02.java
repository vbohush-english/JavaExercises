package net.bohush.exercises.chapter24;

import java.util.LinkedList;
import java.util.Scanner;

public class Exercise02 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String string = input.next();
		char[] charString = string.toCharArray();
		
		LinkedList<Character> result = new LinkedList<>();
		for (int j = 0; j < charString.length; j++) {
			LinkedList<Character> tmpResult = new LinkedList<>();
			tmpResult.add(charString[j]);
			for (int i = j + 1; i < charString.length; i++) {
				if(charString[i] > tmpResult.getLast()) {
					tmpResult.add(charString[i]);
				}
			}
			if(result == null) {
				result = tmpResult;				
			} else if (tmpResult.size() > result.size()) {
				result = tmpResult;
			}						
		}
		for (Character character : result) {
			System.out.print(character);
		}
		

	}

}
