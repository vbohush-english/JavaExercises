package net.bohush.exercises.chapter41;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.sql.*;

public class Exercise03 extends JApplet {

	private static final long serialVersionUID = 1L;

	private TableEditor tableEditor1 = new TableEditor();

	/** Creates new form TestTableEditor */
	public Exercise03() {

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = statement.executeQuery("select * from course");
			tableEditor1.setResultSet(resultSet);
		} catch (Exception ex) {
		}

		add(tableEditor1, BorderLayout.CENTER);


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
				return resultSet.getRow(); // Get the current row number
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

	
	public class TableEditor extends JPanel {
		private static final long serialVersionUID = 1L;

		// Table model, table selection model, table, rowset
		private ResultSetTableModel tableModel = new ResultSetTableModel();
		private DefaultListSelectionModel listSelectionModel = new DefaultListSelectionModel();
		private JTable jTable1 = new JTable();
		private ResultSet resultSet;

		/** Set a new row set */
		public void setResultSet(ResultSet resultSet) {
			this.resultSet = resultSet;
			tableModel.setResultSet(resultSet);
			jTable1.setModel(tableModel);

			// Enable auto sort on columns
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
			jTable1.setRowSorter(sorter);
		}

		/** Create a TableEditor */
		public TableEditor() {

			setLayout(new BorderLayout());
			add(new JScrollPane(jTable1), BorderLayout.CENTER);

			// Set selection model for the table
			jTable1.setSelectionModel(listSelectionModel);

			listSelectionModel
					.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							handleSelectionValueChanged(e);
						}
					});
		}

		/** Set cursor in the table and set the row number in the status */
		private void setTableCursor() throws java.sql.SQLException {
			int row = resultSet.getRow();
			listSelectionModel.setSelectionInterval(row - 1, row - 1);
		}

		/** Handle the selection in the table */
		private void handleSelectionValueChanged(ListSelectionEvent e) {
			int selectedRow = jTable1.getSelectedRow();

			try {
				if (selectedRow != -1) {
					resultSet.absolute(selectedRow + 1);
					setTableCursor();
				}
			} catch (java.sql.SQLException ex) {
			}
		}
	}
}