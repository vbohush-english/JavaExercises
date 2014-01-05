package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise09 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise09() {
		setLayout(new GridLayout(2, 2, 5, 5));
		add(new Fun());
		add(new Fun());
		add(new Fun());
		add(new Fun());
	}
	
	public static void main(String[] args) {
		Exercise09 frame = new Exercise09();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise09");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class Fun extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.setColor(getColor());
		g.fillArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 0, 30);
		g.setColor(getColor());
		g.fillArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 90, 30);
		g.setColor(getColor());
		g.fillArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 180, 30);
		g.setColor(getColor());
		g.fillArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 270, 30);
		g.setColor(getColor());
		g.drawOval((int) (0.05 * width), (int) (0.05 * height), (int) (0.9 * width), (int) (0.9 * height));
	}
	
	private static Color getColor() {
		return new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
	}

}