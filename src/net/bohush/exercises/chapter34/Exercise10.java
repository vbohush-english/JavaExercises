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
		
		ArrayList<AGSStudent> students = new ArrayList<>();
		ResultSet rset = statement.executeQuery("select * from AGSStudent");
		while(rset.next()) {
			students.add(new AGSStudent(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4)));
		}
		
		
		ArrayList<ExerciseAssigned> exercises = new ArrayList<>();
		rset = statement.executeQuery("select * from ExerciseAssigned");
		while(rset.next()) {
			exercises.add(new ExerciseAssigned(rset.getString(1), rset.getString(2), rset.getString(3)));
		}
		
		
		ArrayList<AGSLog> logs = new ArrayList<>();
		rset = statement.executeQuery("select * from AGSLog");
		while(rset.next()) {
			logs.add(new AGSLog(rset.getString(1), rset.getString(2)));
		}
		
		
		ArrayList<AGSLog> newLogs = new ArrayList<>();
		for (AGSStudent agsStudent : students) {
			for (ExerciseAssigned exerciseAssigned : exercises) {
				if(agsStudent.instructorEmail.equals(exerciseAssigned.instructorEmail)) {
					AGSLog agsLog = new AGSLog(agsStudent.username, exerciseAssigned.exerciseName);
					if(!logs.contains(agsLog)) {
						newLogs.add(agsLog);
					}					
				}
			}
		}
		

		for (AGSLog agsLog : newLogs) {
			String queryString = "insert into AGSLog (username, exerciseName) values (?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, agsLog.username);
			preparedStatement.setString(2, agsLog.exerciseName);
			preparedStatement.executeUpdate();			
		}
	}
	
	static class AGSStudent {
		String username;
		String password;
		String fullname;
		String instructorEmail;
		
		public AGSStudent(String username, String password, String fullname, String instructorEmail) {
			this.username = username;
			this.password = password;
			this.fullname = fullname;
			this.instructorEmail = instructorEmail;
		}

		@Override
		public String toString() {
			return "AGSStudent [username=" + username + ", password=" + password + ", fullname=" + fullname	+ ", instructorEmail=" + instructorEmail + "]";
		}		
	}
	
	static class ExerciseAssigned {
		String instructorEmail;
		String exerciseName;
		String maxScore;
		
		public ExerciseAssigned(String instructorEmail, String exerciseName, String maxScore) {
			this.instructorEmail = instructorEmail;
			this.exerciseName = exerciseName;
			this.maxScore = maxScore;
		}

		@Override
		public String toString() {
			return "ExerciseAssigned [instructorEmail=" + instructorEmail + ", exerciseName=" + exerciseName + ", maxScore=" + maxScore + "]";
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
		public String toString() {
			return "AGSLog [username=" + username + ", exerciseName=" + exerciseName + "]";
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
