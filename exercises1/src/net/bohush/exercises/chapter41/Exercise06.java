package net.bohush.exercises.chapter41;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Exercise06 extends JApplet {
	private static final long serialVersionUID = 1L;
	private Statement statement;
	public Exercise06() {
		
		initializeDB();
		
		try {
			ResultSet rset = statement.executeQuery("select * from Country;");
			
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
				rowData[currentRow][0] = rset.getString(1);				
				Blob blob = rset.getBlob(2);
				rowData[currentRow][1] = new ImageIcon(blob.getBytes(1,(int) blob.length()));				
				rowData[currentRow][2] = rset.getString(3);
				currentRow++;
			} while (rset.next());
			
			MyTableModel tableModel = new MyTableModel(rowData, columnNames);
			JTable jTable1 = new JTable(tableModel);			
			jTable1.setRowHeight(100);
			add(new JScrollPane(jTable1), BorderLayout.CENTER);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	public class MyTableModel extends DefaultTableModel {
		private static final long serialVersionUID = 1L;

		public MyTableModel() {
		}

		/** Construct a table model with specified data and columnNames */
		public MyTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		/** Override this method to return a class for the column */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			Class<?> columnClass = getColumnClass(column);
			return columnClass != ImageIcon.class && columnClass != Date.class;
		}

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

	public static void main(String[] args) {
		Exercise06 applet = new Exercise06();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise03");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}