package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;

public class Exercise12 extends JApplet{
	private FanControl[] fanControls = new FanControl[6];
	private static final long serialVersionUID = 1L;

	public Exercise12() {
		JPanel panel1 = new JPanel(new GridLayout(2, 3));
		for (int i = 0; i < fanControls.length; i++) {
			fanControls[i] = (new FanControl()); 
			panel1.add(fanControls[i]);
		}
		add(panel1, BorderLayout.CENTER);
		JPanel panel2 = new JPanel();
		JButton jButton1 = new JButton("Start All");
		JButton jButton2 = new JButton("Stop All");
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < fanControls.length; i++) {
					fanControls[i].getFan().start();
				}
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < fanControls.length; i++) {
					fanControls[i].getFan().stop();
				}
			}
		});
		panel2.add(jButton1);
		panel2.add(jButton2);
		add(panel2, BorderLayout.SOUTH);
	}
	
	class FanControl extends JPanel {
		private static final long serialVersionUID = 1L;
		private Fan fan; 
		private JScrollBar jScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 5, 0, 1, 40);
		
		public Fan getFan() {
			return fan;
		}
		
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
	
	class Fan extends JPanel {
		private Timer timer;
		private static final long serialVersionUID = 1L;
		private Color color;
		private int arc = 0;
		private boolean reverseFan = false;

		public void setSpeed(int spead) {
			if (spead > 40) {
				spead = 40;
			} else if (spead < 1) {
				spead = 1;
			}
			timer.setDelay(spead);
		}
		
		public void start() {
			timer.start();
		}
		
		public void stop() {
			timer.stop();
		}
		
		public void reverse() {
			reverseFan = (reverseFan ? false : true);
		}
		
		public Fan(int spead) {
			color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
			timer = new Timer(spead, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();
				}

			});
			timer.start();
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

	}



}
