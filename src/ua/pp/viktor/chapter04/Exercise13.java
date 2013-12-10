package ua.pp.viktor.chapter04;

public class Exercise13 {

	public static void main(String[] args) {
		int n = 0;
		while ((n + 1) * (n + 1) * (n + 1) < 12000) {
			n++;
		}
		System.out.println(n);
	}

}
