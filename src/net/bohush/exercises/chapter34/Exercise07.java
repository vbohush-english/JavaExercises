package net.bohush.exercises.chapter34;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, SQLException {
		Scanner input = new Scanner(new URL("http://www.cs.armstrong.edu/liang/data/Quiz.txt").openStream());
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
		String queryString = "insert into Quiz (questionId, question, choicea, choiceb, choicec, choiced, answer) values (?, ?, ?, ?, ?, ?, ?);";
		
		while(input.hasNextLine()) {
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
			String nextLine = input.nextLine();
			if(nextLine.equals("")) {
				continue;
			}
			preparedStatement.setString(1, nextLine.substring(0, nextLine.indexOf(".")));
			preparedStatement.setString(2, nextLine.substring(nextLine.indexOf(".") + 2));
			
			do {
				nextLine = input.nextLine();				
			} while((nextLine.length() < 2)||(!nextLine.substring(0, 2).equals("a.")));
			
			preparedStatement.setString(3, nextLine.substring(3));
			preparedStatement.setString(4, input.nextLine().substring(3));
			preparedStatement.setString(5, input.nextLine().substring(3));
			preparedStatement.setString(6, input.nextLine().substring(3));
			preparedStatement.setString(7, input.nextLine().substring(7));
			preparedStatement.executeUpdate();
		}
		input.close();
	}

}
