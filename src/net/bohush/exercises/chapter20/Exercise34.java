package net.bohush.exercises.chapter20;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Exercise34 extends JApplet{

	private static final long serialVersionUID = 1L;	
	private JLabel label = new JLabel(" ");
	
	public Exercise34() {		
		add(new QueensPanel(8), BorderLayout.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.SOUTH);
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
	
	class QueensPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int size;
		int currentBoard = -1;
		
		private ArrayList<StringBuilder> queensList = new ArrayList<>();
		
		public QueensPanel(int size) {
			this.size = size;
			getBoard(size);
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					currentBoard++;
					if (currentBoard == queensList.size())
						currentBoard = 0;
					repaint();
				}
				
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			double stepX = getWidth() / (double)size;
			double stepY = getHeight() / (double)size;
			
			if (currentBoard >= 0) {
				label.setText((currentBoard + 1) + " of " + queensList.size());
				Image queenImage = new ImageIcon("image/20_34/queen.jpg").getImage();
				for (int i = 0; i < size; i++) {
					
					g.drawImage(queenImage, (int) ((queensList.get(currentBoard).charAt(i) - '0') * stepX), (int) (i * stepY), (int)stepX, (int)stepY, this);	
				}				
			}
			
			g.setColor(Color.DARK_GRAY);
			for (int i = 0; i < size; i++) {
				g.drawLine(0, (int) (i * stepY), getWidth(), (int) (i * stepY));
				g.drawLine((int) (i * stepX), 0, (int) (i * stepX), getHeight());
			}

			g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
			g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());

		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(size * 50, size * 50);
		}
	
		private void getBoard(int size) {
			queensList.clear();
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < size; i++) {
				s.append(i);
			}
			getBoard(new StringBuilder(), new StringBuilder(s));
			currentBoard++;
		}
		
		private void getBoard(StringBuilder s1, StringBuilder s2) {
			if (s2.length() == 2) {			
				if (isDiagonalFine(new StringBuilder(s1).append(s2)))
					queensList.add(new StringBuilder(s1).append(s2));
				if (isDiagonalFine(new StringBuilder(s1).append(s2.charAt(1)).append(s2.charAt(0))))
					queensList.add(new StringBuilder(s1).append(s2.charAt(1)).append(s2.charAt(0)));
			} else {
				for (int i = 0; i < s2.length(); i++) {
					StringBuilder newS2 = new StringBuilder();
					for (int j = 0; j < s2.length(); j++) {
						if (j != i)
							newS2.append(s2.charAt(j));
					}				
					getBoard(new StringBuilder(s1).append(s2.charAt(i)), newS2);
				}
			}
		}
		
		private boolean isDiagonalFine(StringBuilder chessboard) {
			for (int i = 0; i < chessboard.length(); i++) {
				for (int j = i + 1; j < chessboard.length(); j++) {
					if (chessboard.charAt(j) - chessboard.charAt(i) == j - i) 
						return false;
					if (chessboard.charAt(i) - chessboard.charAt(j) == j - i) 
						return false;
				}
			}
			return true;	
		}
	}
}
