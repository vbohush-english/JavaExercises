package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise11 extends JFrame {
	private CharPanel panel = new CharPanel();
	private static final long serialVersionUID = 1L;

	public Exercise11() {
		add(panel);
		panel.setFocusable(true);
	}

	public static void main(String[] args) {
		Exercise11 frame = new Exercise11();
		frame.setTitle("Exercise11");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class CharPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private char character;
		private int xPos = 20;
		private int yPos = 20;

		public CharPanel() {

			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					character = e.getKeyChar();
					repaint();
				}
			});
			
			addMouseMotionListener(new MouseMotionAdapter() {
				
				@Override
				public void mouseMoved(MouseEvent e) {
					xPos = e.getX();
					yPos = e.getY();
					repaint();
				}
				
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString(character + "", xPos, yPos);
		}
	}
}
