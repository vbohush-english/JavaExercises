package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise36 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise36() {
		add(new CirclePanel());
	}
	
	public static void main(String[] args) {
		Exercise36 frame = new Exercise36();
		frame.setTitle("Exercise36");
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
		
		private int x3;
		private int y3;
		private int deltaX3;
		private int deltaY3;
		
		boolean firstRun = true;
		
		boolean isMoving = false;
		boolean isMoving2 = false;
		boolean isMoving3 = false;
		
		private Cursor normalCurcor = new Cursor(Cursor.DEFAULT_CURSOR);
		private Cursor crossCurcor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		
		private int radius = 5;

		public CirclePanel() {
			setCursor(normalCurcor);
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
					} else if (isMoving3) {
						x3 = e.getX() + deltaX3;
						y3 = e.getY() + deltaY3;
						repaint();
						isMoving3 = false;
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
					} else if (getLenght(e, x3, y3) < radius) {
						deltaX3 = x3 - e.getX();
						deltaY3 = y3 - e.getY();
						isMoving3 = true;
					}		
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					isMoving = false;
					isMoving2 = false;
					isMoving3 = false;
					repaint();
				}
			});
			
			addMouseMotionListener(new MouseMotionListener() {
				@Override
				public void mouseDragged(MouseEvent arg0) {
					if (isMoving) {
						x = arg0.getX() + deltaX;
						y = arg0.getY() + deltaY;
					} else if (isMoving2) {
						x2 = arg0.getX() + deltaX2;
						y2 = arg0.getY() + deltaY2;
					} else if (isMoving3) {
						x3 = arg0.getX() + deltaX3;
						y3 = arg0.getY() + deltaY3;
					}
					repaint();
				}
				
				@Override
				public void mouseMoved(MouseEvent e) {
					if ((getLenght(e, x, y) <= radius) ||
							(getLenght(e, x2, y2) <= radius) ||
							(getLenght(e, x3, y3) <= radius)) {
						if (getCursor() == normalCurcor) {
							setCursor(crossCurcor);	
						}						
					} else {
						if (getCursor() == crossCurcor) {
							setCursor(normalCurcor);	
						}
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
				x3 = 100;
				y3 = 100;
				firstRun = false;
			}
			g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
			
			g.fillOval(x2 - radius, y2 - radius, 2 * radius, 2 * radius);

			g.fillOval(x3 - radius, y3 - radius, 2 * radius, 2 * radius);

			g.drawLine(x, y, x2, y2);
			g.drawLine(x2, y2, x3, y3);
			g.drawLine(x, y, x3, y3);
			
			double a = Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
			double b = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
			double c = Math.sqrt((x3 - x) * (x3 - x) + (y3 - y) * (y3 - y));
			
			int A = (int)(Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c))));
			int B = (int)(Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c))));
			int C = 180 - A - B;

			g.drawString(A + "", x3 + radius, y3);
			g.drawString(B + "", x + radius, y);
			g.drawString(C + "", x2 + radius, y2);
		}
	}
}
