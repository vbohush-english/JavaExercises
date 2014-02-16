package net.bohush.exercises.chapter20;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise35 extends JApplet {

	private static final long serialVersionUID = 1L;
	private SierpinskiTrianglePanel trianglePanel = new SierpinskiTrianglePanel();

	public Exercise35() {
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

			// Select three points in proportion to the panel size
			Point p1 = new Point(getWidth() / 2, 10);
			Point p2 = new Point(10, getHeight() - 10);
			Point p3 = new Point(getWidth() - 10, getHeight() - 10);

			displayTriangles(g, order, p1, p2, p3);
		}

		private static void displayTriangles(Graphics g, int order, Point p1,
				Point p2, Point p3) {
			if (order == 0) {
				// Draw a triangle to connect three points
				g.drawLine(p1.x, p1.y, p2.x, p2.y);
				g.drawLine(p1.x, p1.y, p3.x, p3.y);
				g.drawLine(p2.x, p2.y, p3.x, p3.y);
			} else {
				// Get the midpoint on each edge in the triangle
				Point p12 = midpoint(p1, p2);
				Point p23 = midpoint(p2, p3);
				Point p31 = midpoint(p3, p1);

				// Recursively display three triangles
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
		JFrame frame = new JFrame("Exercise35");
		Exercise35 applet = new Exercise35();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}