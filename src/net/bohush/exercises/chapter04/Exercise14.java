package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise14 {

	public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);

	    System.out.print("Enter first integer: ");
	    int n1 = input.nextInt();
	    System.out.print("Enter second integer: ");
	    int n2 = input.nextInt();
	    input.close();
	    
	    for (int d = n1 > n2 ? n2 : n1; d > 0; d--) {
	    	if ((n1 % d == 0) && (n2 % d == 0)) {
	    	    System.out.println("The greatest common divisor for " + n1 + " and " + n2 + " is " + d);
	    		break;
	    	}
	    }
	}

}
