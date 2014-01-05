package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise10 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise10() {
		add(new Cilinder());
	}
	
	public static void main(String[] args) {
		Exercise10 frame = new Exercise10();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise10");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class Cilinder extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawLine((int)(width * 0.2), (int)(height * 0.2), (int)(width * 0.2), (int)(height * 0.8));
		g.drawLine((int)(width * 0.8), (int)(height * 0.2), (int)(width * 0.8), (int)(height * 0.8));
		g.drawOval((int)(width * 0.2), (int)(height * 0.1), (int)(width * 0.6), (int)(height * 0.2));
		g.drawArc((int)(width * 0.2), (int)(height * 0.7), (int)(width * 0.6), (int)(height * 0.2), 180, 180);
		g.drawArc((int)(width * 0.2), (int)(height * 0.7), (int)(width * 0.6), (int)(height * 0.2), 0, 20);
		g.drawArc((int)(width * 0.2), (int)(height * 0.7), (int)(width * 0.6), (int)(height * 0.2), 40, 20);
		g.drawArc((int)(width * 0.2), (int)(height * 0.7), (int)(width * 0.6), (int)(height * 0.2), 80, 20);
		g.drawArc((int)(width * 0.2), (int)(height * 0.7), (int)(width * 0.6), (int)(height * 0.2), 120, 20);
		g.drawArc((int)(width * 0.2), (int)(height * 0.7), (int)(width * 0.6), (int)(height * 0.2), 160, 20);
	}

}