package net.bohush.exercises.chapter41;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Exercise08 {

	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
		
		Scanner input = new Scanner(new BufferedInputStream(new URL("http://cs.armstrong.edu/liang/data/Salary.txt").openStream()));
		long time = System.currentTimeMillis();
		Statement statement = connection.createStatement();
		while(input.hasNextLine()) {
			statement.execute("insert into Salary (firstName, lastName, rank, salary) values (\'" + input.next() + "\', \'" + input.next() + "\', \'" + input.next() + "\', \'" + input.next() + "\');");
		}
		System.out.println("Non-batch mode: " + (System.currentTimeMillis() - time));		
		statement.execute("delete from Salary;");		
		input.close();
		
		
		input = new Scanner(new BufferedInputStream(new URL("http://cs.armstrong.edu/liang/data/Salary.txt").openStream()));
		time = System.currentTimeMillis();
		Statement statement2 = connection.createStatement();
		while(input.hasNextLine()) {
			statement2.addBatch("insert into Salary (firstName, lastName, rank, salary) values (\'" + input.next() + "\', \'" + input.next() + "\', \'" + input.next() + "\', \'" + input.next() + "\');");
		}
		statement2.executeBatch();
		System.out.println("Batch mode: " + (System.currentTimeMillis() - time));
		
		statement2.execute("delete from Salary;");		
		input.close();
	}

}
