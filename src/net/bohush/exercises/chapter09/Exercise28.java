package net.bohush.exercises.chapter09;

public class Exercise28 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Calculator \"operand1 operator operand2\"");
			System.exit(0);
		}

		int result = 0;
		 if (args[0].length() == 3) {
			 args[0] = args[0].charAt(0) + " " + args[0].charAt(1) + " " + args[0].charAt(2);
		 }
		
		String[] tokens = args[0].split(" ");

		switch (tokens[1].charAt(0)) {
		case '+':
			result = Integer.parseInt(tokens[0]) + Integer.parseInt(tokens[2]);
			break;
		case '-':
			result = Integer.parseInt(tokens[0]) - Integer.parseInt(tokens[2]);
			break;
		case '*':
			result = Integer.parseInt(tokens[0]) * Integer.parseInt(tokens[2]);
			break;
		case '/':
			result = Integer.parseInt(tokens[0]) / Integer.parseInt(tokens[2]);
		}

		System.out.println(tokens[0] + ' ' + tokens[1] + ' ' + tokens[2]
				+ " = " + result);
	}
}