package net.bohush.exercises.chapter20;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise39 extends JApplet {

	private static final long serialVersionUID = 1L;
	private TreePanel treePanel = new TreePanel();

	public Exercise39() {
		JButton jButton1 = new JButton("-");
		JButton jButton2 = new JButton("+");
		JPanel panel = new JPanel();
		panel.add(jButton1);
		panel.add(jButton2);

		add(treePanel);
		add(panel, BorderLayout.SOUTH);


		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				treePanel.downOrder();
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				treePanel.upOrder();
			}
		});
	}

	static class TreePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int order = 0;
		private Point p1;
		private Point p2;
		private int deltaX1;
		private int deltaY1;
		private int deltaX2;
		private int deltaY2;

		private boolean firstRun = true;
		
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

		public TreePanel() {
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {

				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					deltaX1 = e.getX() - p1.x;
					deltaY1 = e.getY() - p1.y;
					deltaX2 = e.getX() - p2.x;
					deltaY2 = e.getY() - p2.y;
					repaint();
				}
			});
			
			addMouseMotionListener(new MouseMotionAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) {
					p1.x = e.getX() - deltaX1;
					p1.y = e.getY() - deltaY1;
					p2.x = e.getX() - deltaX2;
					p2.y = e.getY() - deltaY2;
					repaint();
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			if (firstRun) {
				p1 = new Point(getWidth() / 2, getHeight());
				p2 = new Point(getWidth() / 2, getHeight() / 2);
				firstRun = false;
			}
			
			displayTree(g, order, p1, p2);
		}

		private static void displayTree(Graphics g, int order, Point p1, Point p2) {
			g.setColor(Color.BLACK);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			if (order != 0) {
				int angle = getAngle(p2, p1);
				double size = Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y)) / 2;
				
				Point p3 = new Point();
				p3.x = (int) (p2.x + size * Math.cos((angle + 30) * 2 * (Math.PI / 360)));
				p3.y = (int) (p2.y - size * Math.sin((angle + 30) * 2 * (Math.PI / 360)));

				Point p4 = new Point();
				p4.x = (int) (p2.x + size * Math.cos((angle + 330) * 2 * (Math.PI / 360)));
				p4.y = (int) (p2.y - size * Math.sin((angle + 330) * 2 * (Math.PI / 360)));
				
				displayTree(g, order - 1, p2, p3);
				displayTree(g, order - 1, p2, p4);
			}
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
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise39");
		Exercise39 applet = new Exercise39();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}