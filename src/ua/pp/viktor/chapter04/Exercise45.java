package ua.pp.viktor.chapter04;

public class Exercise45 {

	  public static void main(String[] args) {
			int combinations = 0;
		    for (int i = 1; i <= 7; i++) {
		    	for (int j = i + 1; j <= 7; j++) {
		    		System.out.println(i + " " + j);
		    		combinations++;
		    	}
		    }
		    System.out.println("The total number of all combinations is " + combinations);
		  }

}
