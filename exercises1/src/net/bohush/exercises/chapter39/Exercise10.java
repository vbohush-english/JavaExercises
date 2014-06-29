package net.bohush.exercises.chapter39;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.border.*;

public class Exercise10 extends JApplet {
	private static final long serialVersionUID = 1L;
	private final static int NUMBER_OF_CLOCKS = 3;
	private Clock[] clocks = new Clock[NUMBER_OF_CLOCKS];

	// Create a list model
	private DefaultListModel<Clock> listModel = new DefaultListModel<Clock>();

	// Create a list using the list model
	private JList<Clock> jlstClocks = new JList<Clock>(listModel);

	// Create a list cell renderer
	private ListCellRenderer<Clock> renderer = new MyListCellRenderer<Clock>();

	// Create a split pane
	private JSplitPane jSplitPane1 = new JSplitPane();

	private JPanel clockPanel = new JPanel(new BorderLayout());

	/** Construct ListCellRenderer */
	public Exercise10() {
		// Load small and large image icons
		clocks[0] = new Clock();
		clocks[0].setHeader("Kyiv");
		clocks[1] = new Clock("London", "GMT+1"); 
		clocks[2] = new Clock("New York", "GMT-4");
		for (int i = 0; i < NUMBER_OF_CLOCKS; i++) {
			listModel.addElement(clocks[i]);
		}

		// Set list cell renderer
		jlstClocks.setCellRenderer(renderer);
		jlstClocks.setPreferredSize(new Dimension(200, 200));
		jSplitPane1.setLeftComponent(new JScrollPane(jlstClocks));
		jSplitPane1.setRightComponent(clockPanel);
		jSplitPane1.setDividerLocation(250);
		add(jSplitPane1, BorderLayout.CENTER);

		// Register listener
		jlstClocks.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Clock selectedClock = jlstClocks.getSelectedValue();
				Clock newClock = new Clock(selectedClock.getHeader(), selectedClock.getTimeZoneID());
				newClock.setDateStyle(selectedClock.getDateStyle());
				newClock.setDigitalDateTimeColor(selectedClock.getDigitalDateTimeColor());
				newClock.setHeadColor(selectedClock.getHeadColor());
				newClock.setHourHandColor(selectedClock.getHourHandColor());
				newClock.setMinuteHandColor(selectedClock.getMinuteHandColor());
				newClock.setSecondHandColor(selectedClock.getSecondHandColor());
				newClock.setShowingDigitalDateTime(selectedClock.isShowingDigitalDateTime());
				newClock.setTimeStyle(selectedClock.getTimeStyle());
				clockPanel.removeAll();
				clockPanel.add(newClock);
				clockPanel.updateUI();

			}
		});
		
		Timer timer = new Timer(1000, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jlstClocks.repaint();
			}
		});
		timer.start();
	}

	public static void main(String[] args) {
		Exercise10 applet = new Exercise10();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise10");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public class MyListCellRenderer<E> implements ListCellRenderer<E> {
		private Border lineBorder = BorderFactory.createLineBorder(Color.black,	1);
		private Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

		/** Implement this method in ListCellRenderer */
		public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus) {
			Clock clock = (Clock) value;
			if (isSelected) {
				clock.setForeground(list.getSelectionForeground());
				clock.setBackground(list.getSelectionBackground());
			} else {
				clock.setForeground(list.getForeground());
				clock.setBackground(list.getBackground());
			}

			clock.setBorder(cellHasFocus ? lineBorder : emptyBorder);
			return clock;
		}
	}

	public  class Clock extends JPanel {
		private static final long serialVersionUID = 1L;

		private Color headColor;
		private Color hourHandColor = Color.green;
		private Color minuteHandColor = Color.blue;
		private Color secondHandColor = Color.red;
		private String header;
		private int dateStyle = DateFormat.SHORT;
		private int timeStyle = DateFormat.SHORT;
		private Color digitalDateTimeColor;
		private boolean showingDigitalDateTime;
		private boolean showingHeader;
		private boolean running = true;
	
		private String timeZoneID;
		private TimeZone timeZone;
		private int timeZoneOffset;	
		private boolean usingTimeZoneID = true;
	
		private Timer timer = new Timer(1000, new TimerListener());
		private StillClock clock;
		private JLabel jlblDigitTime = new JLabel("", JLabel.CENTER);
		private JLabel jlblHeader = new JLabel("", JLabel.CENTER);
		
		public Color getHeadColor() {
			return headColor;
		}

		public void setHeadColor(Color headColor) {
			this.headColor = headColor;
			jlblHeader.setForeground(headColor);
		}

		public Color getHourHandColor() {
			return hourHandColor;
		}

		public void setHourHandColor(Color hourHandColor) {
			this.hourHandColor = hourHandColor;
		}

		public Color getMinuteHandColor() {
			return minuteHandColor;
		}

		public void setMinuteHandColor(Color minuteHandColor) {
			this.minuteHandColor = minuteHandColor;
		}

		public Color getSecondHandColor() {
			return secondHandColor;
		}

		public void setSecondHandColor(Color secondHandColor) {
			this.secondHandColor = secondHandColor;
		}
		
		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
			jlblHeader.setText(header);
		}
		
		public int getDateStyle() {
			return dateStyle;
		}

		public void setDateStyle(int dateStyle) {
			this.dateStyle = dateStyle;
		}
		
		public int getTimeStyle() {
			return timeStyle;
		}

		public void setTimeStyle(int timeStyle) {
			this.timeStyle = timeStyle;
		}

		public Color getDigitalDateTimeColor() {
			return digitalDateTimeColor;
		}

		public void setDigitalDateTimeColor(Color digitalDateTimeColor) {
			this.digitalDateTimeColor = digitalDateTimeColor;
			jlblDigitTime.setForeground(digitalDateTimeColor);
		}

		public boolean isShowingDigitalDateTime() {
			return showingDigitalDateTime;
		}

		public void setShowingDigitalDateTime(boolean showingDigitalDateTime) {
			this.showingDigitalDateTime = showingDigitalDateTime;
			jlblDigitTime.setVisible(showingDigitalDateTime);
		}

		public boolean isShowingHeader() {
			return showingHeader;
		}

		public void setShowingHeader(boolean showingHeader) {
			this.showingHeader = showingHeader;
			jlblHeader.setVisible(showingHeader);
		}

		public void start() {
			running = true;
			refreshTime();
			timer.start();
		}
		
		public void stop() {
			running = false;
			timer.stop();
		}
		
		public boolean isRunning() {
			return running;
		}
		
		
		public int getTimeZoneOffset() {
			return timeZoneOffset;
		}

		public boolean isUsingTimeZoneID() {
			return usingTimeZoneID;
		}
		
		public String getTimeZoneID() {
			return timeZoneID;
		}

		public TimeZone getTimeZone() {
			return timeZone;
		}

		public Clock(String header, String timeZoneID) {
			if(timeZoneID == null) {
				this.timeZoneID = null;
				timeZone = null;
				usingTimeZoneID = false;
				timeZoneOffset = 0;				
			} else {
				this.timeZoneID = timeZoneID;
				timeZone = TimeZone.getTimeZone(timeZoneID);
				usingTimeZoneID = true;
				timeZoneOffset = timeZone.getOffset(System.currentTimeMillis());
			}			
			setLayout(new BorderLayout());
			add(jlblHeader, BorderLayout.NORTH);
			clock = new StillClock();
			add(clock, BorderLayout.CENTER);
			add(jlblDigitTime, BorderLayout.SOUTH);
			setDigitalDateTimeColor(Color.MAGENTA);
			setHeadColor(Color.DARK_GRAY);
			refreshTime();
			setShowingDigitalDateTime(true);
			setShowingHeader(true);
			start();
			setHeader(header);
		}

		public Clock(String timeZoneID) {
			this("", timeZoneID);
		}
		
		public Clock() {
			this(null);
		}
		
		private class TimerListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTime();
			}
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(200, 200);
		}
		
		private void refreshTime() {
			Calendar calendar;
			if(usingTimeZoneID) {
				calendar = new GregorianCalendar(timeZone, getLocale());
			} else {
				calendar = new GregorianCalendar();
			}
			clock.setHour(calendar.get(Calendar.HOUR));
			clock.setMinute(calendar.get(Calendar.MINUTE));
			clock.setSecond(calendar.get(Calendar.SECOND));

			// Display digit time on the label
			DateFormat formatter = DateFormat.getDateTimeInstance(dateStyle, timeStyle, getLocale());
			if(usingTimeZoneID) {
				formatter.setTimeZone(timeZone);	
			}			
			jlblDigitTime.setText(formatter.format(calendar.getTime()));
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
				int radius = (int) (Math.min(getWidth(), getHeight()) * 0.4);

				// Draw circle
				g.setColor(Color.BLACK);
				g.drawOval(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius);
				g.drawLine(xCenter + radius - 15, yCenter, xCenter + radius, yCenter);
				g.drawString("3", xCenter + radius - 25, yCenter);
				final int POINTS_COUNT = 60;
				int hourLines = 2;
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
						if (hourLines == 0) {
							hourLines = 12;
						}
						g.drawString((hourLines--) + "", x3, y3);
					}
				}

				// Draw second hand
				int sLength = (int) (clockRadius * 0.8);
				int xSecond = (int) (xCenter + sLength * Math.sin(second * (2 * Math.PI / 60)));
				int ySecond = (int) (yCenter - sLength * Math.cos(second * (2 * Math.PI / 60)));
				g.setColor(secondHandColor);
				g.drawLine(xCenter, yCenter, xSecond, ySecond);

				// Draw minute hand
				int mLength = (int) (clockRadius * 0.65);
				int xMinute = (int) (xCenter + mLength * Math.sin(minute * (2 * Math.PI / 60)));
				int yMinute = (int) (yCenter - mLength * Math.cos(minute * (2 * Math.PI / 60)));
				g.setColor(minuteHandColor);
				g.drawLine(xCenter, yCenter, xMinute, yMinute);

				// Draw hour hand
				int hLength = (int) (clockRadius * 0.5);
				int xHour = (int) (xCenter + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
				int yHour = (int) (yCenter - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
				g.setColor(hourHandColor);
				g.drawLine(xCenter, yCenter, xHour, yHour);
			}

			public void setCurrentTime() {
				// Construct a calendar for the current date and time
				Calendar calendar;
				if(usingTimeZoneID) {
					calendar = new GregorianCalendar(timeZone, getLocale());
				} else {
					calendar = new GregorianCalendar(getLocale());
				}
				
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
	
}
