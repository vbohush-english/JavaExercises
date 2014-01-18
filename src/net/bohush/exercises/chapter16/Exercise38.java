package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise38 extends JFrame{
	private JButton jbtRandom = new JButton("Draw a Random Arrow Line");
	private DrawArrow drawArrow = new DrawArrow();
	
	private static final long serialVersionUID = 1L;

	public Exercise38() {
		JPanel panel1 = new JPanel();
		panel1.add(jbtRandom);
		add(panel1, BorderLayout.SOUTH);
		add(drawArrow, BorderLayout.CENTER);
		jbtRandom.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawArrow.redrawArrow();
			}
		});
	}
	
	public static void main(String[] args) {
		Exercise38 frame = new Exercise38();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise28");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	static class DrawArrow extends JPanel {
		private int x1;
		private int x2;
		private int y1;
		private int y2;
		private boolean drawLine = false;
		private static final long serialVersionUID = 1L;
		
		public void redrawArrow() {
			int width = getWidth();
			int height = getHeight();
			x1 = (int)(Math.random() * width);
			x2 = (int)(Math.random() * width);
			y1 = (int)(Math.random() * height);
			y2 = (int)(Math.random() * height);
			drawLine = true;
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (drawLine) {
				drawArrowLine(x1, y1, x2, y2, g);
				drawLine = true;
			}
		}

		public static void drawArrowLine(int x1, int y1, int x2, int y2, Graphics g) {
			g.drawLine(x1, y1, x2, y2);
			
			int angle = 0;
			int minDelta = Integer.MAX_VALUE;
			double radius = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
			for (int i = 0; i < 360; i++) {
				int nextX = (int) (x2 + radius * Math.cos(i * 2 * (Math.PI / 360)));
				int nextY = (int) (y2 - radius * Math.sin(i * 2 * (Math.PI / 360)));
				int nextDelta = Math.abs(nextX - x1) + Math.abs(nextY - y1);
				if (nextDelta < minDelta) {
					minDelta = nextDelta;
					angle = i;
				}
			}
			radius = 20;
			g.drawLine((int) (x2 + radius * Math.cos((angle - 30) * 2 * (Math.PI / 360))), (int) (y2 - radius * Math.sin((angle - 30) * 2 * (Math.PI / 360))), x2, y2);
			g.drawLine((int) (x2 + radius * Math.cos((angle + 30) * 2 * (Math.PI / 360))), (int) (y2 - radius * Math.sin((angle + 30) * 2 * (Math.PI / 360))), x2, y2);
		}
	}

}