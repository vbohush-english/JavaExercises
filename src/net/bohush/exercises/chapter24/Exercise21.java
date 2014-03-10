package net.bohush.exercises.chapter24;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise21 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JTextField[] jTextFields = new JTextField[81];
	
	public Exercise21() {
		
		JPanel jPanel1 = new JPanel();
		JButton jButton1 = new JButton("Solve");
		jPanel1.add(jButton1);
		JButton jButton2 = new JButton("Clear");
		jPanel1.add(jButton2);
		add(jPanel1, BorderLayout.SOUTH);
		
		JPanel jPanel2 = new JPanel(new GridLayout(3, 3, 4, 4));
		jPanel2.setBackground(Color.BLACK);
		for (int i = 0; i < 9; i++) {
			JPanel tmp = new JPanel(new GridLayout(3, 3, 1, 1));
			tmp.setBackground(Color.BLACK);
			for (int j = 0; j < 9; j++) {
				jTextFields[i * 9 + j] = new JTextField(3);
				jTextFields[i * 9 + j].setHorizontalAlignment(JTextField.CENTER);
				jTextFields[i * 9 + j].setFont(new Font("Monospaced", Font.BOLD, 20));
				tmp.add(jTextFields[i * 9 + j]);
			}
			jPanel2.add(tmp);
		}
		add(jPanel2, BorderLayout.CENTER);
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < jTextFields.length; i++) {
					jTextFields[i].setText("");
					jTextFields[i].setBackground(Color.WHITE);
				}
			}
		});
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int[][] grid = new int[9][9];
				for (int k = 0; k < 3; k++) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							for (int l = 0; l <= 6; l+=3) {
								String value = jTextFields[i * 9 + j + l * 6 + 3 * (l + k)].getText();
								if (value.equals("")) {
									grid[l + k][i * 3 + j] = 0;
								} else {
									grid[l + k][i * 3 + j] = Integer.parseInt(value);
								}
							}
						}
					}
				}
				if (!isValid(grid))
					JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
				else if (search(grid)) {
					for (int k = 0; k < 3; k++) {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								for (int l = 0; l <= 6; l+=3) {
									String value = jTextFields[i * 9 + j + l * 6 + 3 * (l + k)].getText();
									if (value.equals("")) {
										jTextFields[i * 9 + j + l * 6 + 3 * (l + k)].setText(grid[l + k][i * 3 + j] + "");
									} else {
										jTextFields[i * 9 + j + l * 6 + 3 * (l + k)].setBackground(Color.LIGHT_GRAY);
									}
									
								}
							}
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "No solution", "Warning", JOptionPane.WARNING_MESSAGE);
			}			
		});
	}

	static class LinearSearchPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString("asd", 10, 10);
		}

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise21");
		Exercise21 applet = new Exercise21();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	/** Obtain a list of free cells from the puzzle */
	public static int[][] getFreeCellList(int[][] grid) {
		// Determine the number of free cells
		int numberOfFreeCells = 0;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (grid[i][j] == 0)
					numberOfFreeCells++;
		// Store free cell positions into freeCellList
		int[][] freeCellList = new int[numberOfFreeCells][2];
		int count = 0;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (grid[i][j] == 0) {
					freeCellList[count][0] = i;
					freeCellList[count++][1] = j;
				}
		return freeCellList;
	}

	/** Search for a solution */
	public static boolean search(int[][] grid) {
		int[][] freeCellList = getFreeCellList(grid); // Free cells
		if (freeCellList.length == 0)
			return true; // "No free cells");
		int k = 0; // Start from the first free cell
		while (true) {
			int i = freeCellList[k][0];
			int j = freeCellList[k][1];
			if (grid[i][j] == 0)
				grid[i][j] = 1; // Fill the free cell with number 1
			if (isValid(i, j, grid)) {
				if (k + 1 == freeCellList.length) { // No more free cells
					return true; // A solution is found
				} else { // Move to the next free cell
					k++;
				}
			} else if (grid[i][j] < 9) {
				// Fill the free cell with the next possible value
				grid[i][j] = grid[i][j] + 1;
			} else { // free cell grid[i][j] is 9, backtrack
				while (grid[i][j] == 9) {
					if (k == 0) {
						return false; // No possible value
					}
					grid[i][j] = 0; // Reset to free cell
					k--; // Backtrack to the preceding free cell
					i = freeCellList[k][0];
					j = freeCellList[k][1];
				}
				// Fill the free cell with the next possible value,
				// search continues from this free cell at k
				grid[i][j] = grid[i][j] + 1;
			}
		}
	}

	/** Check whether grid[i][j] is valid in the grid */
	public static boolean isValid(int i, int j, int[][] grid) {
		// Check whether grid[i][j] is valid at the i's row
		for (int column = 0; column < 9; column++)
			if (column != j && grid[i][column] == grid[i][j])
				return false;
		// Check whether grid[i][j] is valid at the j's column
		for (int row = 0; row < 9; row++)
			if (row != i && grid[row][j] == grid[i][j])
				return false;
		// Check whether grid[i][j] is valid in the 3 by 3 box
		for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
			for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
				if (row != i && col != j && grid[row][col] == grid[i][j])
					return false;
		return true; // The current value at grid[i][j] is valid
	}

	/** Check whether the fixed cells are valid in the grid */
	public static boolean isValid(int[][] grid) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (grid[i][j] < 0 || grid[i][j] > 9
						|| (grid[i][j] != 0 && !isValid(i, j, grid)))
					return false;
		return true; // The fixed cells are valid
	}
}