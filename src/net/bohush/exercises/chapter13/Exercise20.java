package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise20 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise20() {
		// Create an analog clock for the current time
		StillClock3 clock = new StillClock3();

		// Display hour, minute, and seconds in the message panel
		MessagePanel messagePanel = new MessagePanel(clock.getHour() + ":"
				+ clock.getMinute() + ":" + clock.getSecond());
		messagePanel.setCentered(true);
		messagePanel.setForeground(Color.blue);
		messagePanel.setFont(new Font("Courie", Font.BOLD, 16));

		// Add the clock and message panel to the frame
		add(clock);
		add(messagePanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		Exercise20 frame = new Exercise20();
		frame.setTitle("Exercise20");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 350);
		frame.setVisible(true);
	}
}

class StillClock3 extends StillClock {

	private static final long serialVersionUID = 1L;

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
		int sLength = (int) (radius * 0.8);
		int xSecond = (int) (xCenter + sLength
				* Math.sin(getSecond() * (2 * Math.PI / 60)));
		int ySecond = (int) (yCenter - sLength
				* Math.cos(getSecond() * (2 * Math.PI / 60)));
		g.setColor(Color.red);
		g.drawLine(xCenter, yCenter, xSecond, ySecond);

		// Draw minute hand
		int mLength = (int) (radius * 0.65);
		int xMinute = (int) (xCenter + mLength
				* Math.sin(getMinute() * (2 * Math.PI / 60)));
		int yMinute = (int) (yCenter - mLength
				* Math.cos(getMinute() * (2 * Math.PI / 60)));
		g.setColor(Color.blue);
		g.drawLine(xCenter, yCenter, xMinute, yMinute);

		// Draw hour hand
		int hLength = (int) (radius * 0.5);
		int xHour = (int) (xCenter + hLength
				* Math.sin((getHour() % 12 + getMinute() / 60.0)
						* (2 * Math.PI / 12)));
		int yHour = (int) (yCenter - hLength
				* Math.cos((getHour() % 12 + getMinute() / 60.0)
						* (2 * Math.PI / 12)));
		g.setColor(Color.green);
		g.drawLine(xCenter, yCenter, xHour, yHour);

	}

}

class MessagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** The message to be displayed */
	private String message = "Welcome to Java";

	/** The x coordinate where the message is displayed */
	private int xCoordinate = 20;

	/** The y coordinate where the message is displayed */
	private int yCoordinate = 20;

	/** Indicate whether the message is displayed in the center */
	private boolean centered;

	/**
	 * The interval for moving the message horizontally and vertically
	 */
	private int interval = 10;

	/** Construct with default properties */
	public MessagePanel() {
	}

	/** Construct a message panel with a specified message */
	public MessagePanel(String message) {
		this.message = message;
	}

	/** Return message */
	public String getMessage() {
		return message;
	}

	/** Set a new message */
	public void setMessage(String message) {
		this.message = message;
		repaint();
	}

	/** Return xCoordinator */
	public int getXCoordinate() {
		return xCoordinate;
	}

	/** Set a new xCoordinator */
	public void setXCoordinate(int x) {
		this.xCoordinate = x;
		repaint();
	}

	/** Return yCoordinator */
	public int getYCoordinate() {
		return yCoordinate;
	}

	/** Set a new yCoordinator */
	public void setYCoordinate(int y) {
		this.yCoordinate = y;
		repaint();
	}

	/** Return centered */
	public boolean isCentered() {
		return centered;
	}

	/** Set a new centered */
	public void setCentered(boolean centered) {
		this.centered = centered;
		repaint();
	}

	/** Return interval */
	public int getInterval() {
		return interval;
	}

	/** Set a new interval */
	public void setInterval(int interval) {
		this.interval = interval;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (centered) {
			// Get font metrics for the current font
			FontMetrics fm = g.getFontMetrics();

			// Find the center location to display
			int stringWidth = fm.stringWidth(message);
			int stringAscent = fm.getAscent();
			// Get the position of the leftmost character in the baseline
			xCoordinate = getWidth() / 2 - stringWidth / 2;
			yCoordinate = getHeight() / 2 + stringAscent / 2;
		}

		g.drawString(message, xCoordinate, yCoordinate);
	}

	/** Move the message left */
	public void moveLeft() {
		xCoordinate -= interval;
		repaint();
	}

	/** Move the message right */
	public void moveRight() {
		xCoordinate += interval;
		repaint();
	}

	/** Move the message up */
	public void moveUp() {
		yCoordinate -= interval;
		repaint();
	}

	/** Move the message down */
	public void moveDown() {
		yCoordinate += interval;
		repaint();
	}

	/** Override get method for preferredSize */
	public Dimension getPreferredSize() {
		return new Dimension(200, 30);
	}
}