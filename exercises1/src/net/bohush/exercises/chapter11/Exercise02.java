package net.bohush.exercises.chapter11;

public class Exercise02 {

	public static void main(String[] args) {
		Person p = new Person("Viktor", "Kiev", "102", "a@a.com");
		Student s = new Student("Andriy", "Lviv", "103", "b@b.com", Student.FRESHMAN);
		Employee e = new Employee("Slavik", "Donetsk", "103", "c@c.com", "DonOffice", 1000, new MyDate(2013, 12, 31));
		Faculty f = new Faculty("Tolik", "Ternopil", "105", "d@d.com", "TernopilOffice", 2000, new MyDate(), 123, "director");
		Staff st = new Staff("Yuliya", "Lugansk", "106", "e@e.com", "LugCom", 3000, new MyDate(2013, 11, 11), "staFF");
		System.out.println(p);
		System.out.println(s);
		System.out.println(e);
		System.out.println(f);
		System.out.println(st);
	}

}

class Person {
	private String name;
	private String address;
	private String phoneNumber;
	private String emailAddress;
	
	public Person(String name, String address, String phoneNumber, String emailAddress) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "Person " + name;
	}
}

class Student extends Person {
	private int classStatus;
	static final int FRESHMAN = 0;
	static final int SOPHOMORE = 1;
	static final int JUNIOR = 2;
	static final int SENIOR = 3;
	
	public int getClassStatus() {
		return classStatus;
	}
	public void setClassStatus(int classStatus) {
		this.classStatus = classStatus;
	}
	public Student(String name, String address, String phoneNumber,	String emailAddress, int classStatus) {
		super(name, address, phoneNumber, emailAddress);
		this.classStatus = classStatus;
	}
	
	@Override
	public String toString() {
		return "Student " + super.getName();
	}
		
}

class Employee extends Person {
	private String office;
	private double salary;
	private MyDate hired;
	
	public Employee(String name, String address, String phoneNumber, String emailAddress,
			String office, double salary, MyDate hired) {
		super(name, address, phoneNumber, emailAddress);
		this.office = office;
		this.salary = salary;
		this.hired = hired;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public MyDate getHired() {
		return hired;
	}
	public void setHired(MyDate hired) {
		this.hired = hired;
	}
	
	@Override
	public String toString() {
		return "Employee " + super.getName();
	}
	
}

class Faculty extends Employee {
	int officeHours;
	String rank;	
	
	public Faculty(String name, String address, String phoneNumber,
			String emailAddress, String office, double salary, MyDate hired, int officeHours, String rank) {
		super(name, address, phoneNumber, emailAddress, office, salary, hired);
		this.officeHours = officeHours;
		this.rank = rank;
	}
	public int getOfficeHours() {
		return officeHours;
	}
	public void setOfficeHours(int officeHours) {
		this.officeHours = officeHours;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Faculty " + super.getName();
	}
	
}

class Staff extends Employee {
	String title;	
	
	public Staff(String name, String address, String phoneNumber,
			String emailAddress, String office, double salary, MyDate hired, String title) {
		super(name, address, phoneNumber, emailAddress, office, salary, hired);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Staff " + super.getName();
	}
	
}

class MyDate {
	private int year;
	private int month;
	private int day;
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public MyDate(long elapsedTime) {
		setDate(elapsedTime);
	}
	
	public MyDate() {
		this(System.currentTimeMillis());
	}
	
	public MyDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	
	public void setDate(long elapsedTime) {
		long totalSeconds = elapsedTime / 1000;
		long totalMinutes = totalSeconds / 60;
		long totalHours = totalMinutes / 60;
		long totalDays = totalHours / 24;
		totalDays++;
		
		int year = 1970;
		while (totalDays > daysInYear(year)) {
			totalDays -= daysInYear(year++);
		}
		
		int month = 1;
		while (totalDays > getNumberOfDaysInMonth(year, month)) {
			totalDays -= getNumberOfDaysInMonth(year, month++);
		}
		this.year = year;
		this.month = month - 1;
		this.day = (int)(totalDays);
	}
	
	private static int getNumberOfDaysInMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12)
			return 31;

		if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;

		if (month == 2)
			return isLeapYear(year) ? 29 : 28;

		return 0;
	}

	private static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}
	
	private static int daysInYear(int year) {
		return isLeapYear(year) ? 366: 365;
	}
}