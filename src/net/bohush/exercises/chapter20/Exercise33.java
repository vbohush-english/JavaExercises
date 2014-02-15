package net.bohush.exercises.chapter20;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Exercise33 extends JApplet{

	private static final long serialVersionUID = 1L;

	private KnightPanel knightPanel = new KnightPanel(8);
	private JButton jButton1 = new JButton("Solve");
	
	public Exercise33() {
		
		add(knightPanel, BorderLayout.CENTER);
		
		JPanel jPanel = new JPanel();
		jPanel.add(jButton1);
		add(jPanel, BorderLayout.SOUTH);
		
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1.setEnabled(false);
				knightPanel.buildKnight();
			}
		});
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

	class KnightPanel extends JPanel {
		private int sideNumber = 8;
		private int size;
		private double stepX;
		private double stepY;
		private static final long serialVersionUID = 1L;
		private int[][] board;
		private int startX = 3;
		private int startY = 3;
		private boolean isMoving = false;
		private boolean isSolving = false;
		private Timer timer;
		private int showingMoves = 0;

		public KnightPanel(int size) {
			this.size = size;
			board = new int[size][size];
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					isMoving = false;
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					if ((!isSolving) && (e.getX() >= (int) (startX * stepX)) &&
						(e.getX() <= (int) ((startX + 1) * stepX)) &&
						(e.getY() >= (int) (startY * stepY)) &&
						(e.getY() <= (int) ((startY + 1) * stepY))) {
						isMoving = true;
						showingMoves = 0;
						isSolving = true;
						for (int i = 0; i < KnightPanel.this.size; i++) {
							for (int j = 0; j < KnightPanel.this.size; j++) {
								board[i][j] = 0;
							}				
						}
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					isMoving = false;					
				}
				
			});
			
			addMouseMotionListener(new MouseMotionAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if (isMoving) {
						startX = (int) (e.getX() / stepX);
						startY = (int) (e.getY() / stepY);
						repaint();
					}
					
				}
			});
			
			timer = new Timer(200, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showingMoves++;
					if (showingMoves == KnightPanel.this.size * KnightPanel.this.size) {
						isSolving = false;
						jButton1.setEnabled(true);
						timer.stop();
					}
					repaint();					
				}
			});
		}
		
		public void buildKnight() {
			showingMoves = 0;
			isSolving = true;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					board[i][j] = 0;
				}				
			}
			try {
				board[startX][startY] = 1;
				buildPath(startX, startY);
			} catch (NumberFormatException e) {
			} catch (ArrayIndexOutOfBoundsException e) {
			}
			repaint();
			timer.start();
		
		}
		
		class Moves {
			public int x;
			public int y;
			public int avaliable;
			public Moves(int x, int y, int avaliable) {
				super();
				this.x = x;
				this.y = y;
				this.avaliable = avaliable;
			}
		}
		
		public boolean buildPath(int x, int y) {
			
			if (checkPath()) {
				return true;
			}		

			int[][] newPos = new int[sideNumber][2];
			newPos[0][0] = x + 2;
			newPos[1][0] = x + 1;
			newPos[2][0] = x - 1;
			newPos[3][0] = x - 2;
			newPos[4][0] = x - 2;
			newPos[5][0] = x - 1;
			newPos[6][0] = x + 1;
			newPos[7][0] = x + 2;
			newPos[0][1] = y - 1;
			newPos[1][1] = y - 2;
			newPos[2][1] = y - 2;
			newPos[3][1] = y - 1;
			newPos[4][1] = y + 1;
			newPos[5][1] = y + 2;
			newPos[6][1] = y + 2;
			newPos[7][1] = y + 1;
			ArrayList<Moves> sortedMoves = new ArrayList<>();
			for (int i = 0; i < newPos.length; i++) {
				int newX = newPos[i][0];
				int newY = newPos[i][1];
				int avaliableMoves = getAllowedPositions(newX, newY);
				if ((newX < size) && (newY < size) && (newY >= 0) && (newX >= 0) && (board[newX][newY] == 0)) {
					sortedMoves.add(new Moves(newX, newY, avaliableMoves));
				};
			}
			

			for (int i = 0; i < sortedMoves.size() - 1; i++) {
				for (int j = i + 1; j < sortedMoves.size(); j++) {
					if (sortedMoves.get(i).avaliable > sortedMoves.get(j).avaliable) {
						Moves tmp = sortedMoves.get(i);
						sortedMoves.set(i, sortedMoves.get(j));
						sortedMoves.set(j, tmp);
					}
				}
			}
			
			for (int i = 0; i < sortedMoves.size(); i++) {
				int nextX = sortedMoves.get(i).x;
				int nextY = sortedMoves.get(i).y;	
				board[nextX][nextY] = board[x][y] + 1;
				if (buildPath(nextX, nextY)) {
					return true;
				}
			
			}
			
			board[x][y] = 0;
			return false;
			
		}
		
		
		private int getAllowedPositions(int x, int y) {
			int result = 0;
			int[][] newPos = new int[sideNumber][2];
			newPos[0][0] = x + 2;
			newPos[1][0] = x + 1;
			newPos[2][0] = x - 1;
			newPos[3][0] = x - 2;
			newPos[4][0] = x - 2;
			newPos[5][0] = x - 1;
			newPos[6][0] = x + 1;
			newPos[7][0] = x + 2;
			newPos[0][1] = y - 1;
			newPos[1][1] = y - 2;
			newPos[2][1] = y - 2;
			newPos[3][1] = y - 1;
			newPos[4][1] = y + 1;
			newPos[5][1] = y + 2;
			newPos[6][1] = y + 2;
			newPos[7][1] = y + 1;
			for (int i = 0; i < newPos.length; i++) {
				int newX = newPos[i][0];
				int newY = newPos[i][1];
				if ((newX < size) && (newY < size) && (newY >= 0) && (newX >= 0) && (board[newX][newY] == 0)) {
					result++;
				}
			}


			return result;
		}
		
		public boolean checkPath() {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (board[i][j] == 0) {
						return false;
					}
				}				
			}	
			return true;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			stepX = getWidth() / (double)size;
			stepY = getHeight() / (double)size;
			
			g.drawImage(new ImageIcon("image/20_33/knight.jpg").getImage(), (int) (startX * stepX), (int) (startY * stepY), (int)stepX, (int)stepY, this);
			if (showingMoves != 0) {
				Point p = getCoordinats(showingMoves);
				g.drawImage(new ImageIcon("image/20_33/knight.jpg").getImage(), (int) (p.x * stepX), (int) (p.y * stepY), (int)stepX, (int)stepY, this);
			}
			
			
			g.setColor(Color.DARK_GRAY);
			for (int i = 0; i < size; i++) {
				g.drawLine(0, (int) (i * stepY), getWidth(), (int) (i * stepY));
				g.drawLine((int) (i * stepX), 0, (int) (i * stepX), getHeight());
			}

			g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
			g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());

			g.setColor(Color.BLUE);
			for (int i = 1; i < showingMoves; i++) {
				Point p1 = getCoordinats(i);
				Point p2 = getCoordinats(i + 1);
				if (board[p1.x][p1.y] != 0) {
					g.drawLine((int) ((p1.x + 0.5) * stepX), (int) ((p1.y + 0.5) * stepY),
							(int) ((p2.x + 0.5) * stepX), (int) ((p2.y + 0.5) * stepY));					
				}
			}
			
		}
		
		private Point getCoordinats(int number) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (board[i][j] == number) {
						return new Point(i, j);
					}
				}				
			}
			return new Point(0, 0);
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(size * 50, size * 50);
		}

	}	
	
}
