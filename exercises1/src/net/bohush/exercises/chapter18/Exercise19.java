package net.bohush.exercises.chapter18;

import javax.swing.Timer;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Exercise19 extends JApplet {

	private static final long serialVersionUID = 1L;

	public Exercise19() {
		add(new BallControl());
	}

	public static void main(String[] args) {
		Exercise19 applet = new Exercise19();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise19");
		frame.add(applet, BorderLayout.CENTER);
		frame.setSize(400, 320);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class BallControl extends JPanel {
		private static final long serialVersionUID = 1L;
		private Ball ball = new Ball();
		private JButton jbtSuspend = new JButton("Suspend");
		private JButton jbtResume = new JButton("Resume");
		private JButton jButton1 = new JButton(" +1 ");
		private JButton jButton2 = new JButton(" -1 ");
		private JButton jButton3 = new JButton(" +++ ");
		private JScrollBar jsbDelay = new JScrollBar();

		public BallControl() {
			// Group buttons in a panel
			JPanel panel = new JPanel();
			panel.add(jbtSuspend);
			panel.add(jbtResume);
			panel.add(jButton1);
			panel.add(jButton2);
			panel.add(jButton3);

			
			// Add ball and buttons to the panel
			ball.setBorder(new javax.swing.border.LineBorder(Color.red));
			jsbDelay.setOrientation(JScrollBar.HORIZONTAL);
			ball.setDelay(jsbDelay.getMaximum());
			setLayout(new BorderLayout());
			add(jsbDelay, BorderLayout.NORTH);
			add(ball, BorderLayout.CENTER);
			add(panel, BorderLayout.SOUTH);

			jButton1.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					ball.addBall();
				}
			});
			jButton2.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					ball.removeBall();
				}
			});
			jButton3.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < 1000; i++) {
						ball.addBall();	
					}					
				}
			});
			// Register listeners
			jbtSuspend.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ball.suspend();
				}
			});
			jbtResume.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ball.resume();
				}
			});
			jsbDelay.addAdjustmentListener(new AdjustmentListener() {
				public void adjustmentValueChanged(AdjustmentEvent e) {
					ball.setDelay(jsbDelay.getMaximum() - e.getValue());
				}
			});
		}
	}

	class OneBall {
		public int x = 0;
		public int y = 0;
		public int dx = 2;
		public int dy = 2;
		public Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
		
		public OneBall(int x, int y) {
			this.x = x;
			this.y = y;
			dx = (Math.random() < 0.5 ? 2 : -2);
			dy = (Math.random() < 0.5 ? 2 : -2);
		}
		
		public OneBall() {
			this(0, 0);
		}

	}

	class Ball extends JPanel {

		private static final long serialVersionUID = 1L;

		private int delay = 10;

		// Create a timer with delay 1000 ms
		private Timer timer = new Timer(delay, new TimerListener());

		private int radius = 5; // Ball radius
		private ArrayList<OneBall> balls = new ArrayList<>();

		
		public Ball() {
			balls.add(new OneBall());
			timer.start();
		}

			
		public void addBall() {
			balls.add(new OneBall((int)(Math.random() * getWidth()), (int)(Math.random() * getHeight())));
		}
		
		public void removeBall() {
			if (balls.size() > 0) {
				balls.remove(balls.size() - 1);
			}
		}
		
		private class TimerListener implements ActionListener {
			@Override
			/** Handle the action event */
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (int i = 0; i < balls.size(); i++) {
				g.setColor(balls.get(i).color);
				// Check boundaries
				if (balls.get(i).x < 0 || balls.get(i).x > getWidth())
					balls.get(i).dx *= -1;
				if (balls.get(i).y < 0 || balls.get(i).y > getHeight())
					balls.get(i).dy *= -1;

				// Adjust ball position
				balls.get(i).x += balls.get(i).dx;
				balls.get(i).y += balls.get(i).dy;
				g.fillOval(balls.get(i).x - radius, balls.get(i).y - radius, radius * 2, radius * 2);				
			}
			
		}

		public void suspend() {
			timer.stop(); // Suspend timer
		}

		public void resume() {
			timer.start(); // Resume timer
		}

		public void setDelay(int delay) {
			this.delay = delay;
			timer.setDelay(delay);
		}
	}

}
