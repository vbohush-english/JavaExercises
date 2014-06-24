package net.bohush.exercises.chapter41;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise02 extends JApplet {
	private static final long serialVersionUID = 1L;
	private	JTextField jTextField2 = new JTextField(10);
	private	JTextField jTextField3 = new JTextField(10);
	private	JTextField jTextField4 = new JTextField(3);
	private	JTextField jTextField5 = new JTextField(10);
	private	JTextField jTextField6 = new JTextField(10);
	private	JTextField jTextField7 = new JTextField(3);
	private	JTextField jTextField8 = new JTextField(10);
	private	JTextField jTextField9 = new JTextField(10);
	private JLabel jLabel1 = new JLabel(" ");
	
	private Connection connection;
	private ResultSet resultSet;
	
	public void init() {
		setLayout(new BorderLayout());
		initializeDB();
		JPanel jPanel1 = new JPanel(new GridLayout(5, 1, 5, 5));
		jPanel1.setBorder(new TitledBorder("Staff information"));
		JPanel jPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		jPanel4.add(new JLabel("Last Name"));
		jPanel4.add(jTextField2);
		jPanel4.add(new JLabel("First Name"));
		jPanel4.add(jTextField3);
		jPanel4.add(new JLabel("mi"));
		jPanel4.add(jTextField4);
		JPanel jPanel5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		jPanel5.add(new JLabel("Address"));
		jPanel5.add(jTextField5);
		JPanel jPanel6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		jPanel6.add(new JLabel("City"));
		jPanel6.add(jTextField6);
		jPanel6.add(new JLabel("State"));
		jPanel6.add(jTextField7);
		JPanel jPanel7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		jPanel7.add(new JLabel("Telephone"));
		jPanel7.add(jTextField8);
		jPanel7.add(new JLabel("E-mail"));
		jPanel7.add(jTextField9);
		jPanel1.add(jPanel4);
		jPanel1.add(jPanel5);
		jPanel1.add(jPanel6);
		jPanel1.add(jPanel7);
		jPanel1.add(jLabel1);
		add(jPanel1, BorderLayout.CENTER);
		
		JPanel jPanel2 = new JPanel(new GridLayout(1, 4, 5, 5));
		jPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		JButton jButton1 = new JButton("First");
		JButton jButton2 = new JButton("Next");
		JButton jButton3 = new JButton("Prior");
		JButton jButton4 = new JButton("Last");
		JButton jButton5 = new JButton("Insert");
		JButton jButton6 = new JButton("Delete");
		JButton jButton7 = new JButton("Update");
		jPanel2.add(jButton1);		
		jPanel2.add(jButton2);		
		jPanel2.add(jButton3);		
		jPanel2.add(jButton4);
		jPanel2.add(jButton5);
		jPanel2.add(jButton6);
		jPanel2.add(jButton7);
		add(jPanel2, BorderLayout.NORTH);
		
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent aerg0) {
				try {
					if(!resultSet.isFirst()) {
						if(resultSet.first()) {
							showRow();	
						}
					}	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent aerg0) {
				try {
					if(!resultSet.isLast()) {
						if(resultSet.next()) {
							showRow();	
						}
					}				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent aerg0) {
				try {
					if(!resultSet.isFirst()) {
						if(resultSet.previous()) {
							showRow();
						}
					}					
				} catch (SQLException e) {
				}
			}
		});
		
		jButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent aerg0) {
				try {
					if(!resultSet.isLast()) {
						if(resultSet.last()) {
							showRow();	
						}	
					}						
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		jButton5.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					resultSet.last();
					resultSet.moveToInsertRow();
					resultSet.updateString("id", (resultSet.getRow() + 1) + "");
					resultSet.updateString("lastName", jTextField2.getText());
					resultSet.updateString("firstName", jTextField3.getText());
					resultSet.updateString("mi", jTextField4.getText());
					resultSet.updateString("address", jTextField5.getText());
					resultSet.updateString("city", jTextField6.getText());
					resultSet.updateString("state", jTextField7.getText());
					resultSet.updateString("telephone", jTextField8.getText());
					resultSet.updateString("email", jTextField9.getText());
					resultSet.insertRow();
					resultSet.moveToCurrentRow();
					if(resultSet.last()) {
						showRow();
					}	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		jButton6.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(resultSet.getRow()!=0) {
						resultSet.deleteRow();
						if(!resultSet.isFirst()) {
							if(resultSet.previous()) {
								showRow();
							}
						}	
					}					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		jButton7.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(resultSet.getRow()!=0) {
						resultSet.updateString("lastName", jTextField2.getText());
						resultSet.updateString("firstName", jTextField3.getText());
						resultSet.updateString("mi", jTextField4.getText());
						resultSet.updateString("address", jTextField5.getText());
						resultSet.updateString("city", jTextField6.getText());
						resultSet.updateString("state", jTextField7.getText());
						resultSet.updateString("telephone", jTextField8.getText());
						resultSet.updateString("email", jTextField9.getText());
						resultSet.updateRow();						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		
		/*
		jButton5.addActionListener(new ActionListener() {			
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
		
		jButton7.addActionListener(new ActionListener() {			
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
		
		jButton6.addActionListener(new ActionListener() {			
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
		});*/
	}

	private void showRow() throws SQLException {
		jTextField2.setText(resultSet.getString(2));
		jTextField3.setText(resultSet.getString(3));
		jTextField4.setText(resultSet.getString(4));
		jTextField5.setText(resultSet.getString(5));
		jTextField6.setText(resultSet.getString(6));
		jTextField7.setText(resultSet.getString(7));
		jTextField8.setText(resultSet.getString(8));
		jTextField9.setText(resultSet.getString(9));
		jLabel1.setText("Current row number: " + resultSet.getString(1));
	}
	
	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery("select id, lastName, firstName, mi, address, city, state, telephone, email from Staff");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise02 applet = new Exercise02();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise02");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(700, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}