package net.bohush.exercises.chapter22;

import javax.swing.Timer;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.PriorityQueue;

public class Exercise09 extends JApplet {

	private static final long serialVersionUID = 1L;

	public Exercise09() {
		add(new BallControl());
	}

	public static void main(String[] args) {
		Exercise09 applet = new Exercise09();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise09");
		frame.add(applet, BorderLayout.CENTER);
		frame.setSize(400, 320);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class BallControl extends JPanel {
		private static final long serialVersionUID = 1L;
		private BallsPanel ballsPanel = new BallsPanel();
		private JButton jbtSuspend = new JButton("Suspend");
		private JButton jbtResume = new JButton("Resume");
		private JButton jButton1 = new JButton(" +1 ");
		private JButton jButton2 = new JButton(" -1 ");
		private JButton jButton3 = new JButton(" +++ ");
		private JScrollBar jsbDelay = new JScrollBar();

		public BallControl() {
			JPanel panel = new JPanel();
			panel.add(jbtSuspend);
			panel.add(jbtResume);
			panel.add(jButton1);
			panel.add(jButton2);
			panel.add(jButton3);

			
			// Add ball and buttons to the panel
			ballsPanel.setBorder(new javax.swing.border.LineBorder(Color.red));
			jsbDelay.setOrientation(JScrollBar.HORIZONTAL);
			ballsPanel.setDelay(jsbDelay.getMaximum());
			setLayout(new BorderLayout());
			add(jsbDelay, BorderLayout.NORTH);
			add(ballsPanel, BorderLayout.CENTER);
			add(panel, BorderLayout.SOUTH);

			jButton1.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					ballsPanel.addBall();
					ballsPanel.setFocusable(true);
				}
			});
			jButton2.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					ballsPanel.removeBall();
					ballsPanel.setFocusable(true);
				}
			});
			jButton3.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < 100; i++) {
						ballsPanel.addBall();	
						ballsPanel.setFocusable(true);
					}					
				}
			});
			jbtSuspend.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ballsPanel.suspend();
					ballsPanel.setFocusable(true);
				}
			});
			jbtResume.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ballsPanel.resume();
					ballsPanel.setFocusable(true);
				}
			});
			jsbDelay.addAdjustmentListener(new AdjustmentListener() {
				public void adjustmentValueChanged(AdjustmentEvent e) {
					ballsPanel.setDelay(jsbDelay.getMaximum() - e.getValue());
					ballsPanel.setFocusable(true);
				}
			});
		}
	}

	class OneBall implements Comparable<OneBall> {
		private int x = 0;
		private int y = 0;
		private int dx = 2;
		private int dy = 2;
		private Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
		private int radius;
		
		
		public OneBall(int x, int y, int radius) {
			this.x = x;
			this.y = y;
			this.radius = radius;
			dx = (Math.random() < 0.5 ? dx : -dx);
			dy = (Math.random() < 0.5 ? dx : -dx);
		}
		
		@Override
		public String toString() {
			return "x: " + x + "\ty: " + y + "\tradius: " + radius;
		}

		@Override
		public int compareTo(OneBall o) {
			if (radius < o.radius)
				return 1;
			else if (radius > o.radius)
				return -1;
			else
				return 0;
		}

		
	}

	class BallsPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private int delay = 10;

		private Timer timer = new Timer(delay, new TimerListener());

		private PriorityQueue<OneBall> balls = new PriorityQueue<>();

		
		public BallsPanel() {
			int radius = 2 + (int)(Math.random() * 19);
			balls.offer(new OneBall(radius, radius, radius));
			timer.start();
			setFocusable(true);
			addMouseListener(new MouseAdapter() {				
				@Override
				public void mouseReleased(MouseEvent e) {
					removeBall();
				}
			});

		}
		
		double getLenght(MouseEvent e, OneBall b2) {
			return Math.sqrt((e.getX() - b2.x) * (e.getX() - b2.x) + (e.getY() - b2.y) * (e.getY() - b2.y));
		}

			
		public void addBall() {
			int radius = 2 + (int)(Math.random() * 19);
			balls.offer(new OneBall(radius + (int)(Math.random() * (getWidth() - radius * 2)), radius + (int)(Math.random() * (getHeight() - radius * 2)), radius));
		}
		
		public void removeBall() {
			balls.poll();
		}
		
		private class TimerListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (OneBall oneBall : balls) {
				g.setColor(oneBall.color);
				if (oneBall.x - oneBall.radius < 0) {
					oneBall.dx = Math.abs(oneBall.dx);
				} else if (oneBall.x + oneBall.radius > getWidth()) {
					oneBall.dx = -Math.abs(oneBall.dx);
				}
				if (oneBall.y - oneBall.radius < 0){
					oneBall.dy = Math.abs(oneBall.dy);
				} else if (oneBall.y + oneBall.radius > getHeight()) {
					oneBall.dy = -Math.abs(oneBall.dy);
				}

				oneBall.x += oneBall.dx;
				oneBall.y += oneBall.dy;
				g.fillOval(oneBall.x -oneBall.radius, oneBall.y - oneBall.radius, oneBall.radius * 2, oneBall.radius * 2);
			}
			
		}

		public void suspend() {
			timer.stop();
		}

		public void resume() {
			timer.start();
		}

		public void setDelay(int delay) {
			this.delay = delay;
			timer.setDelay(delay);
		}
	}

}
