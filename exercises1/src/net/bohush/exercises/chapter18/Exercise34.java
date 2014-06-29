package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Exercise34 extends JApplet{
	private static final long serialVersionUID = 1L;
	private JPanel panel3 = new JPanel(new BorderLayout());
	
	public Exercise34() {
		panel3.add(new ConnectFourPanel(), BorderLayout.CENTER);
		add(panel3, BorderLayout.CENTER);
		JPanel panel2 = new JPanel();
		JButton jButton1 = new JButton("Start Over");
		panel2.add(jButton1);
		add(panel2, BorderLayout.SOUTH);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel3.removeAll();
				panel3.add(new ConnectFourPanel());
				panel3.updateUI();
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise34());
		frame.setTitle("Exercise34");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class ConnectFourPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private final int COLS = 7;
		private final int ROWS = 6;
		private int radius = 50;
		private int delta = 2;
		private int firstX;
		private int firstY;
		private int[][] board;
		private int activePlayer = 1;
		private ArrayList<Pair> result = new ArrayList<>();
		private boolean isFinish = false;
		private Color flashColor = Color.WHITE;
		private Timer timer;
		
		public ConnectFourPanel() {
			board = new int[ROWS][COLS];
			int tmpNumber = 1000;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					board[i][j] = tmpNumber++;
				}
			}
			
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if (!isFinish &&
						(e.getX() > firstX) &&
						(e.getY() > firstY) &&
						(e.getX() < firstX + COLS * radius) &&
						(e.getY() < firstY + ROWS * radius)) {
							int column = (e.getX() - firstX) / radius;
							if (putDisk(column)) {
								activePlayer = (activePlayer == 1 ? 2 : 1);
								repaint();
								if (isConsecutiveFour(board)) {
									isFinish = true;
									if (activePlayer == 2) {
										flashColor = Color.YELLOW;
									} else {
										flashColor = Color.RED;
									}
									timer.start();
								} else if (isDraw()) {
									isFinish = true;								
								}
							}							
					}
				}
				
			});
			timer = new Timer(200, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (flashColor == Color.WHITE) {
						if (activePlayer == 2) {
							flashColor = Color.YELLOW;
						} else {
							flashColor = Color.RED;
						}
					} else {
						flashColor = Color.WHITE;
					}
					repaint();
				}
			});
		}
		
		public boolean putDisk(int column) {
			if (board[0][column] <= 2) {
				return false;
			}
			for (int i = 0; i < board.length - 1; i++) {
				if (board[i + 1][column] <= 2) {
					board[i][column] = activePlayer;
					return true;
				};
			}
			board[board.length - 1][column] = activePlayer;
			return true;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			radius = Math.min(getWidth() / COLS, getHeight() / ROWS);
			firstX = (getWidth() - radius * COLS) / 2;
			firstY = (getHeight() - radius * ROWS) / 2;
			for (int i = 0; i < COLS; i++) {
				for (int j = 0; j < ROWS; j++) {
					if (board[j][i] == 1) {
						g.setColor(Color.YELLOW);
					} else if (board[j][i] == 2) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.WHITE);			
					}
					g.fillOval(firstX + i * radius + delta, firstY + j * radius + delta, radius - 2 * delta, radius - 2 * delta);
				}
			}
			if (result.size() == 4) {
				g.setColor(flashColor);
				for (int i = 0; i < result.size(); i++) {
					g.fillOval(firstX + result.get(i).getB() * radius + delta, firstY + result.get(i).getA() * radius + delta, radius - 2 * delta, radius - 2 * delta);
				}				
			}

		}
		
		public boolean isDraw() {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] > 2) {
						return false;
					}
				}
			}
			return true;
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(COLS * radius, ROWS * radius);
		}
		
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

	}


}
