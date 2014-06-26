package net.bohush.exercises.chapter41;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.sql.*;

public class Exercise03 extends JApplet {

	private static final long serialVersionUID = 1L;

	private JTable jTable1 = new JTable();

	/** Creates new form TestTableEditor */
	public Exercise03() {

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
		    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = statement.executeQuery("select * from course");

			ResultSetTableModel model = new ResultSetTableModel();
			model.setResultSet(resultSet);
			jTable1.setModel(model);
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			jTable1.setRowSorter(sorter);
			
		} catch (Exception ex) {
		}

		 add(new JScrollPane(jTable1), BorderLayout.CENTER);


	}

	/** Main method */
	public static void main(String[] args) {
		Exercise03 applet = new Exercise03();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(3);
		frame.setTitle("Exercise03");
		frame.add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public class ResultSetTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private ResultSet resultSet;
		
		public ResultSet getResultSet() {
			return resultSet;
		}

		public void setResultSet(ResultSet resultSet) {
			if(resultSet != null) {
				this.resultSet = resultSet;
			}
		}

		@Override
		public int getRowCount() {
			try {
				resultSet.last();
				return resultSet.getRow();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return 0;
		}

		@Override
		public int getColumnCount() {
			try {
				if (resultSet != null) {
					return resultSet.getMetaData().getColumnCount();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return 0;
		}

		@Override
		public String getColumnName(int column) {
			try {
				return resultSet.getMetaData().getColumnLabel(column + 1);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return super.getColumnName(column);
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			try {
				resultSet.absolute(rowIndex + 1);
				return resultSet.getObject(columnIndex + 1);
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
			return null;
		}
		
	}
}