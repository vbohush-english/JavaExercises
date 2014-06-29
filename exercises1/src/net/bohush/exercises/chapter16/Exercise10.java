package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise10 extends JFrame {
	private MessagePanel panel = new MessagePanel();
	private static final long serialVersionUID = 1L;

	public Exercise10() {
		add(panel);
		panel.setFocusable(true);
	}

	public static void main(String[] args) {
		Exercise10 frame = new Exercise10();
		frame.setTitle("Exercise10");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class MessagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private String message = "";
		boolean isMessageFinished = false;

		public MessagePanel() {

			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					if (isMessageFinished) {
						isMessageFinished = false;
						message = "";
					}
					if (e.getKeyCode() != KeyEvent.VK_ENTER) {
						message += e.getKeyChar();
					} else {
						isMessageFinished = true;
					}
					repaint();
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (isMessageFinished) {
				g.drawString(message, 20, 20);
			}
		}
	}
}
