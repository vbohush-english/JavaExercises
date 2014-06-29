package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise05 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise05() {
		add(new TriangularPattern());
	}
	
	public static void main(String[] args) {
		Exercise05 frame = new Exercise05();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise05");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class TriangularPattern extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.setFont(new Font("SansSerif", Font.BOLD, 14));
		FontMetrics fm = g.getFontMetrics();
		int i = 1;
		String newLine = "";
		do {
			g.drawString(newLine, 0, i * fm.getHeight());
			newLine = "";
			for (int j = 1; j <= i; j++) {
				newLine += " " + j;
			}
			i++;
		} while ((fm.stringWidth(newLine) <= width) && (fm.getHeight() * i <= height));
		
	}

}