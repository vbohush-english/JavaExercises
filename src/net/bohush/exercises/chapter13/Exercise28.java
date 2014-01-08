package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise28 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise28() {
		add(new DrawArrow());
	}
	
	public static void main(String[] args) {
		Exercise28 frame = new Exercise28();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise28");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class DrawArrow extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int x1 = (int)(Math.random() * width);
		int x2 = (int)(Math.random() * width);
		int y1 = (int)(Math.random() * height);
		int y2 = (int)(Math.random() * height);
		drawArrowLine(x1, y1, x2, y2, g);
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