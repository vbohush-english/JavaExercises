package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

public class Exercise05 extends JApplet {
	StillClock3 clock;
	private static final long serialVersionUID = 1L;

	public Exercise05() {
		clock = new StillClock3();
		clock.setSecondHandVisible(false);
		setRandomTime();
		clock.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setRandomTime();	
				repaint();
			}

		});
		add(clock);
	}
	
	private  void setRandomTime() {
		int hours = (int) (Math.random() * 12);
		int minute = (int) (Math.random() * 4) * 15;
		int seconds = 0;
		clock.setHour(hours);
		clock.setMinute(minute);
		clock.setSecond(seconds);
	}
	
	class StillClock3 extends StillClock {

		private static final long serialVersionUID = 1L;
		
		private boolean hourHandVisible = true;
		private boolean minuteHandVisible = true;
		private boolean secondHandVisible = true;

		public StillClock3() {
			super();
		}
		
		public StillClock3(int hour, int minute, int second) {
			super(hour, minute, second);
		}
		
		public boolean isHourHandVisible() {
			return hourHandVisible;
		}

		public void setHourHandVisible(boolean hourHandVisible) {
			this.hourHandVisible = hourHandVisible;
		}

		public boolean isMinuteHandVisible() {
			return minuteHandVisible;
		}

		public void setMinuteHandVisible(boolean minuteHandVisible) {
			this.minuteHandVisible = minuteHandVisible;
		}

		public boolean isSecondHandVisible() {
			return secondHandVisible;
		}

		public void setSecondHandVisible(boolean secondHandVisible) {
			this.secondHandVisible = secondHandVisible;
		}
		

		@Override
		/** Draw the clock */
		protected void paintComponent(Graphics g) {

			// Initialize clock parameters
			int radius = (int) (Math.min(getWidth(), getHeight()) * 0.4);
			int xCenter = getWidth() / 2;
			int yCenter = getHeight() / 2;

			// Draw circle
			g.setColor(Color.black);
			g.drawOval(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius);
			g.drawLine(xCenter + radius - 15, yCenter, xCenter + radius, yCenter);
			g.drawString("3", xCenter + radius - 25, yCenter);
			final int POINTS_COUNT = 60;
			int hour = 2;
			for (int i = 1; i < POINTS_COUNT; i++) {
				int minusRadius = i % 5 == 0 ? 15 : 5;
				int x1 = (int) (xCenter + (radius - minusRadius) * Math.cos(i * 2 * Math.PI / POINTS_COUNT));
				int y1 = (int) (yCenter - (radius - minusRadius) * Math.sin(i * 2 * Math.PI / POINTS_COUNT));
				int x2 = (int) (xCenter + radius * Math.cos(i * 2 * Math.PI / POINTS_COUNT));
				int y2 = (int) (yCenter - radius * Math.sin(i * 2 * Math.PI / POINTS_COUNT));
				g.drawLine(x1, y1, x2, y2);
				if (i % 5 == 0) {
					int x3 = (int) (xCenter + (radius - 25) * Math.cos(i * 2 * Math.PI / POINTS_COUNT));
					int y3 = (int) (yCenter - (radius - 25) * Math.sin(i * 2 * Math.PI / POINTS_COUNT));
					if (hour == 0) {
						hour = 12;
					}
					g.drawString((hour--) + "", x3, y3);
				}
			}

			// Draw second hand
			if (secondHandVisible) {
				int sLength = (int) (radius * 0.8);
				int xSecond = (int) (xCenter + sLength
						* Math.sin(getSecond() * (2 * Math.PI / 60)));
				int ySecond = (int) (yCenter - sLength
						* Math.cos(getSecond() * (2 * Math.PI / 60)));
				g.setColor(Color.red);
				g.drawLine(xCenter, yCenter, xSecond, ySecond);
			}
			
			// Draw minute hand
			if (minuteHandVisible) {
				int mLength = (int) (radius * 0.65);
				int xMinute = (int) (xCenter + mLength
						* Math.sin(getMinute() * (2 * Math.PI / 60)));
				int yMinute = (int) (yCenter - mLength
						* Math.cos(getMinute() * (2 * Math.PI / 60)));
				g.setColor(Color.blue);
				g.drawLine(xCenter, yCenter, xMinute, yMinute);
			}
			
			// Draw hour hand
			if (hourHandVisible) {
				int hLength = (int) (radius * 0.5);
				int xHour = (int) (xCenter + hLength
						* Math.sin((getHour() % 12 + getMinute() / 60.0) * (2 * Math.PI / 12)));
				int yHour = (int) (yCenter - hLength
						* Math.cos((getHour() % 12 + getMinute() / 60.0) * (2 * Math.PI / 12)));
				g.setColor(Color.green);
				g.drawLine(xCenter, yCenter, xHour, yHour);
			}

		}

	}

	
	class StillClock extends JPanel {

		private static final long serialVersionUID = 1L;
		private int hour;
		private int minute;
		private int second;

		/** Construct a default clock with the current time */
		public StillClock() {
			setCurrentTime();
		}

		/** Construct a clock with specified hour, minute, and second */
		public StillClock(int hour, int minute, int second) {
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}

		/** Return hour */
		public int getHour() {
			return hour;
		}

		/** Set a new hour */
		public void setHour(int hour) {
			this.hour = hour;
			repaint();
		}

		/** Return minute */
		public int getMinute() {
			return minute;
		}

		/** Set a new minute */
		public void setMinute(int minute) {
			this.minute = minute;
			repaint();
		}

		/** Return second */
		public int getSecond() {
			return second;
		}

		/** Set a new second */
		public void setSecond(int second) {
			this.second = second;
			repaint();
		}

		@Override
		/** Draw the clock */
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Initialize clock parameters
			int clockRadius = (int) (Math.min(getWidth(), getHeight()) * 0.8 * 0.5);
			int xCenter = getWidth() / 2;
			int yCenter = getHeight() / 2;

			// Draw circle
			g.setColor(Color.black);
			g.drawOval(xCenter - clockRadius, yCenter - clockRadius,
					2 * clockRadius, 2 * clockRadius);
			g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
			g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
			g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
			g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);

			// Draw second hand
			int sLength = (int) (clockRadius * 0.8);
			int xSecond = (int) (xCenter + sLength
					* Math.sin(second * (2 * Math.PI / 60)));
			int ySecond = (int) (yCenter - sLength
					* Math.cos(second * (2 * Math.PI / 60)));
			g.setColor(Color.red);
			g.drawLine(xCenter, yCenter, xSecond, ySecond);

			// Draw minute hand
			int mLength = (int) (clockRadius * 0.65);
			int xMinute = (int) (xCenter + mLength
					* Math.sin(minute * (2 * Math.PI / 60)));
			int yMinute = (int) (yCenter - mLength
					* Math.cos(minute * (2 * Math.PI / 60)));
			g.setColor(Color.blue);
			g.drawLine(xCenter, yCenter, xMinute, yMinute);

			// Draw hour hand
			int hLength = (int) (clockRadius * 0.5);
			int xHour = (int) (xCenter + hLength
					* Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
			int yHour = (int) (yCenter - hLength
					* Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
			g.setColor(Color.green);
			g.drawLine(xCenter, yCenter, xHour, yHour);
		}

		public void setCurrentTime() {
			// Construct a calendar for the current date and time
			Calendar calendar = new GregorianCalendar();

			// Set current hour, minute and second
			this.hour = calendar.get(Calendar.HOUR_OF_DAY);
			this.minute = calendar.get(Calendar.MINUTE);
			this.second = calendar.get(Calendar.SECOND);
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(200, 200);
		}
	}
}

