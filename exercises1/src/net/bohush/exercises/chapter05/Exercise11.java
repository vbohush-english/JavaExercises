package net.bohush.exercises.chapter05;

public class Exercise11 {

	public static void main(String[] args) {
		System.out.println("Sales Amount\tCommission");
		System.out.println("----------------------------");
		for (int amount = 10000; amount <= 100000; amount += 5000) {
			System.out.printf("%d\t\t%9.1f\n", amount, computeCommission(amount));	
		}
	}
	public static double computeCommission(double salesAmount) {
		if (salesAmount <= 5000) {
			return salesAmount * 0.08;
		} else if ((salesAmount > 5000) && (salesAmount <= 10000)) {
			return 5000 * 0.08 + (salesAmount - 5000) * 0.10;
		} else {
			return 5000 * 0.08 + 5000 * 0.10 + (salesAmount - 10000) * 0.12;
		}
	}

}
