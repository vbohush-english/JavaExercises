package net.bohush.exercises.chapter34;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
		Statement statement = connection.createStatement();
		ResultSet rset = statement.executeQuery("select * from student1");
		
		String queryString = "insert into Student2 (username, password, firstname, lastname) values (?, ?, ?, ?);";
		while(rset.next()) {
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, rset.getString(1));
			preparedStatement.setString(2, rset.getString(2));
			
			Scanner name = new Scanner(rset.getString(3));
			String name1 = name.next();
			String name2 = name.next();
			if(name.hasNext()) {
				preparedStatement.setString(3, name1 + " " + name2);
				preparedStatement.setString(4, name.next());				
			} else {
				preparedStatement.setString(3, name1);
				preparedStatement.setString(4, name2);				
			}
			name.close();
			
			preparedStatement.executeUpdate();
		}
		
	}

}
