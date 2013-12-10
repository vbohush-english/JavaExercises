package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		System.out.print("Enter the number of students: ");
		Scanner input = new Scanner(System.in);
		int numberOfStudents = input.nextInt();
		if (numberOfStudents > 0 ) {
			
			System.out.print("Enter student's name: ");
			String highestStudentsName = input.next();
			System.out.print("Enter student's score: ");
			int highestStudentsScore = input.nextInt();	
			
			for (int i = 1; i < numberOfStudents; i++) {
				
				System.out.print("Enter student's name: ");
				String StudentsName = input.next();
				System.out.print("Enter student's score: ");
				int StudentsScore = input.nextInt();
				
				if (StudentsScore > highestStudentsScore) {
					highestStudentsScore = StudentsScore;
					highestStudentsName = StudentsName;
				}
			}
			input.close();
			System.out.println("The name of the student with the highest score: " + highestStudentsName);
		} else {
			System.out.println("The number of students must be greater than 1");
		}
	}
}
