package net.bohush.exercises.chapter36;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class Exercise01 extends JFrame {
	private static final long serialVersionUID = 1L;
	private MessagePanelWithActionEvent messagePanel;

	public Exercise01() {
		messagePanel = new MessagePanelWithActionEvent(new Date().toString());
		messagePanel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				messagePanel.setMessage(new Date().toString());
			}
		});

		messagePanel.setCentered(true);
		add(messagePanel);
	}

	public static void main(String[] args) {
		Exercise01 frame = new Exercise01();
		frame.setSize(300, 200);
		frame.setTitle("Exercise01");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	 class MessagePanelWithActionEvent extends JPanel {
		private static final long serialVersionUID = 1L;

		/** The message to be displayed */
		private String message;

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

		private ArrayList<ActionListener> actionListenerList;

		/** Register an action event listener */
		public synchronized void addActionListener(ActionListener listener) {
			if (actionListenerList == null) {
				actionListenerList = new ArrayList<ActionListener>(2);
			}

			if (!actionListenerList.contains(listener)) {
				actionListenerList.add(listener);
			}
		}

		/** Remove an action event listener */
		public synchronized void removeActionListener(ActionListener listener) {
			if (actionListenerList != null && actionListenerList.contains(listener)) {
				actionListenerList.remove(listener);
			}
		}

		/** Fire ActionEvent */
		@SuppressWarnings("unchecked")
		private void processEvent(ActionEvent e) {
			ArrayList<ActionListener> list;

			synchronized (this) {
				if (actionListenerList == null) {
					return;
				}				
				list = (ArrayList<ActionListener>) actionListenerList.clone();
			}
			for (int i = 0; i < list.size(); i++) {
				ActionListener listener = (ActionListener) list.get(i);
				listener.actionPerformed(e);
			}
		}

		/** Construct with default properties */
		public MessagePanelWithActionEvent() {
			this("Welcome to Java");
		}

		/** Construct a message panel with a specified message */
		public MessagePanelWithActionEvent(String message) {
			this.message = message;
			addMouseListener(new MouseAdapter() {				
				@Override
				public void mouseReleased(MouseEvent e) {
					processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
				}
			});
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
