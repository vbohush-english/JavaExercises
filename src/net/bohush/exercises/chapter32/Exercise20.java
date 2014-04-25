package net.bohush.exercises.chapter32;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise20 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JTextField[] jTextFields = new JTextField[81];
	private JButton jButton1 = new JButton("Solve");
	private JButton jButton2 = new JButton("Clear");
	
	public Exercise20() {
		
		JPanel jPanel1 = new JPanel();
		jPanel1.add(jButton1);
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
				jButton1.setEnabled(false);
				jButton2.setEnabled(false);
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
									jTextFields[i * 9 + j + l * 6 + 3 * (l + k)].setBackground(Color.LIGHT_GRAY);
								}
							}
						}
					}
				}
				if (!isValid(grid))
					JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					new Search(grid);
				}
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
		JFrame frame = new JFrame("Exercise20");
		Exercise20 applet = new Exercise20();
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
	
	class Search implements Runnable {
		private int[][] grid;
		
		public Search(int[][] grid) {
			this.grid = grid;
			Thread thread = new Thread(this);
			thread.start();
		}
		
		private void display() {
			for (int k = 0; k < 3; k++) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int l = 0; l <= 6; l+=3) {
							if(grid[l + k][i * 3 + j] != 0) {
								jTextFields[i * 9 + j + l * 6 + 3 * (l + k)].setText(grid[l + k][i * 3 + j] + "");
							} else {
								jTextFields[i * 9 + j + l * 6 + 3 * (l + k)].setText("");
							}
						}
					}
				}
			}
		}
		
		@Override
		public void run() {
			int[][] freeCellList = getFreeCellList(grid); // Free cells
			if (freeCellList.length == 0) {
				jButton1.setEnabled(true);
				jButton2.setEnabled(true);
				display();
				return; // "No free cells");
			}
			int k2 = 0; // Start from the first free cell
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				display();
				
				int i = freeCellList[k2][0];
				int j = freeCellList[k2][1];
				if (grid[i][j] == 0)
					grid[i][j] = 1; // Fill the free cell with number 1
				if (isValid(i, j, grid)) {
					if (k2 + 1 == freeCellList.length) { // No more free cells
						jButton1.setEnabled(true);
						jButton2.setEnabled(true);
						display();
						return; // A solution is found
					} else { // Move to the next free cell
						k2++;
					}
				} else if (grid[i][j] < 9) {
					// Fill the free cell with the next possible value
					grid[i][j] = grid[i][j] + 1;
				} else { // free cell grid[i][j] is 9, backtrack
					while (grid[i][j] == 9) {
						if (k2 == 0) {
							JOptionPane.showMessageDialog(null, "No solution", "Warning", JOptionPane.WARNING_MESSAGE);
							jButton1.setEnabled(true);
							jButton2.setEnabled(true);
							display();
							return; // No possible value
						}
						grid[i][j] = 0; // Reset to free cell
						k2--; // Backtrack to the preceding free cell
						i = freeCellList[k2][0];
						j = freeCellList[k2][1];
					}
					// Fill the free cell with the next possible value,
					// search continues from this free cell at k
					grid[i][j] = grid[i][j] + 1;
				}
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