package net.bohush.exercises.chapter22;

import java.util.Scanner;
import java.util.Stack;

public class Exercise16 {

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

	/** Evaluate an expression */
	public static String evaluateExpression(String expression) {

		// Create operatorStack to store operators
		Stack<Character> operatorStack = new Stack<Character>();

		// Insert blanks around (, ), +, -, /, and *
		expression = insertBlanks(expression);

		// Extract operands and operators
		String[] tokens = expression.split(" ");

		String result = "";
		
		// Phase 1: Scan tokens
		for (String token : tokens) {
			if (token.length() == 0) // Blank space
				continue; // Back to the while loop to extract the next token
			else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
				// Process all +, -, *, / in the top of the operator stack
				while (!operatorStack.isEmpty()
						&& (operatorStack.peek() == '+'
								|| operatorStack.peek() == '-'
								|| operatorStack.peek() == '*' || operatorStack
								.peek() == '/')) {
					result += operatorStack.pop() + " ";
								
				}

				// Push the + or - operator into the operator stack
				operatorStack.push(token.charAt(0));
			} else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
				// Process all *, / in the top of the operator stack
				while (!operatorStack.isEmpty()
						&& (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
					result += operatorStack.pop() + " ";
			
				}

				// Push the * or / operator into the operator stack
				operatorStack.push(token.charAt(0));
			} else if (token.trim().charAt(0) == '(') {
				operatorStack.push('('); // Push '(' to stack
			} else if (token.trim().charAt(0) == ')') {
				// Process all the operators in the stack until seeing '('
				while (operatorStack.peek() != '(') {
					result += operatorStack.pop() + " ";
			
				}

				operatorStack.pop(); // Pop the '(' symbol from the stack
			} else { // An operand scanned
				// Push an operand to the stack
				//operandStack.push(new Integer(token));
				result += token + " ";
			}
		}

		// Phase 2: process all the remaining operators in the stack
		while (!operatorStack.isEmpty()) {
			result += operatorStack.pop() + " ";
			
		}

		// Return the result
		return result;
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
