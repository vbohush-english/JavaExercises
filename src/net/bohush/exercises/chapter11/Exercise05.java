package net.bohush.exercises.chapter11;

import java.util.ArrayList;

public class Exercise05 {

	public static void main(String[] args) {
		Course course1 = new Course("Data Structures");
		Course course2 = new Course("Database Systems");

		course1.addStudent("Peter Jones");
		course1.addStudent("Brian Smith");
		course1.addStudent("Anne Kennedy");

		course2.addStudent("Peter Jones");
		course2.addStudent("Steve Smith");

		for (int i = 0; i < 25; i++) {
			course1.addStudent("Student n" + i);
		}
		
		course1.dropStudent("Student n5");
		System.out.println("Number of students in course1: " + course1.getNumberOfStudents());
		String[] students = course1.getStudents();
		for (int i = 0; i < course1.getNumberOfStudents(); i++)
			System.out.print(students[i] + ", ");

		System.out.println();
		System.out.println("Number of students in course2: " + course2.getNumberOfStudents());
		course2.clear();
		System.out.println("Number of students in course2: " + course2.getNumberOfStudents());
	}

}

class Course {
	private String courseName;
	private ArrayList<String> students = new ArrayList<>();

	public Course(String courseName) {
		this.courseName = courseName;
	}

	public void addStudent(String student) {
		students.add(student);
	}

	public String[] getStudents() {
		return students.toArray(new String[0]);
	}

	public int getNumberOfStudents() {
		return students.size();
	}

	public String getCourseName() {
		return courseName;
	}

	public void dropStudent(String student) {
		students.remove(student);
	}
	
	public void clear() {
		students.clear();
	}
}

