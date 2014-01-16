package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise19 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise19() {
		add(new CirclePanel());
	}
	
	public static void main(String[] args) {
		Exercise19 frame = new Exercise19();
		frame.setTitle("Exercise19");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class CirclePanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private int x;
		private int y;
		private int radius;
		private int mouseX;
		private int mouseY;
		private String message = "";
		
		private double getLenght(MouseEvent arg0){
			return Math.sqrt((arg0.getX() - x) * (arg0.getX() - x) + (arg0.getY() - y) * (arg0.getY() - y));
		}
		
		public CirclePanel() {
			addMouseMotionListener(new MouseMotionAdapter() {				
				@Override
				public void mouseMoved(MouseEvent arg0) {
					if (getLenght(arg0) < radius) {
						message = "Mouse point is in the circle";	
					} else {
						message = "Mouse point is not in the circle";
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
			x = getWidth() / 2;
			y = getHeight() / 2;
			radius = (int) (Math.min(getWidth(), getHeight()) * 0.4);
			g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
			g.drawString(message, mouseX, mouseY);
						
		}		
	}

}
