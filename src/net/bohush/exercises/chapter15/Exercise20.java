package net.bohush.exercises.chapter15;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise20 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise20() {
		add(new MandelbrotCanvas());
	}
	
	public static void main(String[] args) {
		Exercise20 frame = new Exercise20();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Exercise20");
		frame.setVisible(true);
	}

}

class MandelbrotCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	final static int COUNT_LIMIT = 60;

	@Override
	/** Paint a Mandelbrot image */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (double x = -2.0; x < 2.0; x += 0.01) {
			for (double y = -2.0; y < 2.0; y += 0.01) {
				int c = count(new Complex(x, y));
				if (c == COUNT_LIMIT) {
					g.setColor(Color.BLACK);
				} else {
					g.setColor(new Color(c * 77 % 256, c * 58 % 256, c * 159 % 256));
				}
				g.drawRect((int) (x * 100) + 200, (int) (y * 100) + 200, 1, 1);
			}
		}
	}

	/** Return the iteration count */
	static int count(Complex c) {
		Complex z = new Complex(0, 0); // z0

		for (int i = 0; i < COUNT_LIMIT; i++) {
			z = z.multiply(z).add(c); // Get z1, z2, . . .
			if (z.abs() > 2) {
				return i; // The sequence is unbounded
			}
		}

		return COUNT_LIMIT; // Indicate a bounded sequence
	}
}