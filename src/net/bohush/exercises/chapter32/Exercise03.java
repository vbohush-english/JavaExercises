package net.bohush.exercises.chapter32;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

import javax.swing.*;

public class Exercise03 extends JApplet{
	private static final long serialVersionUID = 1L;

	
	public Exercise03() {
		FlagPanel flagPanel = new FlagPanel();
		add(flagPanel);
		Thread thread = new Thread(flagPanel);
		thread.start();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise03());
		frame.setTitle("Exercise03");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class FlagPanel extends JPanel implements Runnable {
		private int currentFlag = 0;
		private ImageIcon imageIcon;
		private int x;
		private int y;
		private static final long serialVersionUID = 1L;
		private boolean newFlag = true;
		private AudioClip[] audioClip = new AudioClip[7];
		private Image image;
		
		public FlagPanel() {
			for (int i = 0; i < audioClip.length; i++) {
				audioClip[i] = Applet.newAudioClip(this.getClass().getResource("anthem" + i + ".mid"));	
			}			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (newFlag) {
				audioClip[currentFlag].play();
				y = getHeight();
				newFlag = false;
				imageIcon = new ImageIcon(this.getClass().getResource("flag" + currentFlag + ".gif"));
				image = imageIcon.getImage();
			}
			x = (getWidth() - imageIcon.getIconWidth()) / 2;
			g.drawImage(image, x,  y, imageIcon.getIconWidth(), imageIcon.getIconHeight(), this);
			if (y + imageIcon.getIconHeight() <= 0) {
				newFlag = true;
				audioClip[currentFlag].stop();
				currentFlag++;
				if (currentFlag > 6) {
					currentFlag = 0;
				}				
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(10);
					y--;										
					repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
