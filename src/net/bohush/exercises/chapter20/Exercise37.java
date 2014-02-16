package net.bohush.exercises.chapter20;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise37 extends JApplet {

	private static final long serialVersionUID = 1L;
	private SierpinskiTrianglePanel trianglePanel = new SierpinskiTrianglePanel();

	public Exercise37() {
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

		/** Set a new order */
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

			Point p1 = new Point(getWidth() / 2, 10);
			Point p2 = new Point(10, getHeight() - 10);
			Point p3 = new Point(getWidth() - 10, getHeight() - 10);
			g.setColor(Color.BLACK);
			displayTriangles(g, order, p1, p2, p3);
		}

		private static void displayTriangles(Graphics g, int order, Point p1, Point p2, Point p3) {
			if (order == 0) {
				Polygon p = new Polygon();
				p.addPoint(p1.x, p1.y);
				p.addPoint(p2.x, p2.y);
				p.addPoint(p3.x, p3.y);
				g.fillPolygon(p);
			} else {
				Point p12 = midpoint(p1, p2);
				Point p23 = midpoint(p2, p3);
				Point p31 = midpoint(p3, p1);

				displayTriangles(g, order - 1, p1, p12, p31);
				displayTriangles(g, order - 1, p12, p2, p23);
				displayTriangles(g, order - 1, p31, p23, p3);
			}
		}

		private static Point midpoint(Point p1, Point p2) {
			return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise37");
		Exercise37 applet = new Exercise37();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}