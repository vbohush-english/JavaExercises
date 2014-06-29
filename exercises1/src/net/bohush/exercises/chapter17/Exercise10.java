package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise10 extends JFrame {
	private JTextField jtfRed = new JTextField("77", 3);
	private JTextField jtfBlue = new JTextField("58", 3);
	private JTextField jtfGreen = new JTextField("159", 3);
	private MandelbrotCanvas mandelbrotCanvas = new MandelbrotCanvas();
	
	private static final long serialVersionUID = 1L;

	public Exercise10() {
		JPanel controlPanel = new JPanel();
		JLabel jlblRed = new JLabel("Red factor: ");
		JLabel jlblBlue = new JLabel("Blue factor: ");
		JLabel jlblGreen = new JLabel("Green factor: ");
		try {
			jtfRed.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					mandelbrotCanvas.setRedFactors(Integer.parseInt(jtfRed.getText()));
				}
			});
			jtfBlue.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					mandelbrotCanvas.setBlueFactors(Integer.parseInt(jtfBlue.getText()));			
				}
			});
			jtfGreen.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					mandelbrotCanvas.setGreenFactors(Integer.parseInt(jtfGreen.getText()));			
				}
			});
		} catch (NumberFormatException e) {
		}
		controlPanel.add(jlblRed);
		controlPanel.add(jtfRed);
		controlPanel.add(jlblBlue);
		controlPanel.add(jtfBlue);
		controlPanel.add(jlblGreen);
		controlPanel.add(jtfGreen);
		add(controlPanel, BorderLayout.SOUTH);		
		add(mandelbrotCanvas, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		Exercise10 frame = new Exercise10();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(450, 450);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setTitle("Exercise10");
		frame.setVisible(true);
	}

	class Complex {
		private double a;
		private double b;

		public double getRealPart() {
			return a;
		}

		public void setRealPart(double a) {
			this.a = a;
		}

		public double getImaginaryPart() {
			return b;
		}

		public void setImaginaryPart(double b) {
			this.b = b;
		}

		public Complex(double a, double b) {
			super();
			this.a = a;
			this.b = b;
		}

		public Complex(double a) {
			this(a, 0);
		}

		public Complex() {
			this(0, 0);
		}

		public Complex add(Complex c2) {
			return new Complex(a + c2.a, b + c2.b);
		}

		public Complex subtract(Complex c2) {
			return new Complex(a - c2.a, b - c2.b);
		}

		public Complex multiply(Complex c2) {
			return new Complex(a * c2.a - b * c2.b, b * c2.a + a * c2.b);
		}

		public Complex divide(Complex c2) {
			return new Complex((a * c2.a + b * c2.b)
					/ (c2.a * c2.a + c2.b * c2.b), (b * c2.a - a * c2.b)
					/ (c2.a * c2.a + c2.b * c2.b));
		}

		public double abs() {
			return Math.sqrt(a * a + b * b);
		}

		@Override
		public String toString() {
			return "(" + a + " + " + b + "i)";
		}

	}

	class MandelbrotCanvas extends JPanel {
		private int redFactor = 77;
		private int blueFactor = 58;
		private int greenFactor = 159;

		private static final long serialVersionUID = 1L;
		final static int COUNT_LIMIT = 60;

		public void setRedFactors(int redFactor) {
			this.redFactor = redFactor;
			repaint();
		}
		
		public void setBlueFactors(int blueFactor) {
			this.blueFactor = blueFactor;
			repaint();
		}
		
		public void setGreenFactors(int greenFactor) {
			this.greenFactor = greenFactor;
			repaint();
		}
		
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
						g.setColor(new Color(c * redFactor % 256, c * blueFactor % 256, c * greenFactor % 256));
					}
					g.drawRect((int) (x * 100) + 200, (int) (y * 100) + 200, 1,
							1);
				}
			}
		}

		/** Return the iteration count */
		int count(Complex c) {
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

}
