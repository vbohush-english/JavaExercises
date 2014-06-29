package net.bohush.exercises.chapter24;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Exercise19 extends JApplet {

	private static final long serialVersionUID = 1L;
	private int arrSize = 10;
	private JTextField[] jTextFields = new JTextField[arrSize * arrSize];

	public Exercise19() {
		JPanel jPanel1 = new JPanel(new GridLayout(arrSize, arrSize));
		jPanel1.setBorder(new LineBorder(Color.BLACK));
		for (int i = 0; i < jTextFields.length; i++) {
			jTextFields[i] = new JTextField((Math.random() < 0.5 ? "0" : "1"), 3);
			jTextFields[i].setHorizontalAlignment(JTextField.CENTER);
			jTextFields[i].setFont(new Font("Monospaced", Font.BOLD, 20));
			jPanel1.add(jTextFields[i]);
		}
		add(jPanel1, BorderLayout.CENTER);
		JPanel jPanel2 = new JPanel();
		JButton jButton1 = new JButton("Refresh");
		jPanel2.add(jButton1);
		JButton jButton2 = new JButton("Find Largest Block");
		jPanel2.add(jButton2);
		add(jPanel2, BorderLayout.SOUTH);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < jTextFields.length; i++) {
					jTextFields[i].setText((Math.random() < 0.5 ? "0" : "1"));
					jTextFields[i].setForeground(Color.BLACK);
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int[][] data = new int[arrSize][arrSize];
				for (int i = 0; i < arrSize; i++) {
					for (int j = 0; j < arrSize; j++) {
						jTextFields[i * arrSize + j].setForeground(Color.BLACK);
						data[i][j] = Integer.parseInt(jTextFields[i * arrSize + j].getText());
					}
				}
				
				int[] result = findLargestBlock(data);
				
				for (int i = result[0]; i < result[0] + result[2]; i++) {
					for (int j =  result[1]; j < result[1] + result[2]; j++) {
						jTextFields[i * arrSize + j].setForeground(Color.RED);
					}
				}			
				
			}
		});
	}
	
	
	public static int[] findLargestBlock(int[][] m) {
		int[] submatrix = new int[3];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 1) {
					int size = getSubMatrixSize(i, j, m);
					if (size > submatrix[2]) {
						submatrix[0] = i;
						submatrix[1] = j;
						submatrix[2] = size;
					}
					
				}
			}
		}
		return submatrix;
	}
	
	
	public static int getSubMatrixSize(int row, int column, int[][] m) {
		int result = 0;
		for (int i = row; i < m.length; i++) {
			for (int j = column; j < m[i].length; j++) {
				for (int size = 1; size < m.length; size++) {
					if (!isAll1s(row, column, size, m)) {
						if (result < (size - 1)) {
							result = size - 1;
						}
						break;
					}
				}
			}
		}
		return result;
	}
	
	public static boolean isAll1s(int row, int column, int size, int[][] m) {
		for (int i = row; i < row + size; i++) {
			if (i >= m.length) {
				return false;
			}
			for (int j = column; j < column + size; j++) {
				if (j >= m[i].length) {
					return false;
				}
				if (m[i][j] != 1) {
					return false;
				}

			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise19");
		Exercise19 applet = new Exercise19();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}