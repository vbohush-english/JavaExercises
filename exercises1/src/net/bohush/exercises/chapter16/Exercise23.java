package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Exercise23 extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> points = new ArrayList<>();

	public Exercise23() {
		this.add(new MousePanel());
	}

	public static void main(String[] args) {
		Exercise23 frame = new Exercise23();
		frame.setTitle("Exercise23");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class MousePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public MousePanel() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					points.add(new Point(e.getX(), e.getY()));
					repaint();
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			final int DIAMETR = 4;
			for (int i = 0; i < points.size(); i++) {
				g.drawOval(points.get(i).x - DIAMETR / 2, points.get(i).y - DIAMETR / 2, DIAMETR, DIAMETR);
			}
			if (points.size() >= 2) {
				Point pointA = points.get(0);
				Point pointB = points.get(1);
				for (int i = 0; i < points.size() - 1; i++) {
					for (int j = i + 1; j < points.size(); j++) {
						if (getLenght(points.get(i), points.get(j)) < getLenght(pointA, pointB)) {
							pointA = points.get(i);
							pointB = points.get(j);
						}
					}
				}
				g.fillOval(pointA.x - DIAMETR / 2, pointA.y - DIAMETR / 2, DIAMETR, DIAMETR);
				g.fillOval(pointB.x - DIAMETR / 2, pointB.y - DIAMETR / 2, DIAMETR, DIAMETR);
			}
		}
		
		private double getLenght(Point p1, Point p2){
			return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
		}
	}

}