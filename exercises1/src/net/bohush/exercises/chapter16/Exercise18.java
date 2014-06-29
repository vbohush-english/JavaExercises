package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise18 extends JFrame{
	private BallPanel ballPanel = new BallPanel();
	
	private static final long serialVersionUID = 1L;

	public Exercise18() {
		add(ballPanel);
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {				
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: ballPanel.moveUp();	break;
				case KeyEvent.VK_DOWN: ballPanel.moveDown(); break;
				case KeyEvent.VK_LEFT: ballPanel.moveLeft(); break;
				case KeyEvent.VK_RIGHT: ballPanel.moveRight();break;
				}
			}
		});
	}
	
	class BallPanel extends JPanel {
		private int radius = 40;
		private int step = 5;
		private int xPos;
		private int yPos;
		boolean firstStart = true;
		
		private static final long serialVersionUID = 1L;
		
		public void moveUp() {
			if (yPos - step > 0) {
				yPos -= step;
				repaint();
			} else {
				yPos = 0;
				repaint();
			}
		}
		
		public void moveDown() {
			if (yPos + radius * 2 + step < getHeight()) {
				yPos += step;
				repaint();
			} else {
				yPos = getHeight() - radius * 2 - 1;
				repaint();
			}
		}
		
		public void moveLeft() {
			if (xPos - step > 0) {
				xPos -= step;
				repaint();
			} else {
				xPos = 0;
				repaint();
			}
		}
		
		public void moveRight() {
			if (xPos + radius * 2 + step < getWidth()) {
				xPos += step;
				repaint();
			} else {
				xPos = getWidth() - radius * 2 - 1;
				repaint();
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (firstStart) {
				xPos = getWidth() / 2 - radius;
				yPos = getHeight() / 2 - radius;
				firstStart = false;
			}
			g.drawOval(xPos, yPos, radius * 2, radius * 2);
		}
	}
	
	public static void main(String[] args) {
		Exercise18 frame = new Exercise18();
		frame.setTitle("Exercise18");
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

