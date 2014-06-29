package net.bohush.exercises.chapter34;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Exercise10 {

	public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
		Statement statement = connection.createStatement();
		
		ArrayList<AGSLog> logs = new ArrayList<>();
		ResultSet  rset = statement.executeQuery("select * from AGSLog");
		while(rset.next()) {
			logs.add(new AGSLog(rset.getString(1), rset.getString(2)));
		}
		
		rset = statement.executeQuery("select AGSStudent.username, ExerciseAssigned.exerciseName  from AGSStudent, ExerciseAssigned where AGSStudent.instructorEmail = ExerciseAssigned.instructorEmail;");
		while(rset.next()) {
			AGSLog agsLog = new AGSLog(rset.getString(1), rset.getString(2));
			if(!logs.contains(agsLog)) {
				String queryString = "insert into AGSLog (username, exerciseName) values (?, ?);";
				PreparedStatement preparedStatement = connection.prepareStatement(queryString);
				preparedStatement.setString(1, agsLog.username);
				preparedStatement.setString(2, agsLog.exerciseName);
				preparedStatement.executeUpdate();	
			}	
		}		
	}
	
	static class AGSLog {
		String username;
		String exerciseName;
		
		public AGSLog(String username, String exerciseName) {
			this.username = username;
			this.exerciseName = exerciseName;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof AGSLog) {
				AGSLog new_name = (AGSLog) obj;
				if((username.equals(new_name.username)) && (exerciseName.equals(new_name.exerciseName))) {
					return true;
				} else {
					return false;	
				}
			} else {
				return false;
			}
		}
	}
}
