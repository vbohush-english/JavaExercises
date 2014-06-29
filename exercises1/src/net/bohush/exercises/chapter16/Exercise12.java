package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise12 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise12() {
		setLayout(new GridLayout(2, 2, 5, 5));
		for (int i = 0; i < 4; i++) {
			int speed = 1 + (int) (Math.random() * 100);
			int step = 1 + (int) (Math.random() * 10);
			add(new Fun(speed, step));
		}
	}

	public static void main(String[] args) {
		Exercise12 frame = new Exercise12();
		frame.setSize(500, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise12");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class Fun extends JPanel {
		private Timer timer;
		private static final long serialVersionUID = 1L;
		private Color color;
		int arc = 0;
		int step;

		public Fun(int delay, int step) {
			color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
			this.step = step;
			timer = new Timer(delay, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();
				}

			});
			timer.start();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int width = getWidth();
			int height = getHeight();
			int diametr = (int) (Math.min(width, height) * 0.8);
			g.setColor(color);
			g.fillArc((width - diametr) / 2, (height - diametr) / 2, diametr, diametr, arc, 30);
			g.fillArc((width - diametr) / 2, (height - diametr) / 2, diametr, diametr, arc + 90, 30);
			g.fillArc((width - diametr) / 2, (height - diametr) / 2, diametr, diametr, arc + 180, 30);
			g.fillArc((width - diametr) / 2, (height - diametr) / 2, diametr, diametr, arc + 270, 30);
			g.drawOval((width - diametr) / 2 - (int) (diametr * 0.05), (height - diametr) / 2 - (int) (diametr * 0.05),	(int) (diametr * 1.1), (int) (diametr * 1.1));
			arc += step;
		}

	}

}