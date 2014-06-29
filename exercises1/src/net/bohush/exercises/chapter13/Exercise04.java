package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise04 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise04() {
		add(new MultiplicationTable());
	}
	
	public static void main(String[] args) {
		Exercise04 frame = new Exercise04();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise04");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class MultiplicationTable extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("SansSerif", Font.BOLD, 20));
		g.drawString("Multiplication Table", 65, 20);
		g.setFont(new Font("SansSerif", Font.BOLD, 14));
		for (int i = 0; i < 9; i++) {
			g.drawString((i + 1) + "", 60 + i * 30, 50);
			g.drawString((i + 1) + "", 30, 80 + i * 30);
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				g.drawString(((i + 1) * (j + 1)) + "", 60 + i * 30, 80 + j * 30);
			}
		}
		g.drawRect(50, 60, 270, 270);
	}

}