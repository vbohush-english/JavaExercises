package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class Exercise39 extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> points = new ArrayList<>();
	private MousePanel mousePanel = new MousePanel();
	
	public Exercise39() {
		add(mousePanel);

	}

	public static void main(String[] args) {
		Exercise39 frame = new Exercise39();
		frame.setTitle("Exercise39");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class MousePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final int RADIUS = 10;
		public MousePanel() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (e.getX() > 160) {
							points.add(new Point(e.getX(), e.getY()));
							repaint();
						}
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						for (int i = 0; i < points.size(); i++) {
							if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS) {
								points.remove(i);
								repaint();
								break;
							}
						}
					}
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString("INSTRUCTIONS", 20, 20);
			g.drawString("Add:", 20, 40);
			g.drawString("Remove:", 20, 60);
			g.drawString("Left Click", 80, 40);
			g.drawString("Right CLick", 80, 60);
			g.drawRect(15, 5, 130, 60);
			if (points.size() > 0) {
				int minX = points.get(0).x;
				int minY = points.get(0).y;
				int maxX = points.get(0).x;
				int maxY = points.get(0).y;
				for (int i = 0; i < points.size(); i++) {
					if (points.get(i).y < minY) {
						minY = points.get(i).y;
					}
					if (points.get(i).x < minX) {
						minX = points.get(i).x;
					}
					if (points.get(i).y > maxY) {
						maxY = points.get(i).y;
					}
					if (points.get(i).x > maxX) {
						maxX = points.get(i).x;
					}
					g.drawOval(points.get(i).x - RADIUS, points.get(i).y - RADIUS, 2 * RADIUS, 2 * RADIUS);					
				}
				
				g.drawRect(minX - RADIUS, minY - RADIUS, maxX - minX + 2 * RADIUS, maxY - minY + 2 * RADIUS);
			}
			

		}
		
		private double getLenght(Point p1, Point p2){
			return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
		}
	}

}