package net.bohush.exercises.chapter10;

import java.math.BigDecimal;

public class Exercise20 {

	public static void main(String[] args) {
		for (int count = 100; count <= 1000; count += 100) {
			BigDecimal e = BigDecimal.ONE;
			double item = 1;
			for (int i = 2; i <= count; i ++) {
				e = e.add(new BigDecimal(item));
				item /= i;
			}
			System.out.println("e for i = " + count + ": " + e);
		}
	}

}
