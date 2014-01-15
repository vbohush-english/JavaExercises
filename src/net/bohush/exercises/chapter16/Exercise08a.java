package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise08a extends JFrame{
	private MousePanel panel = new MousePanel();
	private static final long serialVersionUID = 1L;

	public Exercise08a() {
		panel.setFocusable(true);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.displaMousePosition(e.getX(), e.getY());
			}
		});
		add(panel);
	}
	
	public static void main(String[] args) {
		Exercise08a frame = new Exercise08a();
		frame.setTitle("Exercise08a");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class MousePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int xPos = 0;
		private int yPos = 0;
		boolean paintNow = false;
		
		public void displaMousePosition(int x, int y) {
			xPos = x;
			yPos = y;
			paintNow = true;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (paintNow) {
				g.drawString("(" + xPos + ", " + yPos + ")", xPos, yPos);
				paintNow = false;
			}
		}
	}
}
