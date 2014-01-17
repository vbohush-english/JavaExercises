package net.bohush.exercises.chapter16;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise22 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise22() {
		add(new BeanMachine());
	}
	
	public static void main(String[] args) {
		Exercise22 frame = new Exercise22();
		frame.setSize(400, 400);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise22");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class BeanMachine extends JPanel {
		private final int NUMBER_OF_BALLS = 10;
		private	final int BEEN_COUNT = 8;
		private static final long serialVersionUID = 1L;
		private int[][] mashine = new int[NUMBER_OF_BALLS][BEEN_COUNT - 1];
		private int nextBall = 0;
		private int nextStep = 0;
		private Timer timer;
		private boolean finished = false;
		

		public BeanMachine() {
			for (int i = 0; i < NUMBER_OF_BALLS; i++) {
				for (int j = 0; j < BEEN_COUNT - 1; j++) {
					mashine[i][j] = (int)(Math.random() * 2);
				}
			}
			timer = new Timer(100, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					nextStep++;
					if (nextStep >= BEEN_COUNT) {
						nextBall++;
						nextStep = 0;
						if (nextBall >= NUMBER_OF_BALLS) {
							finished = true;
							timer.stop();							
						}
					}
					repaint();
				}
			});
			timer.start();
			
		}
		
		private void paintNext(Graphics g, int ballNumber, int ballStep) {
			int size = Math.min(getWidth(), getHeight());
			int startX = (getWidth() - size) / 2;
			int startY = (getHeight() - size) / 2;
			final int OVAL_DIAMETR = (int) (size * 0.04);
			double step = (size - 20.0) / BEEN_COUNT;
			
			g.setColor(Color.BLUE);
			int nextBX = startX  - (int) (step / 2);
			int nextBY = startY;
			
			int position = 0;
			for (int j = (BEEN_COUNT - 1); j >= 0; j--) {
				if (j == ballStep) {
					position = 0;
					for (int i = 0; i < j; i++) {
						if (mashine[ballNumber][i] == 1) {
							position++;
						}
					}
					g.fillOval(nextBX + 10 + (int)((position + 1) * step) - OVAL_DIAMETR / 2, nextBY + (int)(size * 0.8) - OVAL_DIAMETR / 2, OVAL_DIAMETR, OVAL_DIAMETR);
				}
				nextBY = startY - (int)((BEEN_COUNT - j) * ((size * 0.6)) / (BEEN_COUNT - 2));
				nextBX = startX + (int)((BEEN_COUNT - j) * (step / 2.0)) - (int) (step / 2);
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			int size = Math.min(getWidth(), getHeight());
			int startX = (getWidth() - size) / 2;
			int startY = (getHeight() - size) / 2;
			
			g.setColor(Color.DARK_GRAY);
			
			g.drawLine(startX + 10, startY + size - 10, startX + size - 10, startY + size - 10);
			
			g.drawLine(startX + 10, startY + size - 10, startX + 10, startY + (int)(size * 0.8));
			g.drawLine(startX + size - 10, startY + (int)(size * 0.8), startX + size - 10, startY + size - 10);
			
			g.drawLine(startX + (int)(size * 0.4), startY + (int)(size * 0.2), startX + 10, startY + (int)(size * 0.8));
			g.drawLine(startX + size - 10, startY + (int)(size * 0.8), startX + (int)(size * 0.6), startY + (int)(size * 0.2));
			
			g.drawLine(startX + (int)(size * 0.4), startY + (int)(size * 0.2), startX + (int)(size * 0.4), startY + 10);
			g.drawLine(startX + (int)(size * 0.6), startY + 10, startX + (int)(size * 0.6), startY + (int)(size * 0.2));
			
			double step = (size - 20.0) / BEEN_COUNT;
			for (int i = 1; i < BEEN_COUNT; i++) {
				int nextX = (int)(i * step);
				g.drawLine(startX + 10 + nextX, startY + (int)(size * 0.8), startX + 10 + nextX, startY + size - 10);	
			}			
			
			g.setColor(Color.RED);
			int nextBX = startX;
			int nextBY = startY;
			int OVAL_DIAMETR = (int) (size * 0.02);
			for (int j = (BEEN_COUNT - 1); j >= 1; j--) {
				for (int i = 1; i <= j; i++) {
					int nextX = (int)(i * step);
					g.fillOval(nextBX + 10 + nextX - OVAL_DIAMETR / 2,
							   nextBY + (int)(size * 0.8) - OVAL_DIAMETR / 2,
							   OVAL_DIAMETR,
							   OVAL_DIAMETR);
				}
				nextBY = startY - (int)((BEEN_COUNT - j) * ((size * 0.6)) / (BEEN_COUNT - 2));
				nextBX = startX + (int)((BEEN_COUNT - j) * (step / 2.0));
			}
			if (!finished) {
				paintNext(g, nextBall, nextStep);
			}
			
			int[] balls = new int [BEEN_COUNT];
			
			for (int i = 0; i < nextBall; i++) {
				int position = 0;
				for (int j = 0; j < mashine[i].length; j++) {
					if (mashine[i][j] == 1) {
						position++;
					}
				}
				balls[position]++;
			}
			
			int ballOvalDiametr = (int) (size * 0.04);
			g.setColor(Color.BLUE);
			for (int i = 0; i < BEEN_COUNT; i++) {
				for (int j = 0; j < balls[i]; j++) {
					g.fillOval(startX + 10 + (int) (i * step) + (int) (step / 2) - ballOvalDiametr / 2,
							startY + size - 10 - (j + 1) * ballOvalDiametr,
							ballOvalDiametr,
							ballOvalDiametr);
				}
			}

		}		
	}	
}

