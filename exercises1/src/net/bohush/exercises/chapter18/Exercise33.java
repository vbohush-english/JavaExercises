package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Exercise33 extends JApplet{
	private static final long serialVersionUID = 1L;
	private final int ROWS = 6;
	private final int COLS = 7;
	private JTextField[][] jTextFields = new JTextField[ROWS][COLS];
	private ArrayList<Pair> result = new ArrayList<>();
	
	class Pair {
		private int a;
		private int b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public int getA() {
			return a;
		}
		public int getB() {
			return b;
		}
	}
	
	public Exercise33() {
		JPanel panel1 = new JPanel(new GridLayout(ROWS, COLS));
		Font font = new Font("Monospaced", Font.BOLD, 30);
		for (int i = 0; i < jTextFields.length; i++) {
			for (int j = 0; j < jTextFields[i].length; j++) {
				jTextFields[i][j] = new JTextField(3);
				jTextFields[i][j].setFont(font);
				jTextFields[i][j].setHorizontalAlignment(JTextField.CENTER);
				panel1.add(jTextFields[i][j]);
			}

		}
		add(panel1, BorderLayout.CENTER);
		JPanel panel2 = new JPanel();
		JButton jButton1 = new JButton("Solve");
		JButton jButton2 = new JButton("Fill Random");
		jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < jTextFields.length; i++) {
					for (int j = 0; j < jTextFields[i].length; j++) {
						jTextFields[i][j].setText((int) (Math.random() * 10) + "");
						jTextFields[i][j].setBackground(Color.WHITE);
					}
				}
			}
		});
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int[][] values = new int[ROWS][COLS];
				boolean wasError = false;
				for (int i = 0; i < values.length; i++) {
					for (int j = 0; j < values[i].length; j++) {
						jTextFields[i][j].setBackground(Color.WHITE);
						try {
							values[i][j] = Integer.parseInt(jTextFields[i][j].getText());	
						} catch (NumberFormatException e2) {
							jTextFields[i][j].setBackground(Color.RED);
							wasError = true;
						}
					}
				}
				if ((!wasError) && (isConsecutiveFour(values))) {
					for (int i = 0; i < result.size(); i++) {
						jTextFields[result.get(i).getA()][result.get(i).getB()].setBackground(Color.LIGHT_GRAY);
					}
				}
			}
		});
		panel2.add(jButton1);
		panel2.add(jButton2);
		add(panel2, BorderLayout.SOUTH);
	}
	
	public boolean isConsecutiveFour(int[][] values) {
		return isRowConsecutiveFour(values)||isColumnConsecutiveFour(values)||isDiagonal1ConsecutiveFour(values)||isDiagonal2ConsecutiveFour(values);
	}
	
	
	public boolean isDiagonal2ConsecutiveFour(int[][] values) {
		for (int i = 0; i < values[0].length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j <= i; j++) {
				if ((i - j) >= values[0].length) {
					break;
				}
				if ((values.length - j - 1) < 0) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - 1][i - j];
					consecutiveCount++;	
					result.clear();
					result.add(new Pair(values.length - j - 1, i - j));
				} else if (consecutiveNumber == values[values.length - j - 1][i - j]) {
					consecutiveCount++;
					result.add(new Pair(values.length - j - 1, i - j));
					if (consecutiveCount == 4) {
						System.out.println(1);
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - 1][i - j];
					consecutiveCount = 1;
					result.clear();
					result.add(new Pair(values.length - j - 1, i - j));
				}
			}
		}
		
		for (int i = 1; i < values.length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j < values.length - i; j++) {
				if ((values[0].length - j - 1) < 0) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - i - 1][values[0].length - j - 1];
					consecutiveCount++;		
					result.clear();
					result.add(new Pair(values.length - j - i - 1, values[0].length - j - 1));
				} else if (consecutiveNumber == values[values.length - j - i - 1][values[0].length - j - 1]) {
					consecutiveCount++;
					result.add(new Pair(values.length - j - i - 1, values[0].length - j - 1));
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - i - 1][values[0].length - j - 1];
					consecutiveCount = 1;
					result.clear();
					result.add(new Pair(values.length - j - i - 1, values[0].length - j - 1));
				}
			}
		}
		result.clear();
		return false;
	}
	
	
	
	public boolean isDiagonal1ConsecutiveFour(int[][] values) {
		for (int i = 0; i < values.length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j <= i; j++) {
				if (j >= values[0].length) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[i - j][j];
					consecutiveCount++;		
					result.clear();
					result.add(new Pair(i - j, j));
				} else if (consecutiveNumber == values[i - j][j]) {
					consecutiveCount++;
					result.add(new Pair(i - j, j));
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[i - j][j];
					consecutiveCount = 1;
					result.clear();
					result.add(new Pair(i - j, j));
				}
			}
		}
		for (int i = 1; i < values[0].length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j < values.length; j++) {
				if ((i + j) >= values[0].length) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - 1][i + j];
					consecutiveCount++;		
					result.clear();
					result.add(new Pair(values.length - j - 1, i + j));
				} else if (consecutiveNumber == values[values.length - j - 1][i + j]) {
					consecutiveCount++;
					result.add(new Pair(values.length - j - 1, i + j));
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - 1][i + j];
					consecutiveCount = 1;
					result.clear();
					result.add(new Pair(values.length - j - 1, i + j));
				}
			}
		}
		result.clear();
		return false;
	}


	
	public boolean isRowConsecutiveFour(int[][] values) {
		int consecutiveCount = 0;
		int consecutiveNumber = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (consecutiveCount == 0) {
					consecutiveNumber = values[i][j];
					consecutiveCount++;	
					result.clear();
					result.add(new Pair(i, j));
				} else if (consecutiveNumber == values[i][j]) {
					consecutiveCount++;
					result.add(new Pair(i, j));
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[i][j];
					consecutiveCount = 1;
					result.clear();
					result.add(new Pair(i, j));
				}				
			}
			consecutiveCount = 0;
		}
		result.clear();
		return false;
	}
	
	
	public boolean isColumnConsecutiveFour(int[][] values) {
		int consecutiveCount = 0;
		int consecutiveNumber = 0;
		for (int i = 0; i < values[0].length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (consecutiveCount == 0) {
					consecutiveNumber = values[j][i];
					consecutiveCount++;		
					result.clear();
					result.add(new Pair(j, i));
				} else if (consecutiveNumber == values[j][i]) {
					consecutiveCount++;
					result.add(new Pair(j, i));
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[j][i];
					consecutiveCount = 1;
					result.clear();
					result.add(new Pair(j, i));
				}				
			}
			consecutiveCount = 0;
		}
		result.clear();
		return false;
	}

	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise33());
		frame.setTitle("Exercise33");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
