package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise19 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise19() {
		setLayout(new FlowLayout(FlowLayout.CENTER));

		int hour = (int)(Math.random() * 12);
		int minute = (int)(Math.random() * 2) == 0 ? 0 : 30;
		int second = 0;
		StillClock2 clock = new StillClock2(hour, minute, second);
		clock.setSecondHandVisible(false);
		add(clock);
	}

	public static void main(String[] args) {
		Exercise19 frame = new Exercise19();
		frame.setTitle("Exercise19");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(250, 250);
		frame.setVisible(true);
	}
}

class StillClock2 extends StillClock {

	private static final long serialVersionUID = 1L;
	
	private boolean hourHandVisible = true;
	private boolean minuteHandVisible = true;
	private boolean secondHandVisible = true;

	
	public StillClock2(int hour, int minute, int second) {
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
		if (secondHandVisible) {
			int sLength = (int) (clockRadius * 0.8);
			int xSecond = (int) (xCenter + sLength
					* Math.sin(getSecond() * (2 * Math.PI / 60)));
			int ySecond = (int) (yCenter - sLength
					* Math.cos(getSecond() * (2 * Math.PI / 60)));
			g.setColor(Color.red);
			g.drawLine(xCenter, yCenter, xSecond, ySecond);
		}
		
		// Draw minute hand
		if (minuteHandVisible) {
			int mLength = (int) (clockRadius * 0.65);
			int xMinute = (int) (xCenter + mLength
					* Math.sin(getMinute() * (2 * Math.PI / 60)));
			int yMinute = (int) (yCenter - mLength
					* Math.cos(getMinute() * (2 * Math.PI / 60)));
			g.setColor(Color.blue);
			g.drawLine(xCenter, yCenter, xMinute, yMinute);
		}
		
		// Draw hour hand
		if (hourHandVisible) {
			int hLength = (int) (clockRadius * 0.5);
			int xHour = (int) (xCenter + hLength
					* Math.sin((getHour() % 12 + getMinute() / 60.0) * (2 * Math.PI / 12)));
			int yHour = (int) (yCenter - hLength
					* Math.cos((getHour() % 12 + getMinute() / 60.0) * (2 * Math.PI / 12)));
			g.setColor(Color.green);
			g.drawLine(xCenter, yCenter, xHour, yHour);
		}
	}
	
}