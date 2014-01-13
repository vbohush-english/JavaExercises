package net.bohush.exercises.chapter15;

public class Exercise15 {

	public static void main(String[] args) throws CloneNotSupportedException {
		Course c1 = new Course("tratata");
		c1.addStudent("qwe");
		c1.addStudent("asd");
		c1.addStudent("zxc");
		Course c2 = (Course)(c1.clone());
		System.out.println(c1.getStudents() == c2.getStudents());
		System.out.println(c1.getStudents()[0] == c2.getStudents()[0]);
		System.out.println(c1.getCourseName() == c2.getCourseName());
	}

}

class Course implements Cloneable {
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
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Course cloneCourse = (Course)super.clone();
		cloneCourse.students = (String[])(students.clone());
		return cloneCourse;
	}
}