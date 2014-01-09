package net.bohush.exercises.chapter14;

public class Exercise01 {

	public static void main(String[] args) {
		// Check number of strings passed
		if (args.length != 1) {
			System.out.println("Usage: java Calculator \"operand1 operator operand2\"");
			System.exit(0);
		}
		try {
			System.out.println(calculate(args[0]));
		} catch (NumberFormatException2 e){
			System.out.println(e.getMessage());
		}
	}

	public static String calculate(String expression) {
		int result = 0;

		String[] tokens = expression.split(" ");
		
		int x;
		int y; 
		try {
			x = Integer.parseInt(tokens[0]);
		} catch (NumberFormatException ex) {
			throw new NumberFormatException2(tokens[0]);
		}
		
		try {
			y = Integer.parseInt(tokens[2]);
		} catch (NumberFormatException ex) {
			throw new NumberFormatException2(tokens[2]);
		}
		
		switch (tokens[1].charAt(0)) {
		case '+':
			result = x + y;
			break;
		case '-':
			result = x - y;
			break;
		case '*':
			result = x * y;
			break;
		case '/':
			result = x / y;
		}
		return tokens[0] + ' ' + tokens[1] + ' ' + tokens[2] + " = " + result;
	}

}

class NumberFormatException2 extends NumberFormatException {

	private static final long serialVersionUID = 1L;
	String wrongData;

	NumberFormatException2(String wrongData) {
		super("Wrong input: " + wrongData);
		this.wrongData = wrongData;
	}

	public String getWrongData() {
		return wrongData;
	}
}
