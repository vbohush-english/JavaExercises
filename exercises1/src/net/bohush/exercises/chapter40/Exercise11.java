package net.bohush.exercises.chapter40;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;

public class Exercise11 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> jComboBox;
	private Vector<String> tableNames = new Vector<>();
	private JTable jTable = new JTable();
	
	private Statement statement;

	/** Initialize the applet */
	public void init() {
		initializeDB();
		jComboBox = new JComboBox<String>(tableNames);
		jComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jbtShowTable_actionPerformed(e);
			}
		});
		
		jTable.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		JScrollPane jScrollPane = new JScrollPane(jTable);
		setLayout(new BorderLayout(5, 5));
		JPanel jPanel1 = new JPanel(new BorderLayout(5, 5));
		jPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jPanel1.add(jComboBox, BorderLayout.NORTH);
		jPanel1.add(jScrollPane, BorderLayout.CENTER);
		add(jPanel1, BorderLayout.CENTER);
		jbtShowTable_actionPerformed(null);
		
	}

	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
			statement = connection.createStatement();
			DatabaseMetaData dbMetaData = connection.getMetaData();
		    ResultSet rsTables = dbMetaData.getTables(null, null, null, new String[] {"TABLE"});
		    while (rsTables.next()) {
		    	tableNames.add(rsTables.getString("TABLE_NAME"));
		    }
		    
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void jbtShowTable_actionPerformed(ActionEvent e) {
		try {
			ResultSet rset = statement.executeQuery("select * from " + jComboBox.getItemAt(jComboBox.getSelectedIndex()) + ";");
			
			ResultSetMetaData rsMetaData = rset.getMetaData();
			String[] columnNames = new String[rsMetaData.getColumnCount()];
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
				columnNames[i - 1] = rsMetaData.getColumnName(i);
			}
			int rowCount = 0;
			while (rset.next()) {
				rowCount++;
			}
			
			Object[][] rowData = new Object[rowCount][columnNames.length];
			rset.first();
			int currentRow = 0;
			do {
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					rowData[currentRow][i - 1] = rset.getString(i);
				}
				currentRow++;
			} while (rset.next());
			DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
			jTable.setModel(tableModel);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise11 applet = new Exercise11();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise11");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}
}