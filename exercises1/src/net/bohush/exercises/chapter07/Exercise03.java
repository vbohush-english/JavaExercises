package net.bohush.exercises.chapter07;

public class Exercise03 {

	public static void main(String args[]) {
		// Students' answers to the questions
		char[][] answers = {
				{ 'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D' },
				{ 'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D' },
				{ 'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D' },
				{ 'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D' },
				{ 'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D' },
				{ 'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D' },
				{ 'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D' },
				{ 'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D' } };

		// Key to the questions
		char[] keys = { 'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D' };

		int[][] students = new int[answers.length][2];

		// Grade all answers
		for (int i = 0; i < answers.length; i++) {
			// Grade one student
			int correctCount = 0;
			for (int j = 0; j < answers[i].length; j++) {
				if (answers[i][j] == keys[j])
					correctCount++;
			}
			students[i][0] = i;
			students[i][1] = correctCount;
		}
		
		for (int i = 0; i < students.length - 1; i++) {
			for (int j = i + 1; j < students.length; j++) {
				if (students[i][1] > students[j][1]) {
					int tmp0 = students[i][0];
					students[i][0] = students[j][0];
					students[j][0] = tmp0;
					int tmp1 = students[i][1];
					students[i][1] = students[j][1];
					students[j][1] = tmp1;
				}
			}
		}
		
		for (int i = 0; i < students.length; i++) {
			System.out.println("Student " + students[i][0] + "'s correct count is " + students[i][1]);
		}
	}

}
