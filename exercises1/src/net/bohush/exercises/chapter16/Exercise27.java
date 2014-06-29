package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise27 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise27() {
		add(new CirclePanel());
	}
	
	public static void main(String[] args) {
		Exercise27 frame = new Exercise27();
		frame.setTitle("Exercise27");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class CirclePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int x;
		private int y;
		private int radius = 10;
		private int guessCount = 0;
		long startTime = System.currentTimeMillis();
		boolean newGame = true;	
		Color color;
		public CirclePanel() {
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					if (getLenght(arg0) < radius) {
						newGame = true;
						guessCount++;
						repaint();
					}
				}
				

			});
		}
		
		private double getLenght(MouseEvent arg0){
			return Math.sqrt((arg0.getX() - x) * (arg0.getX() - x) + (arg0.getY() - y) * (arg0.getY() - y));
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (guessCount < 20) {
				if (newGame) {
					x = radius + (int) (Math.random() * (getWidth() - 2 * radius));
					y = radius + (int) (Math.random() * (getHeight() - 2 * radius));
					color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
					newGame = false;
				}
				g.setColor(color);
				g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
			} else {
				g.drawString("Time spent: " + (System.currentTimeMillis() - startTime) + " milliseconds", 20, 20);
			}
			
		}
	}
}
