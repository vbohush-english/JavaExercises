package net.bohush.exercises.chapter10;

public class Exercise09 {

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
	private String[] students = new String[10];
	private int numberOfStudents;

	public Course(String courseName) {
		this.courseName = courseName;
	}

	public void addStudent(String student) {
		if (numberOfStudents == students.length) {
			String[] tmp = new String[students.length * 2];
			System.arraycopy(students, 0, tmp, 0, students.length);
			students = tmp;
		}
		students[numberOfStudents] = student;
		numberOfStudents++;
	}

	public String[] getStudents() {
		return students;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public String getCourseName() {
		return courseName;
	}

	public void dropStudent(String student) {
		for (int i = 0; i < numberOfStudents; i++) {
			if (student.equals(students[i])) {
				System.arraycopy(students, i + 1, students, i, numberOfStudents - i - 1);
				students[numberOfStudents - 1] = null;
				numberOfStudents--;
				break;
			}
		}
	}
	
	public void clear() {
		students = new String[10];
		numberOfStudents = 0;
	}
}