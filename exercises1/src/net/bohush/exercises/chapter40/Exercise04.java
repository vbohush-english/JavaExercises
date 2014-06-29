package net.bohush.exercises.chapter40;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.*;


public class Exercise04 extends JApplet {
	private static final long serialVersionUID = 1L;
	
	public Exercise04() throws FileNotFoundException {
		Scanner input = new Scanner(new File("tmp/Exercise40_04.txt"));
		String stringNames = input.nextLine();
		String[] columnNames = stringNames.split(", ");
		
		ArrayList<String> StringRowData = new ArrayList<>();
		while(input.hasNextLine()) {
			StringRowData.add(input.nextLine());
		}
		input.close();
		Object[][] rowData = new Object[StringRowData.size()][];
		for (int i = 0; i < rowData.length; i++) {
			rowData[i] = StringRowData.get(i).split(", ");
		}

		
		DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
		JTable jTable1 = new JTable(tableModel);
		add(new JScrollPane(jTable1), BorderLayout.CENTER);

	}

	// Main method
	public static void main(String[] args) throws FileNotFoundException {
		Exercise04 applet = new Exercise04();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise04");
		frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}