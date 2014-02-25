package net.bohush.exercises.chapter22;

import java.util.Scanner;
import java.util.LinkedList;

public class Exercise14 {
	public static void main(String[] args) {
		System.out.print("Enter expression: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String expression = input.nextLine();

		try {
			System.out.println("Result: " + evaluateExpression(expression));
		} catch (Exception ex) {
			System.out.println("Wrong expression: " + expression);
		}
	}

	public static int evaluateExpression(String expression) {
		LinkedList<Integer> operandStack = new LinkedList<Integer>();

		expression = insertBlanks(expression);

		String[] tokens = expression.split(" ");

		for (String token : tokens) {
			if (token.length() == 0) {
		        continue;
			} else if (token.equals("+")) {
				int tmp = 0;
				for (int value : operandStack) {
					tmp += value;
				}
				operandStack.clear();
				operandStack.add(tmp);
			} else if (token.equals("*")) {
				int tmp = 1;
				for (int value : operandStack) {
					tmp *= value;
				}
				operandStack.clear();
				operandStack.add(tmp);
			} else if (token.equals("-")) {
				int tmp = operandStack.getFirst();
				operandStack.removeFirst();
				for (int value : operandStack) {
					tmp -= value;
				}
				operandStack.clear();
				operandStack.add(tmp);
			} else if (token.equals("/")) {
				int tmp = operandStack.getFirst();
				operandStack.removeFirst();
				for (int value : operandStack) {
					tmp /= value;
				}
				operandStack.clear();
				operandStack.add(tmp);
			} else {
				operandStack.add(new Integer(token));
			}
		}
		return operandStack.getFirst();
	}

	public static String insertBlanks(String s) {
		String result = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '+'
					|| s.charAt(i) == '-' || s.charAt(i) == '*'
					|| s.charAt(i) == '/')
				result += " " + s.charAt(i) + " ";
			else
				result += s.charAt(i);
		}

		return result;
	}
}
