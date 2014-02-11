package net.bohush.exercises.chapter20;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise26 extends JApplet{

	private static final long serialVersionUID = 1L;

	private MazePanel mazePanel = new MazePanel(8);
	private JLabel jLabel = new JLabel(" ");
	
	public Exercise26() {
		
		add(jLabel, BorderLayout.NORTH);
		
		add(mazePanel, BorderLayout.CENTER);
		
		JPanel jPanel = new JPanel();
		JButton jButton1 = new JButton("Find Path");
		JButton jButton2 = new JButton("Clear Path");
		jPanel.add(jButton1);
		jPanel.add(jButton2);
		add(jPanel, BorderLayout.SOUTH);
		
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mazePanel.buildPath()) {
					jLabel.setText("path found");
				} else {
					jLabel.setText("path not found");
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mazePanel.clearPath();
				jLabel.setText(" ");
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise26());
		frame.setTitle("Exercise26");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 350);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	static class MazePanel extends JPanel {
		private int size;
		private double stepX;
		private double stepY;
		private static final long serialVersionUID = 1L;
		private int[][] board;
		private static int errorCount = 0;

		public MazePanel(int size) {
			this.size = size;
			board = new int[size][size];
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					int x = (int)(e.getX() / stepX);
					int y = (int)(e.getY() / stepY);
					if ((x < MazePanel.this.size) && (y < MazePanel.this.size) && (x >= 0) && (y >= 0)) {
						board[x][y] = (board[x][y] == 1 ? 0 : 1);
						repaint();
					}
				}
			});
		}
		
		public boolean buildPath() {
			if (board[0][0] != 0) {
				return false;
			} else {
				board[0][0] = 2;
				if (buildPath(0, 0, 3)) {
					repaint();
					return true;				
				} else {
					repaint();
					return false;
				}
			}
		}
		
		public boolean buildPath(int x, int y, int side) {
			repaint();
			
			if ((x == size - 1) && (y == size - 1)) {
				return true;
			}
			
			if(errorCount == 4) {
				return false;
			}
			
			if (side > 4) {
				side = side - 4;
			}
			
			int nextX = x;
			int nextY = y;
			
			switch (side) {
			case 1: nextY--; break;
			case 2: nextX++; break;
			case 3: nextY++; break;
			case 4: nextX--; break;
			}
			
			if ((nextX == size) || (nextY == size) || (nextY == -1) || (nextX == -1) || (board[nextX][nextY] != 0)) {
				errorCount++;
				boolean result = buildPath(x, y, side + 1);
				System.out.println(1 + " x:" + x + " y:" + y + " side:" + side + " r:" + result);
				return result;
				
			} else {
				errorCount = 0;
				board[nextX][nextY] = 2;
				boolean result = buildPath(nextX, nextY, side);
				System.out.println(2 + " nextX:" + nextX + " nextY:" + nextY + " side:" + side + " r:" + result);
				return result;
			}
		}
		
		public void clearPath() {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					board[i][j] = 0;
				}				
			}	
			errorCount = 0;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			stepX = getWidth() / (double)size;
			stepY = getHeight() / (double)size;

			g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
			g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (board[i][j] == 1) {
						g.setColor(Color.RED);
						g.drawLine((int) (i * stepX), (int) (j * stepY), (int) ((i + 1) * stepX), (int) ((j + 1) * stepY));
						g.drawLine((int) (i * stepX), (int) ((j + 1) * stepY), (int) ((i + 1) * stepX), (int) (j * stepY));
					} else if (board[i][j] == 2) {
						g.setColor(Color.BLUE);
						g.fillRect((int) (i * stepX), (int) (j * stepY), (int) (stepX + 1), (int) (stepY + 1));
					}
				}				
			}
			
			g.setColor(Color.GREEN);
			for (int i = 0; i < size; i++) {
				g.drawLine(0, (int) (i * stepY), getWidth(), (int) (i * stepY));
				g.drawLine((int) (i * stepX), 0, (int) (i * stepX), getHeight());
			}
		}

	}	
	
}
