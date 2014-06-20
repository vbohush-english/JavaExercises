package net.bohush.exercises.chapter40;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.util.*;

public class Exercise03 extends JApplet {
	private static final long serialVersionUID = 1L;

	// Create table column names
	private String[] columnNames = { "Name", "Birthday", "Class Status", "In-State", "Photo"};

	// Create image icons
	private ImageIcon imageIcon = new ImageIcon(getClass().getResource("image/person.jpg"));

	// Create table data
	private Object[][] rowData = {
			{ "Jeff F. Smith", new GregorianCalendar(1998, 9 - 1, 29).getTime(), "Sophomore", false, imageIcon},
			{ "John F. Kay", new GregorianCalendar(1997, 9 - 1, 29).getTime(), "Senior", false, imageIcon}};

	// Create a table model
	private MyTableModel tableModel = new MyTableModel(rowData, columnNames);

	// Create a table
	private JTable jTable1 = new JTable(tableModel);

	public Exercise03() {
		jTable1.setRowHeight(60);
		
	    JComboBox<String> jcboPublishers = new JComboBox<>();
	    jcboPublishers.addItem("Freshman");
	    jcboPublishers.addItem("Sophomore");
	    jcboPublishers.addItem("Junior");
	    jcboPublishers.addItem("Senior");
	    jcboPublishers.addItem("Graduete");
	    TableColumn publisherColumn = jTable1.getColumn("Class Status");
	    publisherColumn.setCellEditor(new DefaultCellEditor(jcboPublishers));
	    
		add(new JScrollPane(jTable1), BorderLayout.CENTER);
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