package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise37 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise37() {
		add(new CirclePanel());
	}
	
	public static void main(String[] args) {
		Exercise37 frame = new Exercise37();
		frame.setTitle("Exercise37");
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
		
		private int x2;
		private int y2;
		private int deltaX2;
		private int deltaY2;
		
		
		boolean firstRun = true;
		
		boolean isMoving = false;
		boolean isMoving2 = false;
		
		private int radius = 15;

		public CirclePanel() {
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if (isMoving) {
						x = e.getX() + deltaX;
						y = e.getY() + deltaY;
						repaint();
						isMoving = false;
					} else if (isMoving2) {
						x2 = e.getX() + deltaX2;
						y2 = e.getY() + deltaY2;
						repaint();
						isMoving2 = false;
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					if (getLenght(e, x, y) < radius) {
						deltaX = x - e.getX();
						deltaY = y - e.getY();
						isMoving = true;
					} else if (getLenght(e, x2, y2) < radius) {
						deltaX2 = x2 - e.getX();
						deltaY2 = y2 - e.getY();
						isMoving2 = true;
					}	
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					isMoving = false;
					isMoving2 = false;
					repaint();
				}
			});
			
			addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent arg0) {
					int tmpX = x;
					int tmpY = y;
					int tmpX2 = x2;
					int tmpY2 = y2;
					if (isMoving) {
						tmpX = arg0.getX() + deltaX;
						tmpY = arg0.getY() + deltaY;
					} else if (isMoving2) {
						tmpX2 = arg0.getX() + deltaX2;
						tmpY2 = arg0.getY() + deltaY2;
					}
					double distance = Math.sqrt((tmpX - tmpX2) * (tmpX - tmpX2) + (tmpY - tmpY2) * (tmpY - tmpY2));
					if (distance >= 70) {
						x = tmpX;
						y = tmpY;
						x2 = tmpX2;
						y2 = tmpY2;
						repaint();
					}
				}
			});
		}
		
		private double getLenght(MouseEvent arg0, int x, int y){
			return Math.sqrt((arg0.getX() - x) * (arg0.getX() - x) + (arg0.getY() - y) * (arg0.getY() - y));
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (firstRun) {
				x = 20;
				y = 20;
				x2 = 120;
				y2 = 50;
				firstRun = false;
			}
			g.setColor(Color.YELLOW);
			g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
			g.setColor(Color.BLACK);
			g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
			
			g.setColor(Color.CYAN);
			g.fillOval(x2 - radius, y2 - radius, 2 * radius, 2 * radius);
			g.setColor(Color.BLACK);
			g.drawOval(x2 - radius, y2 - radius, 2 * radius, 2 * radius);
			
			g.setColor(Color.BLACK);
			g.drawLine(x, y, x2, y2);
			int distance = (int) (Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y)));
			g.drawString(distance + "", (x + x2) / 2, (y + y2) / 2);
		}
	}
}
