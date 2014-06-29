package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise17 {

	public static void main(String[] args) {
		
		int computer = (int)(Math.random() * 3);
		
		System.out.print("scissor (0), rock (1), paper (2): ");
		Scanner input = new Scanner(System.in);
		int user = input.nextInt();
		input.close();
		
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
		} else {
			System.out.println(". Computer won");
		}


	}
}
