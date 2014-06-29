package net.bohush.exercises.chapter32;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;

public class Exercise05 extends JApplet{

	private static final long serialVersionUID = 1L;

	public Exercise05() {
		add(new FanControl());
	}
	
	class FanControl extends JPanel {
		private static final long serialVersionUID = 1L;
		private Fan fan; 
		private JScrollBar jScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 5, 0, 1, 40);

		public FanControl() {
			setLayout(new BorderLayout());
			JPanel panel1 = new JPanel(new GridLayout(1, 3));
			JButton button1 = new JButton("Start");
			JButton button2 = new JButton("Stop");
			JButton button3 = new JButton("Reverse");
			button1.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					fan.start();
				}
			});
			button2.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					fan.stop();
				}
			});
			button3.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					fan.reverse();
				}
			});
			panel1.add(button1);
			panel1.add(button2);
			panel1.add(button3);
			add(panel1, BorderLayout.NORTH);
			
			fan = new Fan(41 - 5);
			add(fan, BorderLayout.CENTER);
			
			jScrollBar.addAdjustmentListener(new AdjustmentListener() {				
				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
					fan.setSpeed(41 - jScrollBar.getValue());					
				}
			});
			add(jScrollBar, BorderLayout.SOUTH);
		}
	}
	
	class Fan extends JPanel implements Runnable{
		private static final long serialVersionUID = 1L;
		private Color color;
		private int arc = 0;
		private boolean reverseFan = false;
		private boolean isMoving = true;
		private int spead;
		
		public void setSpeed(int spead) {
			if (spead > 40) {
				spead = 40;
			} else if (spead < 1) {
				spead = 1;
			}
			this.spead = spead;
		}
		
		public void start() {
			isMoving = true;;
		}
		
		public void stop() {
			isMoving = false;
		}
		
		public void reverse() {
			reverseFan = (reverseFan ? false : true);
		}
		
		public Fan(int spead) {
			this.spead = spead;
			color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
			Thread thread = new Thread(this);
			thread.start();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int width = getWidth();
			int height = getHeight();
			int diametr = (int) (Math.min(width, height) * 0.8);
			g.setColor(color);
			g.fillArc((width - diametr) / 2, (height - diametr) / 2, diametr, diametr, arc, 30);
			g.fillArc((width - diametr) / 2, (height - diametr) / 2, diametr, diametr, arc + 90, 30);
			g.fillArc((width - diametr) / 2, (height - diametr) / 2, diametr, diametr, arc + 180, 30);
			g.fillArc((width - diametr) / 2, (height - diametr) / 2, diametr, diametr, arc + 270, 30);
			g.drawOval((width - diametr) / 2 - (int) (diametr * 0.05), (height - diametr) / 2 - (int) (diametr * 0.05),	(int) (diametr * 1.1), (int) (diametr * 1.1));
			if (reverseFan) {
				arc -= 5;
			} else {
				arc += 5;
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(spead);
					if(isMoving) {
						repaint();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise05());
		frame.setTitle("Exercise05");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


}
