package net.bohush.exercises.chapter32;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise21 extends JApplet{

	private static final long serialVersionUID = 1L;
	private int currentRow = -1;
	
	public Exercise21() {		
		add(new QueensPanel(8), BorderLayout.CENTER);
		JLabel jLabel = new JLabel("Click the mouse to see the next move");
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(jLabel, BorderLayout.SOUTH);
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				currentRow++;
				repaint();
			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise21());
		frame.setTitle("Exercise21");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class QueensPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int size;
		private StringBuilder queen = null;
		
		public QueensPanel(int size) {
			this.size = size;
			queen = getBoard(size);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			double stepX = getWidth() / (double)size;
			double stepY = getHeight() / (double)size;
			
			if (queen != null) {
				Image queenImage = new ImageIcon("image/20_34/queen.jpg").getImage();
				for (int i = 0; (i < queen.length()) && (i <= currentRow); i++) {					
					g.drawImage(queenImage, (int) (queen.charAt(i) * stepX), (int) (i * stepY), (int)stepX, (int)stepY, this);	
				}				
			}
			
			if((currentRow >= 0) && (currentRow < size)) {
				g.setColor(Color.GRAY);
				g.fillRect(0, (int) ((currentRow + 1) * stepY), getHeight(), (int)(stepY));
				g.setColor(Color.BLACK);
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
	
		private StringBuilder getBoard(int size) {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < size; i++) {
				s.append((char)(i));
			}
			return getBoard(new StringBuilder(), s);
		}
		
		private StringBuilder getBoard(StringBuilder s1, StringBuilder s2) {
			if (s2.length() == 2) {			
				if (isDiagonalFine(new StringBuilder(s1).append(s2))) {
					return new StringBuilder(s1).append(s2);
				}
				if (isDiagonalFine(new StringBuilder(s1).append(s2.charAt(1)).append(s2.charAt(0)))) {
					return new StringBuilder(s1).append(s2.charAt(1)).append(s2.charAt(0));
				}				
			} else {
				for (int i = 0; i < s2.length(); i++) {
					StringBuilder newS2 = new StringBuilder();
					for (int j = 0; j < s2.length(); j++) {
						if (j != i)
							newS2.append(s2.charAt(j));
					}				
					StringBuilder result = getBoard(new StringBuilder(s1).append(s2.charAt(i)), newS2);
					if(result != null) {
						return result;
					}
				}
			}
			return null;
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
