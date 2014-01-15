package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise14 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise14() {
		add(new RaiseFlag());
		
	}

	public static void main(String[] args) {
		Exercise14 frame = new Exercise14();
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise14");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class RaiseFlag extends JPanel {
		private Image image = (new ImageIcon("image/16_04/us.gif")).getImage();
		private int yPos;
		private int xPos = 20; 
		private boolean firstRun = true;
		private static final long serialVersionUID = 1L;

		public RaiseFlag() {
			super();
			timer.start();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (firstRun) {
				yPos = getHeight() - image.getHeight(null);
				firstRun = false;
			}
			g.drawImage(image, xPos, yPos, image.getWidth(null), image.getHeight(null), this);
		}
	
		Timer timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				yPos--;
				if (yPos <= 0) {
					yPos = getHeight() - image.getHeight(null);	
				}
				repaint();
			}
		});		

	}
}