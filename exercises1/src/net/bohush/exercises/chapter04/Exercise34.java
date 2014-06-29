package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise34 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int userWinCount = 0;
		int computerWinCount = 0;
		
		while ((userWinCount < 2) && (computerWinCount < 2)) {
			
			int computer = (int)(Math.random() * 3);
			System.out.print("scissor (0), rock (1), paper (2): ");
			int user = input.nextInt();
			
			if ((user < 0) || (user > 2)) {
				System.out.println("Incorect input");	
				continue;
			}
			
			System.out.print("The computer is ");
			switch(computer) {
			case 0: System.out.print("scissor"); break;
			case 1: System.out.print("rock"); break;
			case 2: System.out.print("paper"); break;
			}
			System.out.print(". You are "); 
			switch(user) {
			case 0: System.out.print("scissor"); break;
			case 1: System.out.print("rock"); break;
			case 2: System.out.print("paper"); break;
			}		
			
			if (computer == user) {
				System.out.println(" too. It is a draw");
			} else if (((user == 0) && (computer == 2)) || ((user == 1) && (computer == 0)) || ((user == 2) && (computer == 1))) {
				System.out.println(". You won");	
				userWinCount++;
			} else {
				System.out.println(". Computer won");
				computerWinCount++;
			}
			System.out.println("Computer " + computerWinCount + ":" + userWinCount + " You");
		}
		
		input.close();
		
		if (computerWinCount > userWinCount) {
			System.out.println("\nComputer won the match");
		} else {
			System.out.println("\nYou won the match");
		}

	}

}
