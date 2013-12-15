package ua.pp.viktor.chapter05;

public class Exercise28 {

	public static void main(String[] args) {
		for (int p = 2; p <= 31; p++) {
			int p2 = (int)(Math.pow(2, p) - 1);
			if (Exercise10.isPrime(p2)) {
				System.out.println(p + "\t" + p2);
			}
		}
	}

}
