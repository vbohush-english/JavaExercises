package net.bohush.exercises.chapter33;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.swing.*;

public class Exercise05 extends JApplet{
	private String[] data = new String[20];
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Exercise05 applet = new Exercise05();
		applet.doOnStart();
		frame.add(applet);
		frame.setTitle("Exercise05");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void doOnStart() {
	    java.net.URL url = this.getClass().getResource("stock.png");
		ImageIcon imageIcon = new ImageIcon(url);
		
		add(new JLabel(imageIcon), BorderLayout.NORTH);
		add(new StockTicker(), BorderLayout.CENTER);
		
		java.net.URI uri;
		try {
			uri = this.getClass().getResource("Ticker.txt").toURI();
			Scanner input = new Scanner(new File(uri));
			int i = 0;
			while(input.hasNext()) {
				data[i++] = input.next();
			}
			input.close();
		} catch (URISyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void init() {
		doOnStart();
	}
	
	public Exercise05() {

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
