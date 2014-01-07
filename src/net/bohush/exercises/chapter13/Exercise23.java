package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise23 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise23() {
		add(new DrawCube());
	}
	
	public static void main(String[] args) {
		Exercise23 frame = new Exercise23();
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise23");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class DrawCube extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		//front side
		int[] x = {(int)(width * 0.1), (int)(width * 0.8), (int)(width * 0.8), (int)(width * 0.1)};
		int[] y = {(int)(height * 0.3), (int)(height * 0.3), (int)(height * 0.9), (int)(height * 0.9)};
		for (int i = 0; i < x.length - 1; i++)
			g.drawLine(x[i], y[i], x[i + 1], y[i + 1]);
		g.drawLine(x[0], y[0], x[3], y[3]);
		
		//beck side
		int x2[] = new int[x.length];
		int y2[] = new int[y.length];
		for (int i = 0; i < x.length; i++) {
			x2[i] = x[i] + (int)(width * 0.1);
			y2[i] = y[i] - (int)(height * 0.2);
		}
		for (int i = 0; i < x2.length - 1; i++)
			g.drawLine(x2[i], y2[i], x2[i + 1], y2[i + 1]);
		g.drawLine(x2[0], y2[0], x2[3], y2[3]);
		
		//other sides
		for (int i = 0; i < x.length; i++)
			g.drawLine(x[i], y[i], x2[i], y2[i]);
		
	}

}