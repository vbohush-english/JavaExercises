package net.bohush.exercises.chapter22;

import javax.swing.Timer;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Exercise05 extends JApplet {

	private static final long serialVersionUID = 1L;

	public Exercise05() {
		add(new BallControl());
	}

	public static void main(String[] args) {
		Exercise05 applet = new Exercise05();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise05");
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
			// Group buttons in a panel
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
				}
			});
			jButton2.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					ballsPanel.removeBall();
				}
			});
			jButton3.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < 100; i++) {
						ballsPanel.addBall();	
					}					
				}
			});
			jbtSuspend.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ballsPanel.suspend();
				}
			});
			jbtResume.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ballsPanel.resume();
				}
			});
			jsbDelay.addAdjustmentListener(new AdjustmentListener() {
				public void adjustmentValueChanged(AdjustmentEvent e) {
					ballsPanel.setDelay(jsbDelay.getMaximum() - e.getValue());
				}
			});
		}
	}

	class OneBall {
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
		
	}

	class BallsPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private int delay = 10;

		private Timer timer = new Timer(delay, new TimerListener());

		private ArrayList<OneBall> balls = new ArrayList<>();

		
		public BallsPanel() {
			int radius = 4;
			balls.add(new OneBall(radius, radius, radius));
			
			addMouseListener(new MouseAdapter() {				
				@Override
				public void mouseReleased(MouseEvent e) {
					for (int i = 0; i < balls.size(); i++) {
						if (getLenght(e, balls.get(i)) < balls.get(i).radius) {
							balls.remove(i);
							break;
						}
					}
				}
					
			});
			
			timer.start();
		}
		
		double getLenght(MouseEvent e, OneBall b2) {
			return Math.sqrt((e.getX() - b2.x) * (e.getX() - b2.x) + (e.getY() - b2.y) * (e.getY() - b2.y));
		}

			
		public void addBall() {
			int radius = 4;
			balls.add(new OneBall(radius + (int)(Math.random() * (getWidth() - radius * 2)), radius + (int)(Math.random() * (getHeight() - radius * 2)), radius));
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
				for (int j = i + 1; j < balls.size(); j++) {
					double lenght = Math.sqrt((balls.get(i).x - balls.get(j).x) * (balls.get(i).x - balls.get(j).x) + (balls.get(i).y - balls.get(j).y) * (balls.get(i).y - balls.get(j).y));
					if (lenght < balls.get(i).radius + balls.get(j).radius) {
						balls.get(i).radius += balls.get(j).radius;
						balls.get(i).x -= balls.get(j).radius;
						balls.get(i).y -= balls.get(j).radius;
						balls.remove(j);
					}
				}
				g.setColor(balls.get(i).color);

				if (balls.get(i).x - balls.get(i).radius < 0) {
					balls.get(i).dx = Math.abs(balls.get(i).dx);
				} else if (balls.get(i).x + balls.get(i).radius > getWidth()) {
					balls.get(i).dx = -Math.abs(balls.get(i).dx);
				}
				if (balls.get(i).y - balls.get(i).radius < 0){
					balls.get(i).dy = Math.abs(balls.get(i).dy);
				} else if (balls.get(i).y + balls.get(i).radius > getHeight()) {
					balls.get(i).dy = -Math.abs(balls.get(i).dy);
				}

				balls.get(i).x += balls.get(i).dx;
				balls.get(i).y += balls.get(i).dy;
				g.fillOval(balls.get(i).x - balls.get(i).radius, balls.get(i).y - balls.get(i).radius, balls.get(i).radius * 2, balls.get(i).radius * 2);
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
