package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise16 extends JApplet{
	private String[] data = new String[20];
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
	    java.net.URL url = this.getClass().getResource("image\\18_16\\stock.png");
		ImageIcon imageIcon = new ImageIcon(url);
		add(new JLabel(imageIcon), BorderLayout.NORTH);
		add(new StockTicker(), BorderLayout.CENTER);
		for (int i = 1; i <= 5; i++) {
			String tmpIndex = getParameter("index" + i);
			String[] tmpParams = tmpIndex.split(";");
			data[(i - 1) * 4 + 0] = tmpParams[0];
			data[(i - 1) * 4 + 1] = tmpParams[1];
			data[(i - 1) * 4 + 2] = tmpParams[2];
			data[(i - 1) * 4 + 3] = tmpParams[3];
		}
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	
	}
	
	public Exercise16() {

	}
	
	class StockTicker extends JPanel {
		private static final long serialVersionUID = 1L;
		private int startX;
		private boolean firstRun = true;
		Timer timer;
		public StockTicker() {
			timer = new Timer(10, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					startX--;
					if (startX < - 20 * 40) {
						startX = getWidth();
					}
					repaint();
				}
			});
			timer.start();
			addMouseListener(new MouseAdapter() {				
				@Override
				public void mouseReleased(MouseEvent e) {
					timer.start();
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					timer.stop();
				}
				
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (firstRun) {
				startX = getWidth();
				firstRun = false;
			}
			g.setFont(new Font("Serif", Font.BOLD, 18));			
			for(int i = 0; i < 20; i += 4) {
				String tmp = data[i + 3];
				if (Double.parseDouble(data[i + 3]) < 0) {
					g.setColor(Color.RED);
					tmp += " " + "\u2193";
				} else {
					g.setColor(Color.GREEN);
					tmp += " " + "\u2191";
				}
				g.drawString(data[i] + "  " + data[i + 1], startX + i * 40, 40);
				g.drawString(data[i + 2] + "  " + tmp, startX + i * 40, 60);
			}
		}
	}
	
}
