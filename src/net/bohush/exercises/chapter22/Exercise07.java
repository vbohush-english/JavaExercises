package net.bohush.exercises.chapter22;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Exercise07 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise07() {
		add(new BallonPanel());
	}
	
	public static void main(String[] args) {
		Exercise07 frame = new Exercise07();
		frame.setTitle("Exercise07");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(340, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class BallonPanel extends JPanel {
		private int angle = 270;
		private int timerSpeed = 10;
		private boolean leftToRight = true;
		private Timer timer;
		
		private String[] words = {"write", "that", "program", "java"};
		private String word = words[(int)(Math.random() * words.length)];
		private boolean[] guessedChars = new boolean[word.length()];
		private ArrayList<Character> missedChars = new ArrayList<>();
		
		
		private static final long serialVersionUID = 1L;

		private boolean isGuessed() {
			for (int i = 0; i < guessedChars.length; i++) {
				if (!guessedChars[i]) {
					return false;
				}
			}
			return true;
		}
		
		public boolean putChar(char nextChar) {
			boolean result = false;
			for (int i = 0; i < word.length(); i++) {
				if (nextChar == word.charAt(i)) {
					if (guessedChars[i]) {
						return true;
					} else {
						guessedChars[i] = true;
						result = true;	
					}				
				}
			}
			return result;
		}
		
		public BallonPanel() {

			
			setFocusable(true);
			timer = new Timer(timerSpeed, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int maxAngle = 30;
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
			
			addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					if ((missedChars.size() < 7)&&(!isGuessed())) {
						char nextChar = Character.toLowerCase(arg0.getKeyChar());
						if ((Character.isAlphabetic(nextChar)) &&(!putChar(nextChar))) {
							if(!missedChars.contains(nextChar)) {
								missedChars.add(nextChar); 
								if (missedChars.size() == 7) {
									timer.start();
								}
							}
						}
						repaint();
					}
					
					if ((arg0.getKeyCode() == KeyEvent.VK_ENTER)&&((isGuessed()) || timer.isRunning())) {
						timer.stop();
						word = words[(int)(Math.random() * words.length)];
						guessedChars = new boolean[word.length()];
						missedChars = new ArrayList<>();
						angle = 270;
						repaint();
					}
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
			
			//rope			
			if (missedChars.size() > 0) {
				g.drawLine(200, 30, (int) (200 + 20 * Math.cos(angle * 2 * (Math.PI / 360))), (int) (30 - 20 * Math.sin(angle * 2 * (Math.PI / 360))));
			}
			
			//body			
			if (missedChars.size() > 4) {
				g.drawLine(200, 30, (int) (200 + 110 * Math.cos(angle * 2 * (Math.PI / 360))), (int) (30 - 110 * Math.sin(angle * 2 * (Math.PI / 360))));
			}

			//hand 1
			if (missedChars.size() > 2) {
				int x1 = (int) (200 + 40 * Math.cos(angle * 2 * (Math.PI / 360)));
				int y1 = (int) (30 - 40 * Math.sin(angle * 2 * (Math.PI / 360)));
				int x2 = (int) (x1 + 70 * Math.cos((angle - 45) * 2 * (Math.PI / 360)));
				int y2 = (int) (y1 - 70 * Math.sin((angle - 45) * 2 * (Math.PI / 360)));
				g.drawLine(x1, y1, x2, y2);
			}
			
			//hand 2
			if (missedChars.size() > 3) {
				int x1 = (int) (200 + 40 * Math.cos(angle * 2 * (Math.PI / 360)));
				int y1 = (int) (30 - 40 * Math.sin(angle * 2 * (Math.PI / 360)));
				int x2 = (int) (x1 + 70 * Math.cos((angle + 45) * 2 * (Math.PI / 360)));
				int y2 = (int) (y1 - 70 * Math.sin((angle + 45) * 2 * (Math.PI / 360)));
				g.drawLine(x1, y1, x2, y2);
			}
						
			//head
			if (missedChars.size() > 1) {
				g.setColor(getBackground());
				g.fillOval((int) (200 + 40 * Math.cos(angle * 2 * (Math.PI / 360))) - 20, (int) (30 - 40 * Math.sin(angle * 2 * (Math.PI / 360)) - 20), 40, 40);
				g.setColor(Color.BLACK);
				g.drawOval((int) (200 + 40 * Math.cos(angle * 2 * (Math.PI / 360))) - 20, (int) (30 - 40 * Math.sin(angle * 2 * (Math.PI / 360))) - 20, 40, 40);
			}
			
			//leg 1
			if (missedChars.size() > 5) {
				int x1 = (int) (200 + 110 * Math.cos(angle * 2 * (Math.PI / 360)));
				int y1 = (int) (30 - 110 * Math.sin(angle * 2 * (Math.PI / 360)));
				int x2 = (int) (x1 + 50 * Math.cos((angle - 45) * 2 * (Math.PI / 360)));
				int y2 = (int) (y1 - 50 * Math.sin((angle - 45) * 2 * (Math.PI / 360)));
				g.drawLine(x1, y1, x2, y2);
			}
						
			//leg 2
			if (missedChars.size() > 6) {
				int x1 = (int) (200 + 110 * Math.cos(angle * 2 * (Math.PI / 360)));
				int y1 = (int) (30 - 110 * Math.sin(angle * 2 * (Math.PI / 360)));
				int x2 = (int) (x1 + 50 * Math.cos((angle + 45) * 2 * (Math.PI / 360)));
				int y2 = (int) (y1 - 50 * Math.sin((angle + 45) * 2 * (Math.PI / 360)));
				g.drawLine(x1, y1, x2, y2);
			}

			
			//text
			String text = "Guess a word: ";
			for (int i = 0; i < guessedChars.length; i++) {
				if (guessedChars[i]) {
					text += word.charAt(i);
				} else {
					text += '*';
				}
			}
			g.drawString(text, 110, 200);

			if (missedChars.size() != 0) {	
				String text2 = "Missed letters: ";
				for (int i = 0; i < missedChars.size(); i++) {
					text2 += missedChars.get(i);
				}
				g.drawString(text2, 110, 225);
			}
			
			if ((isGuessed()) || timer.isRunning()) {
				g.drawString("To continue the game, press ENTER", 110, 250);
			}
			

		}
		
	}
}
