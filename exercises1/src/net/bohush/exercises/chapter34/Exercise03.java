package net.bohush.exercises.chapter34;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise03 extends JApplet {
	private static final long serialVersionUID = 1L;
	private DBConnectionPanel connectionPanel = new DBConnectionPanel("jdbc:mysql://localhost/javabook", "scott", "tiger");
	
	public void init() {
		add(connectionPanel);
	}
	
	class DBConnectionPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JComboBox<String> jComboBox = new JComboBox<>(new String[] {"sun.jdbc.odbc.JdbcOdbcDriver", "com.mysql.jdbc.Driver", "oracle.jdbc.driver.OracleDriver"});
		private JTextField jTextField1 = new JTextField(20);
		private JTextField jTextField2 = new JTextField(20);
		private JTextField jTextField3 = new JTextField(20);
		private Connection connection;
		
		public DBConnectionPanel() {
			this("", "", "");
		}
		
		public DBConnectionPanel(String db, String user, String pass) {
			jTextField1.setText(db);
			jTextField2.setText(user);
			jTextField3.setText(pass);
			JPanel jPanel1 = new JPanel(new BorderLayout(5, 5));
			jPanel1.setBorder(new TitledBorder("Enter database information"));

			JPanel jPanel2 = new JPanel(new GridLayout(4, 1, 5, 5));
			jPanel2.add(new JLabel("JDBC Driver"));
			jPanel2.add(new JLabel("Database URL"));
			jPanel2.add(new JLabel("Username"));
			jPanel2.add(new JLabel("Password"));
			jPanel1.add(jPanel2, BorderLayout.WEST);

			JPanel jPanel3 = new JPanel(new GridLayout(4, 1, 5, 5));
			jPanel3.add(jComboBox);
			jPanel3.add(jTextField1);
			jPanel3.add(jTextField2);
			jPanel3.add(jTextField3);
			jPanel1.add(jPanel3, BorderLayout.CENTER);

			JPanel jPanel4 = new JPanel(new BorderLayout(5, 5));
			jPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));
			jPanel4.add(new JLabel("No connection"), BorderLayout.WEST);
			JButton jButton = new JButton("Connect to DB");
			jPanel4.add(jButton, BorderLayout.EAST);
			setLayout(new BorderLayout());
			add(jPanel1, BorderLayout.CENTER);
			add(jPanel4, BorderLayout.SOUTH);

			jButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						Class.forName(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
						connection = DriverManager.getConnection(jTextField1.getText(), jTextField2.getText(), jTextField3.getText());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
		}
		
		public Connection getConnection() {
			return connection;
		}
		
	}
	
	/** Main method */
	public static void main(String[] args) {
		Exercise03 applet = new Exercise03();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise03");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}