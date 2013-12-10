package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		System.out.print("Enter the number of students: ");
		Scanner input = new Scanner(System.in);
		int numberOfStudents = input.nextInt();
		if (numberOfStudents > 1 ) {
			
			System.out.print("Enter student's name: ");
			String highestStudentsName = input.next();
			System.out.print("Enter student's score: ");
			int highestStudentsScore = input.nextInt();	
			
			System.out.print("Enter student's name: ");
			String highStudentsName = input.next();
			System.out.print("Enter student's score: ");
			int highStudentsScore = input.nextInt();	
			
			if (highestStudentsScore < highStudentsScore) {
				int tmp = highestStudentsScore;
				highestStudentsScore = highStudentsScore;
				highStudentsScore = tmp;
				String tmpString = highestStudentsName;
				highestStudentsName = highStudentsName;
				highStudentsName = tmpString;				
			}
			
			for (int i = 2; i < numberOfStudents; i++) {
				
				System.out.print("Enter student's name: ");
				String StudentsName = input.next();
				System.out.print("Enter student's score: ");
				int StudentsScore = input.nextInt();
				
				if (StudentsScore > highStudentsScore) {
					if (StudentsScore > highestStudentsScore) {
						int tmpScore = highestStudentsScore;
						String tmpName = highestStudentsName;
						highestStudentsScore = StudentsScore;
						highestStudentsName = StudentsName;	
						if (tmpScore > highStudentsScore) {
							highStudentsScore = tmpScore;
							highStudentsName = tmpName;								
						}
					} else {
						highStudentsScore = StudentsScore;
						highStudentsName = StudentsName;						
					}
				}
			}
			
			input.close();
			System.out.println(highestStudentsName + " - " + highestStudentsScore);
			System.out.println(highStudentsName + " - " + highStudentsScore);
		} else {
			System.out.println("The number of students must be greater than 1");
		}
	}
}
