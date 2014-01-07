package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise22 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise22() {
		add(new DrawStop());
	}

	public static void main(String[] args) {
		Exercise22 frame = new Exercise22();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise22");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class DrawStop extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;
		int radius = (int) (Math.min(getWidth(), getHeight()) * 0.4);

		Polygon polygon = new Polygon();

		for (int i = 1; i <= 16; i += 2)
		polygon.addPoint((int) (xCenter + radius * Math.cos(i * 2 * (Math.PI / 16))),
						 (int) (yCenter - radius * Math.sin(i * 2 * (Math.PI / 16))));


		g.setColor(Color.RED);
		g.fillPolygon(polygon);
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.BOLD, 60));
		FontMetrics fm = g.getFontMetrics();
		g.drawString("STOP", (getWidth() - fm.stringWidth("STOP")) / 2,
				(getHeight() - fm.getAscent()) / 2 + fm.getAscent());
	}

}