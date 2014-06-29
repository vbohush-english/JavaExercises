package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise33 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise33() {
		add(new BallonPanel());
	}
	
	public static void main(String[] args) {
		Exercise33 frame = new Exercise33();
		frame.setTitle("Exercise33");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class BallonPanel extends JPanel {
		private Point point1 = new Point();
		private Point point2 = new Point();
		private int radius1 = 4;
		private int radius2 = 10;
		private int angle = 270;
		private int timerSpeed = 50;
		private boolean leftToRight = true;
		private Timer timer;

		private static final long serialVersionUID = 1L;

		public BallonPanel() {			
			setFocusable(true);
			timer = new Timer(timerSpeed, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int maxAngle = 60;
					if (leftToRight) {
						angle++;
						if (angle >= 270 + maxAngle) {
							leftToRight = false;
						}
					} else {
						angle--;
						if (angle <= 270 - maxAngle) {
							leftToRight = true;
						}
					}
					repaint();
				}
			});
			timer.start();
			addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					switch (arg0.getKeyCode()) {
					case KeyEvent.VK_R: timer.start();break;
					case KeyEvent.VK_S: timer.stop();break;
					case KeyEvent.VK_UP:
						timerSpeed -= 10;
						if (timerSpeed < 1) {
							timerSpeed = 1;
						}
						timer.setDelay(timerSpeed);
						break;
					case KeyEvent.VK_DOWN:
						timerSpeed += 10;
						timer.setDelay(timerSpeed);
						break;
					}
					repaint();
				}
			});
			setFocusable(true);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			point1.x = getWidth() / 2;
			point1.y = (int) (getHeight() * 0.1);
			point2.x = (int) (point1.x + getHeight() * 0.8 * Math.cos(angle * 2 * (Math.PI / 360)));
			point2.y = (int) (point1.y - getHeight() * 0.8 * Math.sin(angle * 2 * (Math.PI / 360)));
			
			g.fillOval(point1.x - radius1, point1.y - radius1, 2 * radius1, 2 * radius1);
			g.fillOval(point2.x - radius2, point2.y - radius2, 2 * radius2, 2 * radius2);
			g.drawLine(point1.x, point1.y, point2.x, point2.y);
			
			
			


		}
		
	}
}
