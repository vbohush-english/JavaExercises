package net.bohush.exercises.chapter20;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Exercise27 extends JApplet {

	private static final long serialVersionUID = 1L;
	private SierpinskiTrianglePanel trianglePanel = new SierpinskiTrianglePanel();

	public Exercise27() {
		JButton jButton1 = new JButton("-");
		JButton jButton2 = new JButton("+");
		JPanel panel = new JPanel();
		panel.add(jButton1);
		panel.add(jButton2);

		add(trianglePanel);
		add(panel, BorderLayout.SOUTH);


		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				trianglePanel.downOrder();
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				trianglePanel.upOrder();
			}
		});
	}

	static class SierpinskiTrianglePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int order = 0;

		public void setOrder(int order) {
			this.order = order;
			repaint();
		}
		
		public void upOrder() {
			order++;
			repaint();
		}
		
		public void downOrder() {
			order--;
			if (order < 0) {
				order = 0;
			}
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			int x = getWidth() / 2;
			int y = getHeight() / 2;
			Point center = new Point(x, y);
			int size = Math.min(getWidth(), getHeight()) / 2 - 10;
			
			Point p1 = new Point();
			p1.x = (int) (center.x + size * Math.cos(90 * 2 * (Math.PI / 360)));
			p1.y = (int) (center.y - size * Math.sin(90 * 2 * (Math.PI / 360)));
			
			Point p2 = new Point();
			p2.x = (int) (center.x + size * Math.cos(210 * 2 * (Math.PI / 360)));
			p2.y = (int) (center.y - size * Math.sin(210 * 2 * (Math.PI / 360)));
			
			Point p3 = new Point();
			p3.x = (int) (center.x + size * Math.cos(330 * 2 * (Math.PI / 360)));
			p3.y = (int) (center.y - size * Math.sin(330 * 2 * (Math.PI / 360)));

			ArrayList<Point> points = new ArrayList<>();
			points.add(p1);
			points.add(p2);
			points.add(p3);
			displayTriangles(g, order, points);
		}

		private static void displayTriangles(Graphics g, int order, ArrayList<Point> points) {
			
			if (order == 0) {
				for (int i = 0; i < points.size() - 1; i++) {
					g.drawLine(points.get(i).x, points.get(i).y,points.get(i + 1).x, points.get(i + 1).y); 
				}
				g.drawLine(points.get(0).x, points.get(0).y,points.get(points.size() - 1).x, points.get(points.size() - 1).y);
					
			} else {
				ArrayList<Point> newPoints = new ArrayList<>();
				for (int i = 0; i < points.size() - 1; i++) {
					newPoints.add(points.get(i));
					Point tmp1 = point1(points.get(i), points.get(i + 1));
					Point tmp3 = point2(points.get(i), points.get(i + 1));
					Point tmp2 = getMiddlePoint(points.get(i), tmp1, tmp3);
					newPoints.add(tmp1);
					newPoints.add(tmp2);
					newPoints.add(tmp3);
					 
				}
				newPoints.add(points.get(points.size() - 1));
				Point tmp1 = point1(points.get(points.size() - 1), points.get(0));
				Point tmp3 = point2(points.get(points.size() - 1), points.get(0));
				Point tmp2 = getMiddlePoint(points.get(points.size() - 1), tmp1, tmp3);
				newPoints.add(tmp1);
				newPoints.add(tmp2);
				newPoints.add(tmp3);

				displayTriangles(g, order - 1, newPoints);
			}
		}
		
		public static Point getMiddlePoint(Point p1, Point p2, Point p3) {
			int angle1 = getAngle(p1, p2);

			double size = Math.sqrt((p2.x - p3.x) * (p2.x - p3.x) + (p2.y - p3.y) * (p2.y - p3.y));

			int x = (int) (p2.x + size * Math.cos((angle1 + 120) * 2 * (Math.PI / 360)));
			int y = (int) (p2.y - size * Math.sin((angle1 + 120) * 2 * (Math.PI / 360)));
			
			return new Point(x, y);

		}
		
		public static int getAngle(Point p1, Point p2) {
			int angle = 0;
			int minDelta = Integer.MAX_VALUE;
			double size = Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
			for (int i = 0; i < 360; i++) {
				int nextX = (int) (p2.x + size * Math.cos(i * 2 * (Math.PI / 360)));
				int nextY = (int) (p2.y - size * Math.sin(i * 2 * (Math.PI / 360)));
				int nextDelta = Math.abs(nextX - p1.x) + Math.abs(nextY - p1.y);
				if (nextDelta < minDelta) {
					minDelta = nextDelta;
					angle = i;
				}
			}
			return angle;
		}

		private static Point point1(Point p1, Point p2) {
			return new Point(p1.x + (p2.x - p1.x) / 3, p1.y + (p2.y - p1.y) / 3);
		}
		
		private static Point point2(Point p1, Point p2) {
			return new Point(p1.x + (2 * (p2.x - p1.x)) / 3, p1.y + (2 * (p2.y - p1.y)) / 3);
		}
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise27");
		Exercise27 applet = new Exercise27();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}