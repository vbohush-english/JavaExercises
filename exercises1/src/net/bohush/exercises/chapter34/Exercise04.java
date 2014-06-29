package net.bohush.exercises.chapter34;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise04 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JTextField jtfSSN = new JTextField(9);
	private JButton jbtShowGrade = new JButton("Show Grade");
	private JTextArea jTextArea = new JTextArea();
	
	// PreparedStatement for executing queries
	private PreparedStatement preparedStatement;

	/** Initialize the applet */
	public void init() {
		// Initialize database connection and create a Statement object
		initializeDB();

		jbtShowGrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jbtShowGrade_actionPerformed(e);
			}
		});

		JPanel jPanel1 = new JPanel();
		jPanel1.add(new JLabel("SSN"));
		jPanel1.add(jtfSSN);
		jPanel1.add(jbtShowGrade);

		add(jPanel1, BorderLayout.NORTH);
		jTextArea.setEditable(false);
		add(jTextArea, BorderLayout.CENTER);
		
		
	}

	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
			System.out.println("Database connected");
			String queryString = "select firstName, mi, lastName, title, grade from Student, Enrollment, Course where Student.ssn = ? and Enrollment.ssn = Student.ssn and Enrollment.courseId = Course.courseId";
			preparedStatement = connection.prepareStatement(queryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbtShowGrade_actionPerformed(ActionEvent e) {
		String ssn = jtfSSN.getText();
		try {
			preparedStatement.setString(1, ssn);
			ResultSet rset = preparedStatement.executeQuery();

			jTextArea.setText("");
			while (rset.next()) {
				String lastName = rset.getString(1);
				String mi = rset.getString(2);
				String firstName = rset.getString(3);
				String title = rset.getString(4);
				String grade = rset.getString(5);

				jTextArea.append(firstName + " " + mi + " "	+ lastName + "'s grade on course " + title + " is "	+ grade + "\n");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise04 applet = new Exercise04();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise04");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}
}