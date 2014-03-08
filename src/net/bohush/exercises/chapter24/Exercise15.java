package net.bohush.exercises.chapter24;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise15 extends JApplet{
	private static final long serialVersionUID = 1L;
	private ArrayList<MyPoint> points = new ArrayList<>();
	private MousePanel mousePanel = new MousePanel();
	
	public Exercise15() {
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
		frame.add(new Exercise15());
		frame.setTitle("Exercise15");
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
						points.add(new MyPoint(e.getX(), e.getY()));
						repaint();
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						for (int i = 0; i < points.size(); i++) {
							if (getSide(new MyPoint(e.getX(), e.getY()), points.get(i)) < RADIUS) {
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
			if(points.size() < 1) {
				return;
			}
			
			MyPoint h0 = points.get(0);
			for (int i = 1; i < points.size(); i++) {
				if(points.get(i).y > h0.y) {
					h0 = points.get(i);
				} else if(points.get(i).y == h0.y) {
					if(points.get(i).x > h0.x) {
						h0 = points.get(i);
					}
				}
			}
			for (MyPoint myPoint : points) {
				myPoint.setRightMostLowestPoint(h0);
			}
			
			Collections.sort(points);
			
			for (MyPoint myPoint : points) {
				g.fillOval((int)(myPoint.x - RADIUS), (int)(myPoint.y - RADIUS), 2 * RADIUS, 2 * RADIUS);	
			}
			for (int i = 0; i < points.size() - 1; i++) {
				g.drawLine((int)(points.get(i).x),(int)( points.get(i).y), (int)(points.get(i + 1).x), (int)(points.get(i + 1).y));				
			}
			if(points.size() > 1) {
				g.drawLine((int)(points.get(0).x),(int)( points.get(0).y), (int)(points.get(points.size() - 1).x), (int)(points.get(points.size() - 1).y));
			}

		}
		
	}
	

	
	private static class MyPoint implements Comparable<MyPoint> {
		double x, y;
		MyPoint rightMostLowestPoint;

		MyPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public void setRightMostLowestPoint(MyPoint p) {
			rightMostLowestPoint = p;
		}

		@Override
		public int compareTo(MyPoint o) {
			MyPoint virtualPoint = new MyPoint(rightMostLowestPoint.x + 1, rightMostLowestPoint.y);
			double a1 = getAngle(rightMostLowestPoint, virtualPoint, o);
			double a2 = getAngle(rightMostLowestPoint, virtualPoint, this);
			if(a1 > a2) {
				return -1;
			} else if(a2 > a1) {
				return 1;
			} else {
				double l1 = getSide(rightMostLowestPoint, o);
				double l2 = getSide(rightMostLowestPoint, this);
				if(l1 > l2) {
					return -1;
				} else if(l2 > l1) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
	
	public static double getAngle(MyPoint p1, MyPoint p2, MyPoint p3) {
		double a = getSide(p2, p3);
		double b = getSide(p1, p3);
		double c = getSide(p1, p2);
		return Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
	}
	
	public static double getSide(MyPoint p1, MyPoint p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	}

}