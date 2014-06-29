package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise01 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise01() {
		add(new FourLines());
	}
	
	public static void main(String[] args) {
		Exercise01 frame = new Exercise01();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise01");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class FourLines extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.RED);
		g.drawLine(width / 3, 0, width / 3, height);
		g.drawLine(width / 3 * 2, 0, width / 3 * 2, height);
		g.setColor(Color.BLUE);
		g.drawLine(0, height / 3, width, height / 3);
		g.drawLine(0, height / 3 * 2, width, height / 3 * 2);
	}

}