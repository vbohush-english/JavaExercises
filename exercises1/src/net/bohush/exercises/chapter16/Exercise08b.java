package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise08b extends JFrame{
	private MousePanel panel = new MousePanel();
	private static final long serialVersionUID = 1L;
	private boolean isPresssed = false;
	
	public Exercise08b() {
		panel.setFocusable(true);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				panel.displaMousePosition(e.getX(), e.getY());
				isPresssed = true;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				isPresssed = false;
				panel.dontPaint();				
			}
			
			
		});
		
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isPresssed) {
					panel.displaMousePosition(e.getX(), e.getY());
				}
				super.mouseDragged(e);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
					panel.dontPaint();	

			}
			
		});
		add(panel);
	}
	
	public static void main(String[] args) {
		Exercise08b frame = new Exercise08b();
		frame.setTitle("Exercise08b");
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
		
		public void dontPaint() {
			paintNow = false;
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
