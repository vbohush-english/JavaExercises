package net.bohush.exercises.chapter34;

import javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise05 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JTextField jtfName = new JTextField(9);
	private JButton jbtShowTable = new JButton("Show Contents");
	private JTextArea jTextArea = new JTextArea();
	
	private Statement statement;

	/** Initialize the applet */
	public void init() {
		initializeDB();

		jbtShowTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jbtShowGrade_actionPerformed(e);
			}
		});

		JPanel jPanel1 = new JPanel();
		jPanel1.add(new JLabel("Table Name"));
		jPanel1.add(jtfName);
		jPanel1.add(jbtShowTable);

		add(jPanel1, BorderLayout.NORTH);
		jTextArea.setEditable(false);
		jTextArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		add(jScrollPane, BorderLayout.CENTER);
		
		
	}

	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
			statement = connection.createStatement();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbtShowGrade_actionPerformed(ActionEvent e) {
		try {
			ResultSet rset = statement.executeQuery("select * from " + jtfName.getText() + ";");
			jTextArea.setText("");
			
			ResultSetMetaData rsMetaData = rset.getMetaData();
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
				jTextArea.append(String.format("%-25s", rsMetaData.getColumnName(i)));
			}
			jTextArea.append("\n");
			    
			while (rset.next()) {
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					jTextArea.append(String.format("%-25s",rset.getString(i)));
				}
				jTextArea.append("\n");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise05 applet = new Exercise05();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise05");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}
}