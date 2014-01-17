package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise25 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise25() {
		add(new BallonPanel());
	}
	
	public static void main(String[] args) {
		Exercise25 frame = new Exercise25();
		frame.setTitle("Exercise25");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class BallonPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private int balX;
		private int balY;
		private int radius = 10;
		
		private int gunSize = 30;
		private int bulletPos = gunSize;
		private int gunAngle = 90;
		private int gunWidth = 5;
		
		private boolean newGame = true;
		private boolean fired = false;
		private boolean debris = false;
		Timer fireTimer;
		Timer debrisTimer;	

		public BallonPanel() {
			
			fireTimer = new Timer(1, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					bulletPos += 3;
					repaint();
				}
			});
			
			debrisTimer = new Timer(2000, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					debris = false;
					debrisTimer.stop();
					newGame = true;
					bulletPos = gunSize;
					repaint();
				}
			});
			
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if ((!fired) && (!debris)) {
						int angleStep = 5;
						if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
							if (gunAngle + angleStep < 180) {
								gunAngle += angleStep;	
							}
						} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
							if (gunAngle - angleStep > 0) {
								gunAngle -= angleStep;	
							}
						} else if (arg0.getKeyCode() == KeyEvent.VK_UP) {
							fired = true;
							fireTimer.start();
						}  
						repaint();
					}					
				}
			});

			setFocusable(true);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (newGame) {
				balX = radius + (int) (Math.random() * (getWidth() - 2 * radius));
				balY = radius + (int) (Math.random() * (getHeight() - 2 * radius - gunSize));
				newGame = false;
			}
			
			int gunX = getWidth() / 2;
			int gunY = getHeight();
			
			int nextGunX = (int) (gunX + gunSize * Math.cos(gunAngle * 2 * (Math.PI / 360)));
			int nextGunY = (int) (gunY - gunSize * Math.sin(gunAngle * 2 * (Math.PI / 360)));
			Polygon p = new Polygon();
			p.addPoint(gunX, gunY);
			p.addPoint(nextGunX, nextGunY);
			p.addPoint(nextGunX + gunWidth, nextGunY);
			p.addPoint(gunX + gunWidth, gunY);
			g.fillPolygon(p);
			
			if (fired) {
				int bulletX = (int) (gunX + bulletPos * Math.cos(gunAngle * 2 * (Math.PI / 360)));
				int bulletY = (int) (gunY - bulletPos * Math.sin(gunAngle * 2 * (Math.PI / 360)));
				double distanceToCircle = Math.sqrt((bulletX - balX) * (bulletX - balX) + (bulletY - balY) * (bulletY - balY));
				if ((bulletX < 0) || (bulletY < 0) || (bulletX > getWidth())) {
					fired = false;
					bulletPos = gunSize;
					fireTimer.stop();
				} else {
					if (distanceToCircle < radius) {
						fireTimer.stop();
						debrisTimer.start();
						fired = false;
						debris = true;
					} else {
						g.fillOval(bulletX, bulletY, gunWidth, gunWidth);	
					}	
				}
			}
			
			if (debris) {
				g.drawOval(balX + radius, balY - radius, radius / 2, radius / 2);
				g.drawOval(balX - radius, balY + radius, radius / 2, radius / 2);
				g.drawOval(balX - radius, balY - radius, radius / 2, radius / 2);
				g.drawOval(balX + radius, balY + radius, radius / 2, radius / 2);
				g.drawOval(balX, balY, radius / 2, radius / 2);
			} else {
				g.drawOval(balX - radius, balY - radius, 2 * radius, 2 * radius);

			}
		}
		
	}
}
