package ua.pp.viktor.chapter04;

public class Exercise25 {

	public static void main(String[] args) {
		double pi = 0;
		for (int i = 1; i <= 10000; i ++) {
			pi +=  Math.pow(-1, i + 1)/ (2 * i - 1);
		}
		System.out.println("P for  i = 10000: " + 4 * pi);
		pi = 0;
		for (int i = 1; i <= 20000; i ++) {
			pi +=  Math.pow(-1, i + 1)/ (2 * i - 1);
		}
		System.out.println("P for  i = 20000: " + 4 * pi);
		pi = 0;
		for (int i = 1; i <= 100000; i ++) {
			pi +=  Math.pow(-1, i + 1)/ (2 * i - 1);
		}
		System.out.println("P for i = 100000: " + 4 * pi);
	}

}
