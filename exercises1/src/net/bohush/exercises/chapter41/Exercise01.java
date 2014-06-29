package net.bohush.exercises.chapter41;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise01 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JLabel jLabel1 = new JLabel("No connection");
	private JButton jButton2 = new JButton("Batch Update");
	private JButton jButton3 = new JButton("Non-Batch Update");
	private DBConnectionDialog connectionDialog = new DBConnectionDialog();
	private Connection connection = null;
	private JTextArea jTextArea1 = new JTextArea(10, 20);
	private int queryCount = 3000;
	
	public void init() {
		JPanel jPanel1 = new JPanel(new GridLayout(1, 2, 5, 5));
		jPanel1.add(jLabel1);
		JButton jButton1 = new JButton("Connect to Database");
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				connectionDialog.setVisible(true);
				if(connectionDialog.getConnection() != null) {
					jButton2.setEnabled(true);
					jButton3.setEnabled(true);
					jLabel1.setText("Connected to Database");
					connection = connectionDialog.getConnection();
				}
			}
		});
		jPanel1.add(jButton1);

		setLayout(new BorderLayout(5, 5));

		JPanel jPanel2 = new JPanel(new GridLayout(1, 2, 5, 5));
		jButton2.setEnabled(false);
		jButton3.setEnabled(false);
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					long time = System.currentTimeMillis();
					Statement statement = connection.createStatement();
					for (int i = 0; i < queryCount; i++) {
						statement.addBatch("insert into Temp values (" + Math.random() + ", " + Math.random() + ", " + Math.random() + ")");
					}
					statement.executeBatch();
					time = System.currentTimeMillis() - time;
					jTextArea1.append("Batch update completed\n");
					jTextArea1.append("The elapsed time is " + time + "\n");
					statement.execute("delete from temp;");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					long time = System.currentTimeMillis();
					Statement statement = connection.createStatement();
					for (int i = 0; i < queryCount; i++) {
						statement.execute("insert into Temp values (" + Math.random() + ", " + Math.random() + ", " + Math.random() + ");");
					}
					time = System.currentTimeMillis() - time;
					jTextArea1.append("Non-Batch update completed\n");
					jTextArea1.append("The elapsed time is " + time + "\n");
					statement.execute("delete from temp;");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jPanel2.add(jButton2);
		jPanel2.add(jButton3);
		
		JPanel jPanel3 = new JPanel(new BorderLayout(5, 5));
		jPanel3.add(jPanel1, BorderLayout.NORTH);
		jPanel3.add(new JScrollPane(jTextArea1), BorderLayout.CENTER);
		jPanel3.add(jPanel2, BorderLayout.SOUTH);
		jPanel3.setBorder(new EmptyBorder(5, 5, 5, 5));

		add(jPanel3, BorderLayout.CENTER);
	}

	public class DBConnectionDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		private DBConnectionPanel connectionPanel = new DBConnectionPanel("jdbc:mysql://localhost/javabook", "scott", "tiger");
		
		public DBConnectionDialog() {
			this(null, true);
		}

		public DBConnectionDialog(java.awt.Frame parent, boolean modal) {
			super(parent, modal);
			setTitle("Connect to Database");
			setLayout(new BorderLayout());
			add(connectionPanel, BorderLayout.CENTER);
			setSize(400, 220);
			setLocationRelativeTo(null);
			JButton jButton1 = new JButton("Close Dialog");
			add(jButton1, BorderLayout.SOUTH);
			jButton1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DBConnectionDialog.this.setVisible(false);
				}
			});
		}
		
		public Connection getConnection() {
			return connectionPanel.getConnection();
		}
	}

	class DBConnectionPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JComboBox<String> jComboBox = new JComboBox<>(new String[] {"sun.jdbc.odbc.JdbcOdbcDriver", "com.mysql.jdbc.Driver", "oracle.jdbc.driver.OracleDriver"});
		private JTextField jTextField1 = new JTextField(20);
		private JTextField jTextField2 = new JTextField(20);
		private JPasswordField jPasswordField = new JPasswordField(20);
		private Connection connection;
		private JLabel jLabel = new JLabel("No connection");

		public DBConnectionPanel() {
			this("", "", "");
		}

		public DBConnectionPanel(String db, String user, String pass) {
			jTextField1.setText(db);
			jTextField2.setText(user);
			jPasswordField.setText(pass);
			JPanel jPanel1 = new JPanel(new BorderLayout(5, 5));
			jPanel1.setBorder(new TitledBorder("Enter database information"));

			JPanel jPanel2 = new JPanel(new GridLayout(4, 1, 5, 5));
			jPanel2.add(new JLabel("JDBC Driver"));
			jPanel2.add(new JLabel("Database URL"));
			jPanel2.add(new JLabel("Username"));
			jPanel2.add(new JLabel("Password"));
			jPanel1.add(jPanel2, BorderLayout.WEST);

			JPanel jPanel3 = new JPanel(new GridLayout(4, 1, 5, 5));
			jComboBox.setSelectedIndex(1);
			jPanel3.add(jComboBox);
			jPanel3.add(jTextField1);
			jPanel3.add(jTextField2);
			jPanel3.add(jPasswordField);
			jPanel1.add(jPanel3, BorderLayout.CENTER);

			JPanel jPanel4 = new JPanel(new BorderLayout(5, 5));
			jPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));
			jPanel4.add(jLabel, BorderLayout.WEST);
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
						connection = DriverManager.getConnection(jTextField1.getText(), jTextField2.getText(), new String(jPasswordField.getPassword()));
						jLabel.setText("Connected to " + jTextField1.getText());
					} catch (Exception ex) {
						jLabel.setText("No connection");
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
		Exercise01 applet = new Exercise01();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise03");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}