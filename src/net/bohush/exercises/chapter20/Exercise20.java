package net.bohush.exercises.chapter20;

import java.awt.*;

import javax.swing.*;

public class Exercise20 extends JApplet{

	private static final long serialVersionUID = 1L;

	public Exercise20() {
		add(new CirclePanel());
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise20());
		frame.setTitle("Exercise20");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class CirclePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public CirclePanel() {

		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int diametr = Math.min(getWidth(), getHeight()) - 20;
			int x = (getWidth() - diametr) / 2;
			int y = (getHeight() - diametr) / 2;
			paintCircle(g, x, y, diametr);
		}
		
		private void paintCircle(Graphics g, int x, int y, int diametr) {
			if (diametr > 0) {
				g.drawOval(x, y, diametr, diametr);
				paintCircle(g, x + 10, y + 10, diametr - 20);
			}
		}

	}	
	
}
