package net.bohush.exercises.chapter35;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import java.text.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Exercise03 extends JApplet {
	private static final long serialVersionUID = 1L;

	public void init() {
		JPanel jpMain = new JPanel(new BorderLayout(5, 5));
		jpMain.setBorder(new EmptyBorder(5,  5,  5,  5));
		jpMain.add(new CalendarPanel(), BorderLayout.CENTER);
		jpMain.add(new WorldClock(), BorderLayout.EAST);
		add(jpMain, BorderLayout.CENTER);

	}

	/** Main method */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise03");
		Exercise03 applet = new Exercise03();
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class WorldClock extends JPanel {
		private static final long serialVersionUID = 1L;
		private TimeZone timeZone = TimeZone.getTimeZone("EST");
		private Timer timer = new Timer(1000, new TimerListener());
		private StillClock clock = new StillClock();
		private JLabel jlblDigitTime = new JLabel("", JLabel.CENTER);

		public WorldClock() {
			setLayout(new BorderLayout());
			add(clock, BorderLayout.CENTER);
			add(jlblDigitTime, BorderLayout.SOUTH);
			timer.start();
		}

		public void setTimeZone(TimeZone timeZone) {
			this.timeZone = timeZone;
		}

		private class TimerListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = new GregorianCalendar(timeZone, getLocale());
				clock.setHour(calendar.get(Calendar.HOUR));
				clock.setMinute(calendar.get(Calendar.MINUTE));
				clock.setSecond(calendar.get(Calendar.SECOND));

				// Display digit time on the label
				DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, getLocale());
				formatter.setTimeZone(timeZone);
				jlblDigitTime.setText(formatter.format(calendar.getTime()));
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

	class CalendarPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		// The header label
		private JLabel jlblHeader = new JLabel(" ", JLabel.CENTER);

		// Maximun number of labels to display day names and days
		private JLabel[] jlblDay = new JLabel[49];

		private java.util.Calendar calendar;
		private int month; // The specified month
		private int year; // The specified year

		// Panel jpDays to hold day names and days
		private JPanel jpDays = new JPanel(new GridLayout(0, 7));

		public CalendarPanel() {
			// Create labels for displaying days
			for (int i = 0; i < 49; i++) {
				jlblDay[i] = new JLabel();
				jlblDay[i].setBorder(new LineBorder(Color.black, 1));
				jlblDay[i].setHorizontalAlignment(JLabel.RIGHT);
				jlblDay[i].setVerticalAlignment(JLabel.TOP);
			}

			// Place header and calendar body in the panel
			this.setLayout(new BorderLayout());
			this.add(jlblHeader, BorderLayout.NORTH);
			this.add(jpDays, BorderLayout.CENTER);

			// Set current month and year
			calendar = new GregorianCalendar();
			month = calendar.get(Calendar.MONTH);
			year = calendar.get(Calendar.YEAR);
			updateCalendar();

			// Show calendar
			showHeader();
			showDays();
		}

		/** Update the header based on locale */
		private void showHeader() {
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", getLocale());
			String header = sdf.format(calendar.getTime());
			jlblHeader.setText(header);
		}

		/** Update the day names based on locale */
		private void showDayNames() {
			DateFormatSymbols dfs = new DateFormatSymbols(getLocale());
			String dayNames[] = dfs.getWeekdays();

			// jlblDay[0], jlblDay[1], ..., jlblDay[6] for day names
			for (int i = 0; i < 7; i++) {
				jlblDay[i].setText(dayNames[i + 1]);
				jlblDay[i].setHorizontalAlignment(JLabel.CENTER);
				jpDays.add(jlblDay[i]); // Add to jpDays
			}
		}

		/** Display days */
		public void showDays() {
			jpDays.removeAll(); // Remove all labels from jpDays

			showDayNames(); // Display day names

			// Get the day of the first day in a month
			int startingDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

			// Fill the calendar with the days before this month
			Calendar cloneCalendar = (Calendar) calendar.clone();
			cloneCalendar.add(Calendar.DATE, -1); // Becomes preceding month
			int daysInPrecedingMonth = cloneCalendar
					.getActualMaximum(Calendar.DAY_OF_MONTH);

			for (int i = 0; i < startingDayOfMonth - 1; i++) {
				jlblDay[i + 7].setForeground(Color.LIGHT_GRAY);
				jlblDay[i + 7].setText(daysInPrecedingMonth - startingDayOfMonth
						+ 2 + i + "");
				jpDays.add(jlblDay[i + 7]); // Add to jpDays
			}

			// Display days of this month
			int daysInCurrentMonth = calendar
					.getActualMaximum(Calendar.DAY_OF_MONTH);
			for (int i = 1; i <= daysInCurrentMonth; i++) {
				jlblDay[i - 2 + startingDayOfMonth + 7].setForeground(Color.black);
				jlblDay[i - 2 + startingDayOfMonth + 7].setText(i + "");
				jpDays.add(jlblDay[i - 2 + startingDayOfMonth + 7]);
			}

			// Fill the calendar with the days after this month
			int j = 1;
			for (int i = daysInCurrentMonth - 1 + startingDayOfMonth + 7; i % 7 != 0; i++) {
				jlblDay[i].setForeground(Color.LIGHT_GRAY);
				jlblDay[i].setText(j++ + "");
				jpDays.add(jlblDay[i]); // Add to jpDays
			}

			jpDays.repaint(); // Repaint the labels in jpDays
		}

		/**
		 * Set the calendar to the first day of the specified month and year
		 */
		private void updateCalendar() {
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DATE, 1);
		}

		/** Return month */
		public int getMonth() {
			return month;
		}

		/** Set a new month */
		public void setMonth(int newMonth) {
			month = newMonth;
			updateCalendar();
			showHeader();
			showDays();
		}

		/** Return year */
		public int getYear() {
			return year;
		}

		/** Set a new year */
		public void setYear(int newYear) {
			year = newYear;
			updateCalendar();
			showHeader();
			showDays();
		}

		/** Set a new locale */
		public void changeLocale(Locale newLocale) {
			setLocale(newLocale);
			showHeader();
			showDays();
		}
	}
}