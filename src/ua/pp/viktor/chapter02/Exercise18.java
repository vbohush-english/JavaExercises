package ua.pp.viktor.chapter02;

public class Exercise18 {

	public static void main(String[] args) {
		System.out.println("a\tb\tpow(a,b)");
		int first = 1;
		System.out.println(first + "\t" + (first + 1) + "\t" + (int)Math.pow(first, ++first));
		System.out.println(first + "\t" + (first + 1) + "\t" + (int)Math.pow(first, ++first));
		System.out.println(first + "\t" + (first + 1) + "\t" + (int)Math.pow(first, ++first));
		System.out.println(first + "\t" + (first + 1) + "\t" + (int)Math.pow(first, ++first));
		System.out.println(first + "\t" + (first + 1) + "\t" + (int)Math.pow(first, ++first));
	}

}
