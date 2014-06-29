package net.bohush.exercises.chapter18;

import javax.swing.*;
import java.awt.*;

public class Exercise04 extends JApplet {
	private static final long serialVersionUID = 1L;

	/** Initialize the applet */
	public void init() {
		// Get parameter values from the HTML file
		String message = getParameter("MESSAGE");
		int x = Integer.parseInt(getParameter("X"));
		int y = Integer.parseInt(getParameter("Y"));
		int fontsize = Integer.parseInt(getParameter("FONTSIZE"));
		String fontname = getParameter("FONTNAME");
		String colorString = getParameter("COLOR");
		

		// Create a message panel
		MessagePanel messagePanel = new MessagePanel(message);
		messagePanel.setFont(new Font(fontname, Font.PLAIN, fontsize));
		Color color;
		switch (colorString) {
		case "blue": color = Color.BLUE; break;
		case "cyan": color = Color.CYAN; break;
		case "green": color = Color.GREEN; break;
		case "gray": color = Color.GRAY; break;
		case "magenta": color = Color.MAGENTA; break;
		case "orange": color = Color.ORANGE; break;
		case "pink": color = Color.PINK; break;
		case "red": color = Color.RED; break;
		case "white": color = Color.WHITE; break;
		case "yellow": color = Color.YELLOW; break;
		default: color = Color.BLACK; break;
		}
		messagePanel.setForeground(color);
		messagePanel.setXCoordinate(x);
		messagePanel.setYCoordinate(y);

		// Add the message panel to the applet
		add(messagePanel);
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
}