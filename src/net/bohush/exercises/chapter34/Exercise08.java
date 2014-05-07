package net.bohush.exercises.chapter34;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Exercise08 {

	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, SQLException {
		Scanner input = new Scanner(new BufferedInputStream(new URL("http://cs.armstrong.edu/liang/data/Salary.txt").openStream()));
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
		String queryString = "insert into Salary (firstName, lastName, rank, salary) values (?, ?, ?, ?);";
		
		while(input.hasNextLine()) {
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, input.next());
			preparedStatement.setString(2, input.next());
			preparedStatement.setString(3, input.next());
			preparedStatement.setString(4, input.next());
			preparedStatement.executeUpdate();
		}
		input.close();
	}

}
