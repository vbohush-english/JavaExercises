package net.bohush.exercises.chapter06;

import java.util.Scanner;

public class Exercise01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of students: ");		
		int numberOfStudents = input.nextInt();
		System.out.print("Enter " + numberOfStudents + " scores: ");	
		int[] students = new int[numberOfStudents];
		int best = 0;
		for (int i = 0; i < numberOfStudents; i++) {
			students[i] = input.nextInt();
			if (students[i] > best) {
				best = students[i];
			}
		}
		input.close();
		for (int i = 0; i < numberOfStudents; i++) {
			System.out.print("Student " + i + " score is " + students[i] + " and grade is ");
			char grade;
			if (students[i] >= best - 10) {
				grade = 'A';
			} else if (students[i] >= best - 20) {
				grade = 'B';
			} else if (students[i] >= best - 30) {
				grade = 'C';
			} else if (students[i] >= best - 40) {
				grade = 'D';
			} else {
				grade = 'F';
			}
			System.out.println(grade);
		}
	}

}
