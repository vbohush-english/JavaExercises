package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise24 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise24() {
		add(new BeanMachine());
	}
	
	public static void main(String[] args) {
		Exercise24 frame = new Exercise24();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise24");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class BeanMachine extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int size = Math.min(getWidth(), getHeight());
		int startX = (getWidth() - size) / 2;
		int startY = (getHeight() - size) / 2;
		
		g.setColor(Color.BLUE);
		
		g.drawLine(startX + 10, startY + size - 10, startX + size - 10, startY + size - 10);
		
		g.drawLine(startX + 10, startY + size - 10, startX + 10, startY + (int)(size * 0.8));
		g.drawLine(startX + size - 10, startY + (int)(size * 0.8), startX + size - 10, startY + size - 10);
		
		g.drawLine(startX + (int)(size * 0.4), startY + (int)(size * 0.2), startX + 10, startY + (int)(size * 0.8));
		g.drawLine(startX + size - 10, startY + (int)(size * 0.8), startX + (int)(size * 0.6), startY + (int)(size * 0.2));
		
		g.drawLine(startX + (int)(size * 0.4), startY + (int)(size * 0.2), startX + (int)(size * 0.4), startY + 10);
		g.drawLine(startX + (int)(size * 0.6), startY + 10, startX + (int)(size * 0.6), startY + (int)(size * 0.2));
		
		final int BEEN_COUNT = 8;
		
		double step = (size - 20.0) / BEEN_COUNT;
		for (int i = 1; i < BEEN_COUNT; i++) {
			int nextX = (int)(i * step);
			g.drawLine(startX + 10 + nextX, startY + (int)(size * 0.8), startX + 10 + nextX, startY + size - 10);	
		}
		
		
		g.setColor(Color.RED);
		int nextBX = startX;
		int nextBY = startY;
		final int OVAL_DIAMETR = 14;
		for (int j = (BEEN_COUNT - 1); j >= 1; j--) {
			for (int i = 1; i <= j; i++) {
				int nextX = (int)(i * step);
				g.fillOval(nextBX + 10 + nextX - OVAL_DIAMETR / 2,
						   nextBY + (int)(size * 0.8) - OVAL_DIAMETR / 2,
						   OVAL_DIAMETR,
						   OVAL_DIAMETR);
			}
			nextBY = startY - (int)((BEEN_COUNT - j) * ((size * 0.6)) / (BEEN_COUNT - 2));
			nextBX = startX + (int)((BEEN_COUNT - j) * (step / 2.0));
		}

	}
	
}