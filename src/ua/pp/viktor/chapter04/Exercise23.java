package ua.pp.viktor.chapter04;

public class Exercise23 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double number = 0;
		for (int n = 50000; n > 0; n--) {
			number += 1.0 / n;			
		}
		System.out.println(number);
	}

}
