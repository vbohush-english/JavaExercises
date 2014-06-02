package net.bohush.exercises.chapter36;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Exercise06 extends JApplet {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise06");
		Exercise06 applet = new Exercise06();
		frame.add(applet, java.awt.BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	@Override
	public void init() {
		MovingMessage movingMessage = new MovingMessage();
		setLayout(new BorderLayout());
		add(movingMessage, BorderLayout.CENTER);
	}
	
	class MovingMessage extends JPanel {
		private static final long serialVersionUID = 1L;
		private int x = 10;
		private int y = 150;
		private String message = "";
		
		public MovingMessage() {
			Tick tick = new Tick(0, 600, 0, 1);
			tick.addTickListener(new TickListener() {
				@Override
				public void handleTick(TickEvent e) {
					x++;
					message = e.toString();
					repaint();
				}
			});
			tick.suspend();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString(message, x, y);
		}
	}
	
	static class Tick {
		private ArrayList<TickListener> tickListenerList; 
		
		private int tickCount = 0;
		private int tickInterval;
		private int maxInterval;
		private int minInterval;
		private int step;
		private Timer timer;
		
		public Tick() {
			this(100, 5000, 1, 0);
		}
		
		public Tick(int tickInterval, int maxInterval, int minInterval, int step) {
			this.tickInterval = tickInterval;
			this.maxInterval = maxInterval;
			this.minInterval = minInterval;
			this.step = step;
			timer = new Timer(tickInterval, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					Tick.this.tickCount++;
					processEvent(new TickEvent(Tick.this, Tick.this.tickCount, Tick.this.tickInterval));
					if (Tick.this.step != 0) {
						Tick.this.tickInterval += Tick.this.step;
						timer.setDelay(Tick.this.tickInterval);
					}
					if(Tick.this.tickInterval < Tick.this.minInterval) {
						timer.stop();
					}
					if(Tick.this.tickInterval > Tick.this.maxInterval) {
						timer.stop();
					}
				}
			});
		}
		
		public int getTickInterval() {
			return tickInterval;
		}

		public void setTickInterval(int tickInterval) {
			this.tickInterval = tickInterval;
		}

		public int getMaxInterval() {
			return maxInterval;
		}

		public void setMaxInterval(int maxInterval) {
			this.maxInterval = maxInterval;
		}

		public int getMinInterval() {
			return minInterval;
		}

		public void setMinInterval(int minInterval) {
			this.minInterval = minInterval;
		}

		public int getStep() {
			return step;
		}

		public void setStep(int step) {
			this.step = step;
		}

		public int getTickCount() {
			return tickCount;
		}

		public void resume() {
			timer.stop();
		}
		
		public void suspend() {
			timer.start();
		}

		@SuppressWarnings("unchecked")
		private void processEvent(TickEvent e) {
			ArrayList<TickListener> list;

			synchronized (this) {
				if (tickListenerList == null) {
					return;
				}				
				list = (ArrayList<TickListener>) tickListenerList.clone();
			}
			for (int i = 0; i < list.size(); i++) {
				TickListener listener = (TickListener) list.get(i);
				listener.handleTick(e);
			}
		}

		public synchronized void addTickListener(TickListener listener) {
			if (tickListenerList == null) {
				tickListenerList = new ArrayList<TickListener>();
			}

			if (!tickListenerList.contains(listener)) {
				tickListenerList.add(listener);
			}
		}
		
		public synchronized void removeTickListener(TickListener listener) {
			if ((tickListenerList != null) && (tickListenerList.contains(listener))) {
				tickListenerList.remove(listener);
			}
		}
		
	}

	static class TickEvent extends EventObject {
		private static final long serialVersionUID = 1L;
		private long tickCount; 
		private long tickInterval;
		
		public TickEvent(Object source, long tickCount, long tickInterval) {
			super(source);
			this.tickCount = tickCount;
			this.tickInterval = tickInterval;
		}

		public long getTickCount() {
			return tickCount;
		}

		public void setTickCount(long tickCount) {
			this.tickCount = tickCount;
		}

		public long getTickInterval() {
			return tickInterval;
		}

		public void setTickInterval(long tickInterval) {
			this.tickInterval = tickInterval;
		}
		
		@Override
		public String toString() {
			return "[count = " + tickCount + ", interval = " + tickInterval + "]";
		}
	}

	interface TickListener extends EventListener {

		public void handleTick(TickEvent e);
		
	}
	
}
