package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise17 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise17() {
		add(new Hangman());
	}
	
	public static void main(String[] args) {
		Exercise17 frame = new Exercise17();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise17");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class Hangman extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(70, 30, 200, 30);
		g.drawLine(70, 30, 70, 200);
		g.drawLine(200, 50, 200, 30);
		g.drawOval(180, 50, 40, 40);
		g.drawLine(200, 90, 200, 140);
		g.drawLine(170, 180, 200, 140);
		g.drawLine(230, 180, 200, 140);
		g.drawArc(20, 200, 100, 100, 45, 90);
		g.drawLine(215, 85, 250, 120);
		g.drawLine(185, 85, 150, 120);
	}

}