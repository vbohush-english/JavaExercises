package net.bohush.exercises.chapter18;

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
		
		private int x3;
		private int y3;
		private int deltaX3;
		private int deltaY3;
		
		private int x4;
		private int y4;
		private int deltaX4;
		private int deltaY4;
		boolean firstRun = true;
		
		boolean isMoving = false;
		boolean isMoving2 = false;
		boolean isMoving3 = false;
		boolean isMoving4 = false;
		
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
					} else if (isMoving4) {
						x4 = e.getX() + deltaX4;
						y4 = e.getY() + deltaY4;
						repaint();
						isMoving4 = false;
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
					} else if (getLenght(e, x4, y4) < radius) {
						deltaX4 = x4 - e.getX();
						deltaY4 = y4 - e.getY();
						isMoving4 = true;
					}			
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					isMoving = false;
					isMoving2 = false;
					isMoving3 = false;
					isMoving4 = false;
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
					} else if (isMoving4) {
						x4 = arg0.getX() + deltaX4;
						y4 = arg0.getY() + deltaY4;
					}
					repaint();
				}
				
				@Override
				public void mouseMoved(MouseEvent e) {
					if ((getLenght(e, x, y) <= radius) ||
							(getLenght(e, x2, y2) <= radius) ||
							(getLenght(e, x3, y3) <= radius) ||
							(getLenght(e, x4, y4) <= radius)) {
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
		
		private double getLenght(double lx1, double ly1, double lx2, double ly2){
			return Math.sqrt((lx1 - lx2) * (lx1 - lx2) + (ly1 - ly2) * (ly1 - ly2));
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (firstRun) {
				x = 20;
				y = 20;
				x2 = 56;
				y2 = 130;
				x3 = 100;
				y3 = 20;
				x4 = 16;
				y4 = 130;
				 
				firstRun = false;
			}
			g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
			g.fillOval(x2 - radius, y2 - radius, 2 * radius, 2 * radius);
			g.fillOval(x3 - radius, y3 - radius, 2 * radius, 2 * radius);
			g.fillOval(x4 - radius, y4 - radius, 2 * radius, 2 * radius);

			g.drawString("x1", x + radius, y);
			g.drawString("x2", x2 + radius, y2);
			g.drawString("x3", x3 + radius, y3);
			g.drawString("x4", x4 + radius, y4);
			
			g.drawLine(x, y, x2, y2);
			g.drawLine(x3, y3, x4, y4);
			
			double a = y - y2;
			double b = x - x2;
			double c = y3 - y4;
			double d = x3 - x4;
			double e = a * x - b * y;
			double f = c * x3 - d * y3;
			
			if ((a * d - b * c) != 0) {
				double x5 = (e * d - b * f) / (a * d - b * c);
				double y5 = -(a * f - e * c) / (a * d - b * c);
				if (getLenght(x5, y5, x, y) < getLenght(x, y, x2, y2) &&
					getLenght(x5, y5, x2, y2) < getLenght(x, y, x2, y2) &&
					getLenght(x5, y5, x3, y3) < getLenght(x3, y3, x4, y4) &&
					getLenght(x5, y5, x4, y4) < getLenght(x3, y3, x4, y4)) {
					g.fillOval((int)(x5) - radius, (int)(y5) - radius, 2 * radius, 2 * radius);
				}				
			}
			
		}
	}	
	
}
