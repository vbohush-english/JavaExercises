package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise35 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise35() {
		add(new SinFunction());
	}
	
	public static void main(String[] args) {
		Exercise35 frame = new Exercise35();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setTitle("Exercise35");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class SinFunction extends JPanel {
		private int startX;
		private int finishX;
		private int currentX;
		private int radius = 6;
		boolean firstRun = true;
		boolean toRight = true;
		
		private static final long serialVersionUID = 1L;
		private Timer timer;

		private int getY(int x) {
			return (int)(50 * Math.sin((x / 100.0) * 2 * Math.PI));
		}
		
		public SinFunction() {
			timer = new Timer(10, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (toRight) {
						currentX++;
						if (finishX < currentX) {
							toRight = false;
						}
					} else {
						currentX--;
						if (startX > currentX) {
							toRight = true;
						}
					}
					repaint();
				}
			});
			timer.start();
			setFocusable(true);
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					if (arg0.getButton() == MouseEvent.BUTTON1) {
						timer.stop();
					} else if (arg0.getButton() == MouseEvent.BUTTON3) {
						timer.start();
					}  
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//x-line
			int width = getWidth();
			int height = getHeight();
			g.drawLine(10, (int)(height * 0.5), width - 10, (int)(height * 0.5));
			g.drawLine(width - 25, (int)(height * 0.5) - 10, width - 10, (int)(height * 0.5));
			g.drawLine(width - 25, (int)(height * 0.5) + 10, width - 10, (int)(height * 0.5));
			g.drawString("X", width - 10, (int)(height * 0.5) - 15);
			
			//y-line
			g.drawLine((int)(width * 0.5), 10, (int)(width * 0.5), height - 10);
			g.drawLine((int)(width * 0.5), 10, (int)(width * 0.5) - 10, 25);
			g.drawLine((int)(width * 0.5), 10, (int)(width * 0.5) + 10, 25);
			g.drawString("Y", (int)(width * 0.5) + 15, 10);
			
			//sin function
			Polygon p = new Polygon();
			g.setColor(Color.RED);
			startX = -(int)(width * 0.5 - 10);
			finishX = (int)(width * 0.5 - 10);
			for (int x = startX; x <= finishX; x++) {
				p.addPoint((int)(width * 0.5) + x, (int)(height * 0.5) - getY(x));
			}
			g.drawPolyline(p.xpoints, p.ypoints, p.npoints);

			//Pi
			g.setColor(Color.BLACK);
			g.drawString("0", (int)(width * 0.5) - 10, (int)(height * 0.5) + 15);
			g.drawString("-2\u03c0", (int)(width * 0.5) - 10 - 100, (int)(height * 0.5) + 15);
			g.drawString("-\u03c0", (int)(width * 0.5) - 10 - 50, (int)(height * 0.5) + 15);
			g.drawString("2\u03c0", (int)(width * 0.5) - 10 + 100, (int)(height * 0.5) + 15);
			g.drawString("\u03c0", (int)(width * 0.5) - 10 + 50, (int)(height * 0.5) + 15);
			
			//ball
			g.setColor(Color.BLUE);
			if (firstRun) {
				currentX = startX;
				firstRun = false;
			}
			g.fillOval((int)(width * 0.5) + currentX - radius, (int)(height * 0.5) - getY(currentX) - radius, radius * 2, radius * 2);
		}
	}

}