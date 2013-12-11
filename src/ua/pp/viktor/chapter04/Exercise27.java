package ua.pp.viktor.chapter04;

public class Exercise27 {

	public static void main(String args[]) {
	    for (int year = 2001, count = 0; year <= 2100; year++) {
	    	if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
	    		System.out.print(year + (++count % 8 == 0 ? "\n" : " "));
	    	}
	    }
	}

}
