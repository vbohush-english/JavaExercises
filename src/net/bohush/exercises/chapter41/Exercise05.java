package net.bohush.exercises.chapter41;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.sql.*;

import com.sun.rowset.JdbcRowSetImpl;

public class Exercise05 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> jcboDriver = new JComboBox<>(new String[] {
			"com.mysql.jdbc.Driver",
			"sun.jdbc.odbc.JdbcOdbcDriver",			
			"oracle.jdbc.driver.OracleDriver" });
	private JComboBox<String> jcboURL = new JComboBox<>(new String[] {
			"jdbc:mysql://localhost/javabook",
			"jdbc:odbc:exampleMDBDataSource",
			"jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl" });

	private JButton jbtConnect = new JButton("Connect to DB & Get Table");
	private JTextField jtfUserName = new JTextField("scott");
	private JPasswordField jpfPassword = new JPasswordField("tiger");
	private JTextField jtfTableName = new JTextField("student");
	private TableEditor tableEditor1 = new TableEditor();
	private JLabel jlblStatus = new JLabel();

	/** Creates new form TestTableEditor */
	public Exercise05() {
		JPanel jPanel1 = new JPanel(new GridLayout(5, 0));
		jPanel1.add(jcboDriver);
		jPanel1.add(jcboURL);
		jPanel1.add(jtfUserName);
		jPanel1.add(jpfPassword);
		jPanel1.add(jtfTableName);

		JPanel jPanel2 = new JPanel(new GridLayout(5, 0));
		jPanel2.add(new JLabel("JDBC Driver"));
		jPanel2.add(new JLabel("Database URL"));
		jPanel2.add(new JLabel("Username"));
		jPanel2.add(new JLabel("Password"));
		jPanel2.add(new JLabel("Table Name"));

		JPanel jPanel3 = new JPanel(new BorderLayout());
		jPanel3.add(jbtConnect, BorderLayout.SOUTH);
		jPanel3.add(jPanel2, BorderLayout.WEST);
		jPanel3.add(jPanel1, BorderLayout.CENTER);
		tableEditor1.setPreferredSize(new Dimension(400, 200));

		jcboURL.setEditable(true);
		jcboDriver.setEditable(true);

		add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPanel3, tableEditor1),	BorderLayout.CENTER);
		add(jlblStatus, BorderLayout.SOUTH);

		jbtConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					// Connect to the database and create a rowset
					Class.forName(((String) jcboDriver.getSelectedItem()).trim());
					RowSet rowSet = new JdbcRowSetImpl();
					rowSet.setUrl(((String) jcboURL.getSelectedItem()).trim());
					rowSet.setUsername(jtfUserName.getText().trim());
					rowSet.setPassword(new String(jpfPassword.getPassword()));
					rowSet.setCommand("select * from " + jtfTableName.getText().trim());
					rowSet.execute();
					rowSet.beforeFirst();
					tableEditor1.setRowSet(rowSet);
					
					/*
					Class.forName(((String) jcboDriver.getSelectedItem()).trim());
					Connection connection = DriverManager.getConnection(((String) jcboURL.getSelectedItem()).trim(), jtfUserName.getText().trim(), new String(jpfPassword.getPassword()));
					Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					resultSet = statement.executeQuery("select * from " + jtfTableName.getText().trim());
					*/
				} catch (Exception ex) {
					jlblStatus.setText(ex.toString());
				}
			}
		});
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise05 applet = new Exercise05();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(3);
		frame.setTitle("Exercise05");
		frame.add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(1200, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public class RowSetTableModel extends AbstractTableModel implements
			RowSetListener {
		private static final long serialVersionUID = 1L;
		// RowSet for the result set
		private RowSet rowSet;

		/** Return the rowset */
		public RowSet getRowSet() {
			return rowSet;
		}

		/** Set a new rowset */
		public void setRowSet(RowSet rowSet) {
			if (rowSet != null) {
				this.rowSet = rowSet;
				rowSet.addRowSetListener(this);
				fireTableStructureChanged();
			}
		}

		/** Return the number of rows in the row set */
		public int getRowCount() {
			try {
				rowSet.last();
				return rowSet.getRow(); // Get the current row number
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return 0;
		}

		/** Return the number of columns in the row set */
		public int getColumnCount() {
			try {
				if (rowSet != null) {
					return rowSet.getMetaData().getColumnCount();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			return 0;
		}

		/** Return value at the specified row and column */
		public Object getValueAt(int row, int column) {
			try {
				rowSet.absolute(row + 1);
				return rowSet.getObject(column + 1);
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

			return null;
		}
		
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			tableEditor1.updateRow(aValue, rowIndex, columnIndex);
		}

		/** Return the column name at a specified column */
		public String getColumnName(int column) {
			try {
				return rowSet.getMetaData().getColumnLabel(column + 1);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			return "";
		}

		/** Implement rowSetChanged */
		public void rowSetChanged(RowSetEvent e) {
			fireTableStructureChanged();
		}

		/** Implement rowChanged */
		public void rowChanged(RowSetEvent e) {
			fireTableDataChanged();
		}

		/** Implement cursorMoved */
		public void cursorMoved(RowSetEvent e) {
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}
		
	}

	public class TableEditor extends JPanel {
		private static final long serialVersionUID = 1L;
		private JButton jbtFirst = new JButton("First");
		private JButton jbtNext = new JButton("Next");
		private JButton jbtPrior = new JButton("Prior");
		private JButton jbtLast = new JButton("Last");
		private JButton jbtDelete = new JButton("Delete");
		private JButton jbtInsert = new JButton("Insert");
		private JLabel jlblStatus = new JLabel();

		// Table model, table selection model, table, rowset
		private RowSetTableModel tableModel = new RowSetTableModel();
		private DefaultListSelectionModel listSelectionModel = new DefaultListSelectionModel();
		private JTable jTable1 = new JTable();
		private RowSet rowSet;

		/** Set a new row set */
		public void setRowSet(RowSet rowSet) {
			this.rowSet = rowSet;
			tableModel.setRowSet(rowSet);
			jTable1.setModel(tableModel);
			
			// Enable auto sort on columns
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
			jTable1.setRowSorter(sorter);
		}

		/** Create a TableEditor */
		public TableEditor() {
			JPanel jPanel1 = new JPanel();
			jPanel1.add(jbtFirst);
			jPanel1.add(jbtNext);
			jPanel1.add(jbtPrior);
			jPanel1.add(jbtLast);
			jPanel1.add(jbtDelete);
			jPanel1.add(jbtInsert);

			setLayout(new BorderLayout());
			add(jPanel1, BorderLayout.NORTH);
			add(new JScrollPane(jTable1), BorderLayout.CENTER);
			add(jlblStatus, BorderLayout.SOUTH);

			// Set selection model for the table
			listSelectionModel.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION); 
			jTable1.setSelectionModel(listSelectionModel);

			
			
			// Register listeners
			jbtFirst.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					moveCursor("first");
				}
			});
			jbtNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					moveCursor("next");
				}
			});
			jbtPrior.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					moveCursor("previous");
				}
			});
			jbtLast.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					moveCursor("last");
				}
			});
			jbtDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					delete();
				}
			});

			jbtInsert.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					insert();
				}
			});
			
			listSelectionModel.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					handleSelectionValueChanged(e);
				}
			});
			
		}
		
		public void updateRow(Object value, int row, int column) {
			try {
				rowSet.first();
				rowSet.absolute(row + 1);
				rowSet.updateString(column + 1, value.toString());
				rowSet.updateRow();
				listSelectionModel.setSelectionInterval(row - 1, row );
				jlblStatus.setText("Current row number: " + row);
			} catch (java.sql.SQLException ex) {
				jlblStatus.setText(ex.toString());
			}
		}
		
		private void insert() {
			try {
				rowSet.moveToInsertRow();
				rowSet.updateString(1,"");
				rowSet.updateString(2,"");
				rowSet.updateString(3,"");
				rowSet.updateString(4,"");
				rowSet.updateString(5,"");
				rowSet.updateDate(6, new Date(System.currentTimeMillis()));
				rowSet.updateString(7,"");
				rowSet.updateString(8,"");
				rowSet.updateString(9,"BIOL");				
				rowSet.insertRow();
				rowSet.moveToCurrentRow();
				rowSet.updateRow();
				setTableCursor();
			} catch (java.sql.SQLException ex) {
				jlblStatus.setText(ex.toString());
			}
		}


		/* Delete a row */
		private void delete() {
			try {
				// Delete the record from the database
				int currentRow = rowSet.getRow();
				rowSet.deleteRow();
				if (rowSet.isAfterLast())
					rowSet.last();
				else if (rowSet.getRow() >= currentRow)
					rowSet.absolute(currentRow);
				setTableCursor();
			} catch (java.sql.SQLException ex) {
				jlblStatus.setText(ex.toString());
			}
		}

		/** Set cursor in the table and set the row number in the status */
		private void setTableCursor() throws java.sql.SQLException {
			int row = rowSet.getRow();
			listSelectionModel.setSelectionInterval(row - 1, row - 1);
			jlblStatus.setText("Current row number: " + row);
		}

		/** Move cursor to the specified location */
		private void moveCursor(String whereToMove) {
			try {
				if (whereToMove.equals("first"))
					rowSet.first();
				else if (whereToMove.equals("next") && !rowSet.isLast())
					rowSet.next();
				else if (whereToMove.equals("previous") && !rowSet.isFirst())
					rowSet.previous();
				else if (whereToMove.equals("last"))
					rowSet.last();
				setTableCursor();
			} catch (java.sql.SQLException ex) {
				jlblStatus.setText(ex.toString());
			}
		}

		/** Handle the selection in the table */
		private void handleSelectionValueChanged(ListSelectionEvent e) {
			int selectedRow = jTable1.getSelectedRow();

			try {
				if (selectedRow != -1) {
					rowSet.absolute(selectedRow + 1);
					setTableCursor();
				}
			} catch (java.sql.SQLException ex) {
				jlblStatus.setText(ex.toString());
			}
		}
	}
}