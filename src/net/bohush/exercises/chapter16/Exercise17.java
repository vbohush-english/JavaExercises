package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise17 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise17() {
		// Create two MovingMessagePanel for displaying two moving messages
		this.setLayout(new GridLayout(2, 1));
		add(new MovingMessagePanel("message 1 moving?"));
		add(new MovingMessagePanel("message 2 moving?"));
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise17 frame = new Exercise17();
		frame.setTitle("Exercise17");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(280, 100);
		frame.setVisible(true);
	}

	// Inner class: Displaying a moving message
	static class MovingMessagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private String message = "Welcome to Java";
		private int xCoordinate = 0;
		private int yCoordinate = 20;
		private Timer timer = new Timer(100, new TimerListener());

		public MovingMessagePanel(String message) {
			this.message = message;

			// Start timer for animation
			timer.start();

			// Control animation speed using mouse buttons
			this.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					timer.stop();
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					timer.start();
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (xCoordinate > getWidth()) {
				xCoordinate = -20;
			}
			xCoordinate += 5;
			g.drawString(message, xCoordinate, yCoordinate);
		}

		class TimerListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		}
	}
}