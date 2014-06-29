package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise20 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise20() {
		add(new RectanglePanel());
	}
	
	public static void main(String[] args) {
		Exercise20 frame = new Exercise20();
		frame.setTitle("Exercise20");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class RectanglePanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private int x;
		private int y;
		private int width;
		private int height;
		private int mouseX;
		private int mouseY;
		private String message = "";

		public RectanglePanel() {
			addMouseMotionListener(new MouseMotionAdapter() {				
				@Override
				public void mouseMoved(MouseEvent arg0) {
					if ((arg0.getX() > x) &&
							(arg0.getX() < x + width) &&
							(arg0.getY() > y) &&
							(arg0.getY() < y + height)) {
						message = "Mouse point is in the rectangle";	
					} else {
						message = "Mouse point is not in the rectangle";
					}			
					mouseX = arg0.getX();
					mouseY = arg0.getY();
					repaint();
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			x = (int) (getWidth() * 0.2);
			y = (int) (getHeight() * 0.2);
			width = (int) (getWidth() * 0.6);
			height = (int) (getHeight() * 0.6);
			g.drawRect(x, y, width, height);
			g.drawString(message, mouseX, mouseY);
						
		}		
	}

}
