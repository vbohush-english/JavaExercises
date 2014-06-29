package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise09 extends JFrame{
	private LinePanel panel = new LinePanel();
	private static final long serialVersionUID = 1L;

	public Exercise09() {
		add(panel);
		panel.setFocusable(true);
	}
	
	public static void main(String[] args) {
		Exercise09 frame = new Exercise09();
		frame.setTitle("Exercise09");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class LinePanel extends JPanel {		 
		private static final long serialVersionUID = 1L;
		private Polygon p;
		private int nextX = 150;
		private int nextY = 150;
		private int step = 10;
		
		public LinePanel() {
			p = new Polygon();
			p.addPoint(nextX, nextY);
			addKeyListener(new KeyAdapter() {
			
				@Override
				public void keyPressed(KeyEvent e) {				
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: nextY -= step; break;
					case KeyEvent.VK_DOWN: nextY += step; break;
					case KeyEvent.VK_LEFT: nextX -= step; break;
					case KeyEvent.VK_RIGHT: nextX += step; break;
					}
					p.addPoint(nextX, nextY);
					repaint();
				}
			});
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
	}
}
