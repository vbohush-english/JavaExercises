package net.bohush.exercises.chapter24;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise17 extends JApplet{
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> points = new ArrayList<>();
	private MousePanel mousePanel = new MousePanel();
	
	public Exercise17() {
		setLayout(new BorderLayout(5, 5));
		JPanel panel1 = new JPanel(new GridLayout(1, 5, 5, 5));
		panel1.setBorder(new LineBorder(Color.BLACK));
		panel1.add(new JLabel("INSTRUCTIONS"));
		panel1.add(new JLabel("Add: Left Click"));
		panel1.add(new JLabel("Remove: Right Click"));
		add(panel1, BorderLayout.NORTH);
		add(mousePanel, BorderLayout.CENTER);		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise17());
		frame.setTitle("Exercise17");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	
	class MousePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final int RADIUS = 5;
		public MousePanel() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						points.add(new Point(e.getX(), e.getY()));
						repaint();
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						for (int i = 0; i < points.size(); i++) {
							if (Pair.distance(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS) {
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
			for (Point myPoint : points) {
				g.drawOval((int)(myPoint.x - RADIUS), (int)(myPoint.y - RADIUS), 2 * RADIUS, 2 * RADIUS);	
			}
			if(points.size() > 1) {
				Pair pair = Pair.getClosestPair(points);
				g.drawLine((int)(pair.getP1().getX()), (int)(pair.getP1().getY()), (int)(pair.getP2().getX()), (int)(pair.getP2().getY()));
			}

		}
		
	}
	
	
	static class Pair {
		private Point p1;
		private Point p2;
		public Point getP1() {
			return p1;
		}
		public void setP1(Point p1) {
			this.p1 = p1;
		}
		public Point getP2() {
			return p2;
		}
		public void setP2(Point p2) {
			this.p2 = p2;
		}
		public Pair(Point p1, Point p2) {
			super();
			this.p1 = p1;
			this.p2 = p2;
		}
		public double getDistance() {
			return distance(p1, p2);
		}
		
		public static double distance(Point p1, Point p2) {
			return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
			
		public static double distance(double x1, double y1, double x2, double y2) {
			return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		}
		
		public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY) {
			if(low >= high) {
				return null;
			} else if (low + 1 == high) {
				return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
			}
			
			int halfSize = (low + high) / 2;
			Pair p1 = distance(pointsOrderedOnX, low, halfSize, pointsOrderedOnY);
			Pair p2 = distance(pointsOrderedOnX, halfSize + 1, high, pointsOrderedOnY);
			
			double distance = 0;
			Pair p = null;

			if (p1 == null && p2 == null) {
				distance = Double.MAX_VALUE;
			} else if (p1 == null) {
				distance = p2.getDistance();
				p = p2;
			} else if (p2 == null) {
				distance = p1.getDistance();
				p = p1;
			} else {
				distance = Math.min(p1.getDistance(), p2.getDistance());
				p = ((p1.getDistance() <= p2.getDistance())? p1 : p2);
			}

			ArrayList<Point> stripL = new ArrayList<Point>();
			ArrayList<Point> stripR = new ArrayList<Point>();
			for (int i = 0; i < pointsOrderedOnY.length; i++) {
				if ((pointsOrderedOnY[i].getX() <= pointsOrderedOnX[halfSize].getX())&&
						(pointsOrderedOnY[i].getX() >= pointsOrderedOnX[halfSize].getX() - distance)) {
					stripL.add(pointsOrderedOnY[i]);
				} else {
					stripR.add(pointsOrderedOnY[i]);
				}
			}

			
			double d3 = distance;
			int r = 0;
			for (int i = 0; i < stripL.size(); i++) {
				while (r < stripR.size() && stripL.get(i).getY() > stripR.get(r).getY() + distance) {
					r++;
				}

				int r1 = r;
				while (r1 < stripR.size() && stripR.get(r1).getY() <= stripL.get(i).getY() + distance) {
					if (d3 > distance(stripL.get(i), stripR.get(r1))) {
						d3 = distance(stripL.get(i), stripR.get(r1));
						p.p1 = stripL.get(i);
						p.p2 = stripR.get(r1);
					}
					r1++;
				}
			}

			return p;
		}
		
		public static Pair getClosestPair(ArrayList<Point> points) {
			Point[] points2 = new Point[points.size()];
			points.toArray(points2);
			return getClosestPair(points2);
		}
		
		public static Pair getClosestPair(Point[] points) {
			java.util.Arrays.sort(points);
			Point[] pointsOrderedOnY = points.clone();
			java.util.Arrays.sort(pointsOrderedOnY, new CompareY());
			return distance(points, 0, points.length - 1, pointsOrderedOnY);
		}
		
		public static Pair getClosestPair(double[][] points) {
			Point[] points2 = new Point[points.length];
			for (int i = 0; i < points.length; i++) {
				points2[i] = new Point(points[i][0], points[i][1]);
			}
			return getClosestPair(points2);
		}
	}
	
	static class Point implements Comparable<Point> {
		private double x;
		private double y;		
		
		public Point() {
			this(0, 0);
		}	
		
		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (x > o.x) {
				return 1;
			} else if (x < o.x) {
				return -1;
			} else {
				if (y > o.y) {
					return 1;
				} else if (y < o.y) {
					return -1;
				} else {
					return 0;
				}
			}
		}		
		
		@Override
		public String toString() {
			return "x: " + x + ",\ty: " + y;
		}
	}

	static class CompareY implements Comparator<Point> {
		
		@Override
		public int compare(Point o1, Point o2) {
			if (o1.getY() > o2.getY()) {
				return 1;
			} else if (o1.getY() < o2.getY()) {
				return -1;
			} else {
				if (o1.getX() > o2.getX()) {
					return 1;
				} else if (o1.getX() < o2.getX()) {
					return -1;
				} else {
					return 0;
				}
			}
		}	

	}
}