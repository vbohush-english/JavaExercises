package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise26 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise26() {
		add(new CirclePanel());
	}
	
	public static void main(String[] args) {
		Exercise26 frame = new Exercise26();
		frame.setTitle("Exercise26");
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
		private int deltaX;
		private int deltaY;
		boolean firstRun = true;
		boolean isMoving = false;
		private int radius = 10;

		public CirclePanel() {
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if (isMoving) {
						x = e.getX() + deltaX;
						y = e.getY() + deltaY;
						repaint();
						isMoving = false;
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					if (getLenght(e) < radius) {
						deltaX = x - e.getX();
						deltaY = y - e.getY();
						isMoving = true;
					}					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					isMoving = false;
					repaint();
				}
			});
			
			addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent arg0) {
					if (isMoving) {
						x = arg0.getX() + deltaX;
						y = arg0.getY() + deltaY;
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
			if (firstRun) {
				x = radius + (int) (Math.random() * (getWidth() - 2 * radius));
				y = radius + (int) (Math.random() * (getHeight() - 2 * radius));
				firstRun = false;
			}
			g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
			
		}
	}
}
