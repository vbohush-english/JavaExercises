package net.bohush.exercises.chapter34;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise01 extends JApplet {
	private static final long serialVersionUID = 1L;
	private	JTextField jTextField1 = new JTextField(10);
	private	JTextField jTextField2 = new JTextField(10);
	private	JTextField jTextField3 = new JTextField(10);
	private	JTextField jTextField4 = new JTextField(3);
	private	JTextField jTextField5 = new JTextField(10);
	private	JTextField jTextField6 = new JTextField(10);
	private	JTextField jTextField7 = new JTextField(3);
	private	JTextField jTextField8 = new JTextField(10);
	private	JTextField jTextField9 = new JTextField(10);
	
	private Connection connection;

	public void init() {
		setLayout(new BorderLayout());
		initializeDB();

		JPanel jPanel1 = new JPanel(new FlowLayout());
		jPanel1.setBorder(new TitledBorder("Staff information"));
		jPanel1.add(new JLabel("ID"));
		jPanel1.add(jTextField1);
		jPanel1.add(new JLabel("Last Name"));
		jPanel1.add(jTextField2);
		jPanel1.add(new JLabel("First Name"));
		jPanel1.add(jTextField3);
		jPanel1.add(new JLabel("mi"));
		jPanel1.add(jTextField4);
		jPanel1.add(new JLabel("Address"));
		jPanel1.add(jTextField5);
		jPanel1.add(new JLabel("City"));
		jPanel1.add(jTextField6);
		jPanel1.add(new JLabel("State"));
		jPanel1.add(jTextField7);
		jPanel1.add(new JLabel("Telephone"));
		jPanel1.add(jTextField8);
		jPanel1.add(new JLabel("E-mail"));
		jPanel1.add(jTextField9);
		add(jPanel1, BorderLayout.CENTER);
		
		JPanel jPanel2 = new JPanel(new GridLayout(1, 4, 5, 5));
		jPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		JButton jButton1 = new JButton("View");
		jPanel2.add(jButton1);
		JButton jButton2 = new JButton("Insert");
		jPanel2.add(jButton2);
		JButton jButton3 = new JButton("Update");
		jPanel2.add(jButton3);
		JButton jButton4 = new JButton("Clear");
		jPanel2.add(jButton4);
		add(jPanel2, BorderLayout.SOUTH);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent aerg0) {
				String queryString = "select lastName, firstName, mi, address, city, state, telephone, email from Staff where id = ?;";
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(queryString);
					preparedStatement.setString(1, jTextField1.getText());
					ResultSet rset = preparedStatement.executeQuery();
					if (rset.next()) {
						jTextField2.setText(rset.getString(1));
						jTextField3.setText(rset.getString(2));
						jTextField4.setText(rset.getString(3));
						jTextField5.setText(rset.getString(4));
						jTextField6.setText(rset.getString(5));
						jTextField7.setText(rset.getString(6));
						jTextField8.setText(rset.getString(7));
						jTextField9.setText(rset.getString(8));
					} else {
						jTextField2.setText("");
						jTextField3.setText("");
						jTextField4.setText("");
						jTextField5.setText("");
						jTextField6.setText("");
						jTextField7.setText("");
						jTextField8.setText("");
						jTextField9.setText("");
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String queryString = "insert into Staff (id, lastName, firstName, mi, address, city, state, telephone, email) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(queryString);
					preparedStatement.setString(1, jTextField1.getText());
					preparedStatement.setString(2, jTextField2.getText());
					preparedStatement.setString(3, jTextField3.getText());
					preparedStatement.setString(4, jTextField4.getText());
					preparedStatement.setString(5, jTextField5.getText());
					preparedStatement.setString(6, jTextField6.getText());
					preparedStatement.setString(7, jTextField7.getText());
					preparedStatement.setString(8, jTextField8.getText());
					preparedStatement.setString(9, jTextField9.getText());
					preparedStatement.executeUpdate();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String queryString = "update Staff set lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? where id = ?";
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(queryString);
					preparedStatement.setString(1, jTextField2.getText());
					preparedStatement.setString(2, jTextField3.getText());
					preparedStatement.setString(3, jTextField4.getText());
					preparedStatement.setString(4, jTextField5.getText());
					preparedStatement.setString(5, jTextField6.getText());
					preparedStatement.setString(6, jTextField7.getText());
					preparedStatement.setString(7, jTextField8.getText());
					preparedStatement.setString(8, jTextField9.getText());
					preparedStatement.setString(9, jTextField1.getText());
					preparedStatement.executeUpdate();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		jButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Statement statement = connection.createStatement();
					statement.executeUpdate("delete from Staff;");
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
					jTextField4.setText("");
					jTextField5.setText("");
					jTextField6.setText("");
					jTextField7.setText("");
					jTextField8.setText("");
					jTextField9.setText("");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
	}

	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise01 applet = new Exercise01();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise01");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(540, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}