package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Exercise32 extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> points = new ArrayList<>();

	public Exercise32() {
		this.add(new MousePanel());
	}

	public static void main(String[] args) {
		Exercise32 frame = new Exercise32();
		frame.setTitle("Exercise32");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class MousePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final int DIAMETR = 6;
		public MousePanel() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						points.add(new Point(e.getX(), e.getY()));
						repaint();
					} else if (e.getButton() == MouseEvent.BUTTON1) {
						for (int i = 0; i < points.size(); i++) {
							if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < DIAMETR / 2.0) {
								points.remove(i);
								repaint();
								break;
							}
						}
					}
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < points.size(); i++) {
				g.drawOval(points.get(i).x - DIAMETR / 2, points.get(i).y - DIAMETR / 2, DIAMETR, DIAMETR);
			}

		}
		
		private double getLenght(Point p1, Point p2){
			return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
		}
	}

}