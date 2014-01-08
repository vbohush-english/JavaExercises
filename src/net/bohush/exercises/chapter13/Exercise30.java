package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise30 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise30() {
		add(new DrawCircles2());
	}
	
	public static void main(String[] args) {
		Exercise30 frame = new Exercise30();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise30");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class DrawCircles2 extends JPanel {

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

		g.setColor(Color.MAGENTA);
		g.drawLine(x1, y1, x2, y2);
				
		int radius = 15;
				
		g.setColor(getBackground());
		g.fillOval(x1 - radius, y1 - radius, radius * 2, radius * 2);
		g.fillOval(x2 - radius, y2 - radius, radius * 2, radius * 2);
		g.setColor(Color.BLUE);
		g.drawOval(x1 - radius, y1 - radius, radius * 2, radius * 2);
		g.drawOval(x2 - radius, y2 - radius, radius * 2, radius * 2);
		
		g.setColor(Color.RED);
		g.drawString("1", x1 - 5, y1 + 5);
		g.drawString("2", x2 - 5, y2 + 5);
	}

}