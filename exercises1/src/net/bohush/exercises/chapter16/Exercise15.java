package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise15 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise15() {
		add(new CarPanel());
		
	}

	public static void main(String[] args) {
		Exercise15 frame = new Exercise15();
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise15");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class CarPanel extends JPanel {
		private int yPos;
		private int xPos;
		private int size = 20;
		private int speed = 100;
		private boolean firstRun = true;
		private static final long serialVersionUID = 1L;

		public CarPanel() {
			super();
			timer.start();
			setFocusable(true);
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_SPACE) {
						timer.stop();
					}
					if(e.getKeyCode() == KeyEvent.VK_UP) {
						speed -= 10;
						if (speed <= 0) {
							speed = 1;
						}
						timer.setDelay(speed);
					}
					if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						speed += 10;
						timer.setDelay(speed);
					}
				}
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_SPACE) {
						timer.start();
					}
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (firstRun) {
				xPos = - 5* size;
				firstRun = false;
			}
			yPos = getHeight();
			g.setColor(Color.BLACK);
			g.fillOval(xPos + size, yPos - size, size, size);
			g.fillOval(xPos + 3 * size, yPos - size, size, size);
			g.setColor(Color.RED);
			g.fillRect(xPos, yPos - 2 * size, 5 * size, size);
			g.setColor(Color.BLUE);
			Polygon p = new Polygon();
			p.addPoint(xPos + size, yPos - 2 * size);
			p.addPoint(xPos + 2 * size, yPos - 3 * size);
			p.addPoint(xPos + 3 * size, yPos - 3 * size);
			p.addPoint(xPos + 4 * size, yPos - 2 * size);
			g.fillPolygon(p);
		}
	
		Timer timer = new Timer(speed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				xPos ++;
				if (xPos >= getWidth()) {
					xPos = - 5* size;	
				}
				repaint();
			}
		});		

	}
}