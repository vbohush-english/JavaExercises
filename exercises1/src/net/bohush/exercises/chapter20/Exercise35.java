package net.bohush.exercises.chapter20;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise35 extends JApplet {

	private static final long serialVersionUID = 1L;
	private HTreePanel hTreePanel = new HTreePanel();

	public Exercise35() {
		JButton jButton1 = new JButton("-");
		JButton jButton2 = new JButton("+");
		JPanel panel = new JPanel();
		panel.add(jButton1);
		panel.add(jButton2);

		add(hTreePanel);
		add(panel, BorderLayout.SOUTH);


		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				hTreePanel.downOrder();
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				hTreePanel.upOrder();
			}
		});
	}

	static class HTreePanel extends JPanel {
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
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			Point p = new Point(getWidth() / 2, getHeight() / 2);
			int size = Math.min(getWidth(), getHeight()) / 4;
			displayTree(g, order, p, size);
		}

		private static void displayTree(Graphics g, int order, Point p, int size) {
			g.setColor(Color.BLACK);
			Point p1 = new Point(p.x - size, p.y - size);
			Point p2 = new Point(p.x - size, p.y + size);
			Point p3 = new Point(p.x + size, p.y - size);
			Point p4 = new Point(p.x + size, p.y + size);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			g.drawLine(p3.x, p3.y, p4.x, p4.y);
			Point p5 = new Point(p.x - size, p.y);
			Point p6 = new Point(p.x + size, p.y);
			g.drawLine(p5.x, p5.y, p6.x, p6.y);
			if (order != 0) {
				displayTree(g, order - 1, p1, size / 2);
				displayTree(g, order - 1, p2, size / 2);
				displayTree(g, order - 1, p3, size / 2);
				displayTree(g, order - 1, p4, size / 2);
			}
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