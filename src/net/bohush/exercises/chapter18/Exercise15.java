package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise15 extends JApplet{

	private static final long serialVersionUID = 1L;

	public Exercise15() {
		add(new ImagePanel());
	}

	class ImagePanel extends JPanel {
		
	    private static final long serialVersionUID = 1L;
	    private java.net.URL url = this.getClass().getResource("image\\18_15\\image.png");
		private ImageIcon imageIcon = new ImageIcon(url);
		private Image image = imageIcon.getImage();
		private int width = 300;
		private int height = 300;
		private boolean toDown = true;

		public ImagePanel() {
			Timer timer = new Timer(1, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (toDown) {
						width--;
						height--;	
						if (width <= 50) {
							toDown = false;
						}
					} else {
						width++;
						height++;
						if (width >= 300) {
							toDown = true;
						}
					}
					repaint();
				}
			});
			timer.start();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (image != null)
				g.drawImage(image, (getWidth() - width) / 2,  (getHeight() - height) / 2, width, height, this);
		}
	}
}
