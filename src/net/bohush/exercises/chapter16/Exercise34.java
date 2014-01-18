package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise34 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise34() {
		add(new BallonPanel());
	}
	
	public static void main(String[] args) {
		Exercise34 frame = new Exercise34();
		frame.setTitle("Exercise34");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class BallonPanel extends JPanel {
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
					int maxAngle = 15;
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
			g.drawLine(70, 30, 200, 30);
			g.drawLine(70, 30, 70, 200);
			g.drawArc(20, 200, 100, 100, 45, 90);
			//body
			g.drawLine(200, 30, (int) (200 + 110 * Math.cos(angle * 2 * (Math.PI / 360))), (int) (30 - 110 * Math.sin(angle * 2 * (Math.PI / 360))));

			//hands
			int x1 = (int) (200 + 40 * Math.cos(angle * 2 * (Math.PI / 360)));
			int y1 = (int) (30 - 40 * Math.sin(angle * 2 * (Math.PI / 360)));
			int x2 = (int) (x1 + 70 * Math.cos((angle + 45) * 2 * (Math.PI / 360)));
			int y2 = (int) (y1 - 70 * Math.sin((angle + 45) * 2 * (Math.PI / 360)));
			g.drawLine(x1, y1, x2, y2);
			x2 = (int) (x1 + 70 * Math.cos((angle - 45) * 2 * (Math.PI / 360)));
			y2 = (int) (y1 - 70 * Math.sin((angle - 45) * 2 * (Math.PI / 360)));
			g.drawLine(x1, y1, x2, y2);
			
			//head
			g.setColor(getBackground());
			g.fillOval((int) (200 + 40 * Math.cos(angle * 2 * (Math.PI / 360))) - 20, (int) (30 - 40 * Math.sin(angle * 2 * (Math.PI / 360)) - 20), 40, 40);
			g.setColor(Color.BLACK);
			g.drawOval((int) (200 + 40 * Math.cos(angle * 2 * (Math.PI / 360))) - 20, (int) (30 - 40 * Math.sin(angle * 2 * (Math.PI / 360))) - 20, 40, 40);
			
			//legs
			x1 = (int) (200 + 110 * Math.cos(angle * 2 * (Math.PI / 360)));
			y1 = (int) (30 - 110 * Math.sin(angle * 2 * (Math.PI / 360)));
			x2 = (int) (x1 + 50 * Math.cos((angle + 45) * 2 * (Math.PI / 360)));
			y2 = (int) (y1 - 50 * Math.sin((angle + 45) * 2 * (Math.PI / 360)));
			g.drawLine(x1, y1, x2, y2);
			x2 = (int) (x1 + 50 * Math.cos((angle - 45) * 2 * (Math.PI / 360)));
			y2 = (int) (y1 - 50 * Math.sin((angle - 45) * 2 * (Math.PI / 360)));
			g.drawLine(x1, y1, x2, y2);
			


			


		}
		
	}
}
