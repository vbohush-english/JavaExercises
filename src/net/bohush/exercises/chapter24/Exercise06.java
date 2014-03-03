package net.bohush.exercises.chapter24;

import java.util.ArrayList;

public class Exercise06 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 40; i <= 46; i++) {
			list.add(fib(i));
		}
		System.out.println("\t\t|\t40\t41\t42\t43\t44\t45");
		System.out.println("----------------|----------------------------------------------------");
		System.out.print("gcd\t\t|\t");
		for (int i = 0; i < list.size() - 1; i++) {
			long startTime = System.currentTimeMillis();
			gcd(list.get(i), list.get(i + 1));
			long endTime = System.currentTimeMillis();
			long executionTime = endTime - startTime;
			System.out.print(executionTime + "\t");
		}
		System.out.println("\n----------------|----------------------------------------------------");
		System.out.print("gcdEuclid\t|\t");
		for (int i = 0; i < list.size() - 1; i++) {
			long startTime = System.currentTimeMillis();
			gcdEuclid(list.get(i), list.get(i + 1));
			long endTime = System.currentTimeMillis();
			long executionTime = endTime - startTime;
			System.out.print(executionTime + "\t");
		}
		
	}

	public static int fib(int n) {
		int f0 = 0;
		int f1 = 1;
		int f2 = 1;

		if (n == 0)
			return f0;
		else if (n == 1)
			return f1;
		else if (n == 2)
			return f2;

		for (int i = 3; i <= n; i++) {
			f0 = f1;
			f1 = f2;
			f2 = f0 + f1;
		}

		return f2;
	}

	public static int gcd(int m, int n) {
		int gcd = 1;

		if (m % n == 0)
			return n;

		for (int k = n / 2; k >= 1; k--) {
			if (m % k == 0 && n % k == 0) {
				gcd = k;
				break;
			}
		}

		return gcd;
	}

	public static int gcdEuclid (int m, int n) {
		if (m % n == 0)
			return n;
		else
			return gcd(n, m % n);
	}
}
