package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise17 {

	public static void main(String[] args) {
		System.out.print("Enter the number of students: ");
		Scanner input = new Scanner(System.in);
		int numberOfStudents = input.nextInt();
		String[] studentsName = new String[numberOfStudents];
		int[] studentsScores = new int[numberOfStudents];
		
		for (int i = 0; i < numberOfStudents; i++) {
			System.out.print("Enter student's name: ");
			studentsName[i] = input.next();
			System.out.print("Enter student's score: ");
			studentsScores[i] = input.nextInt();			
		}
		input.close();
		
		for(int i = 0; i < numberOfStudents; i++) {
			for(int j = i + 1; j < numberOfStudents; j++) {
				if (studentsScores[j] < studentsScores[i]) {
					int tmpScore = studentsScores[i];
					studentsScores[i] = studentsScores[j];
					studentsScores[j] = tmpScore;
					String tmpname = studentsName[i];
					studentsName[i] = studentsName[j];
					studentsName[j] = tmpname;
				}
			}
		}
		
		
		for(int i = 0; i < numberOfStudents; i++) {
			System.out.println(studentsName[i] + ": " + studentsScores[i]);
		}
	}

}
