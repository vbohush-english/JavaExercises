package ua.pp.viktor.chapter02;

import java.util.Scanner;


public class Exercise24 {
  public static void main(String[] args) {   
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Receive the amount 
    System.out.print("Enter an amount in int, for example 1156: ");
    
    int amount = input.nextInt();

    int remainingAmount = amount;
    
    input.close();
    
    
    // Find the number of one dollars
    int numberOfOneDollars = remainingAmount / 100;
    remainingAmount = remainingAmount % 100;

    // Find the number of quarters in the remaining amount
    int numberOfQuarters = remainingAmount / 25;
    remainingAmount = remainingAmount % 25;

    // Find the number of dimes in the remaining amount
    int numberOfDimes = remainingAmount / 10;
    remainingAmount = remainingAmount % 10;

    // Find the number of nickels in the remaining amount
    int numberOfNickels = remainingAmount / 5;
    remainingAmount = remainingAmount % 5;

    // Find the number of pennies in the remaining amount
    int numberOfPennies = remainingAmount;

    // Display results
    System.out.println("Your amount " + amount / 100 + "." +
    		amount % 100 + " consists of \n" + 
      "\t" + numberOfOneDollars + " dollars\n" + 
      "\t" + numberOfQuarters + " quarters\n" +
      "\t" + numberOfDimes + " dimes\n" + 
      "\t" + numberOfNickels + " nickels\n" +
      "\t" + numberOfPennies + " pennies");
  }
}
