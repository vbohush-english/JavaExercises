package net.bohush.exercises.chapter20;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Exercise36 extends JApplet {

	private static final long serialVersionUID = 1L;
	private HilbertCurvePanel hilbertCurvePanel = new HilbertCurvePanel();

	public Exercise36() {
		JButton jButton1 = new JButton("-");
		JButton jButton2 = new JButton("+");
		JPanel panel = new JPanel();
		panel.add(jButton1);
		panel.add(jButton2);

		add(hilbertCurvePanel);
		add(panel, BorderLayout.SOUTH);


		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				hilbertCurvePanel.downOrder();
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				hilbertCurvePanel.upOrder();
			}
		});
	}

	static class HilbertCurvePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int order = 0;
		
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

			Point p = new Point(getWidth() / 2, getHeight() / 2);
			int size = Math.min(getWidth(), getHeight()) / 4;
			
			Point p1 = new Point(p.x - size, p.y - size);
			Point p2 = new Point(p.x - size, p.y + size);
			Point p3 = new Point(p.x + size, p.y + size);
			Point p4 = new Point(p.x + size, p.y - size);

			ArrayList<Point> points = new ArrayList<>();
			points.add(p1);
			points.add(p2);
			points.add(p3);
			points.add(p4);
			ArrayList<Integer> positions = new ArrayList<>();
			positions.add(0);
			positions.add(1);
			positions.add(1);
			positions.add(2);
			displayCurve(g, order, points, positions, size/2);
		}

		private static void displayCurve(Graphics g, int order, ArrayList<Point> points, ArrayList<Integer> positions, int size) {
			
			if (order == 0) {
				for (int i = 0; i < points.size() - 1; i++) {
					g.drawLine(points.get(i).x, points.get(i).y,points.get(i + 1).x, points.get(i + 1).y); 
				}
					
			} else {
				ArrayList<Point> newPoints = new ArrayList<>();
				ArrayList<Integer> newPositions = new ArrayList<>();
				for (int i = 0; i < points.size(); i++) {
					Point[] ps = new Point[4];
					int position = positions.get(i);
					ps[0] = new Point(points.get(i).x - size, points.get(i).y - size);
					ps[1] = new Point(points.get(i).x - size, points.get(i).y + size);
					ps[2] = new Point(points.get(i).x + size, points.get(i).y + size);
					ps[3] = new Point(points.get(i).x + size, points.get(i).y - size);
					switch (position) {
					case 0:
						newPoints.add(ps[0]); newPoints.add(ps[3]); newPoints.add(ps[2]); newPoints.add(ps[1]);
						newPositions.add(1); newPositions.add(0); newPositions.add(0); newPositions.add(3);
						break;
					case 1:
						newPoints.add(ps[0]); newPoints.add(ps[1]); newPoints.add(ps[2]); newPoints.add(ps[3]);
						newPositions.add(0); newPositions.add(1); newPositions.add(1); newPositions.add(2);
						break;
					case 2:
						newPoints.add(ps[2]); newPoints.add(ps[1]); newPoints.add(ps[0]); newPoints.add(ps[3]);
						newPositions.add(3); newPositions.add(2); newPositions.add(2); newPositions.add(1);
						break;
					case 3:
						newPoints.add(ps[2]); newPoints.add(ps[3]); newPoints.add(ps[0]); newPoints.add(ps[1]);
						newPositions.add(2); newPositions.add(3); newPositions.add(3); newPositions.add(0);
						break;
					}
				}
				displayCurve(g, order - 1, newPoints, newPositions, size/2);
			}
		}
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise36");
		Exercise36 applet = new Exercise36();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}