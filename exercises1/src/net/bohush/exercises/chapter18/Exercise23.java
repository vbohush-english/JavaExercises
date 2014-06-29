package net.bohush.exercises.chapter18;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise23 extends JApplet{
	private static final long serialVersionUID = 1L;

	
	public Exercise23() {
		add(new FlagPanel());
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise23());
		frame.setTitle("Exercise23");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class FlagPanel extends JPanel {
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
			
			Timer timer = new Timer(1, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					y--;
					repaint();
				}
			});
			timer.start();
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
	}
}
