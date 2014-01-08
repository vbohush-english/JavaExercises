package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise29 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise29() {
		add(new DrawCircles());
	}
	
	public static void main(String[] args) {
		Exercise29 frame = new Exercise29();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise29");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class DrawCircles extends JPanel {

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
		drawCircles(x1, y1, x2, y2, g);
	}

	public static void drawCircles(int x1, int y1, int x2, int y2, Graphics g) {
		g.setColor(Color.GREEN);
		int radius = 15;
		g.fillOval(x1 - radius, y1 - radius, radius * 2, radius * 2);
		g.fillOval(x2 - radius, y2 - radius, radius * 2, radius * 2);
		g.setColor(Color.RED);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(Color.BLUE);
		g.drawString("" + (int)(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))), (x2 + x1) / 2, (y2 + y1) / 2);
		
	}
}