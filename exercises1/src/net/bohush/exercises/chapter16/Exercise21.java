package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise21 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise21() {
		add(new PolygonPanel());
	}
	
	public static void main(String[] args) {
		Exercise21 frame = new Exercise21();
		frame.setTitle("Exercise21");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class PolygonPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private Polygon p = new Polygon();
		private int mouseX;
		private int mouseY;
		private String message = "";

		public PolygonPanel() {
			p.addPoint(40, 20);
			p.addPoint(70, 40);
			p.addPoint(60, 80);
			p.addPoint(45, 45);
			p.addPoint(20, 60);
			addMouseMotionListener(new MouseMotionAdapter() {				
				@Override
				public void mouseMoved(MouseEvent arg0) {
					if (p.contains(arg0.getX(), arg0.getY())) {
						message = "Mouse point is in the polygon";	
					} else {
						message = "Mouse point is not in the polygon";
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
			g.drawPolygon(p);
			g.drawString(message, mouseX, mouseY);						
		}		
	}

}
