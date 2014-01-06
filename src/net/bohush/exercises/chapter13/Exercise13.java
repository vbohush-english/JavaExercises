package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise13 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise13() {
		add(new DrawFace());
	}
	
	public static void main(String[] args) {
		Exercise13 frame = new Exercise13();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setTitle("Exercise13");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class DrawFace extends JPanel {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawOval((int)(width * 0.05), (int)(height * 0.05), (int)(width * 0.9), (int)(height * 0.9));
		g.drawArc((int)(width * 0.33), (int)(height * 0.55), (int)(width * 0.33), (int)(height * 0.33), 220, 100);
		g.drawOval((int)(width * 0.2), (int)(height * 0.3), (int)(width * 0.2), (int)(height * 0.1));
		g.fillOval((int)(width * 0.27), (int)(height * 0.32), (int)(width * 0.07), (int)(height * 0.07));
		g.drawOval((int)(width * 0.6), (int)(height * 0.3), (int)(width * 0.2), (int)(height * 0.1));
		g.fillOval((int)(width * 0.67), (int)(height * 0.32), (int)(width * 0.07), (int)(height * 0.07));
		g.drawLine((int)(width * 0.5), (int)(height * 0.33), (int)(width * 0.33), (int)(height * 0.66));
		g.drawLine((int)(width * 0.5), (int)(height * 0.33), (int)(width * 0.66), (int)(height * 0.66));
		g.drawLine((int)(width * 0.33), (int)(height * 0.66), (int)(width * 0.66), (int)(height * 0.66));
		
	}
}