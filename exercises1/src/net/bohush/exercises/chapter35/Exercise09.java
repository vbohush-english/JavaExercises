package net.bohush.exercises.chapter35;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.util.*;
import java.text.*;

public class Exercise09 extends JApplet {
	private static final long serialVersionUID = 1L;

	// Create a CalendarPanel for showing calendars
	private CalendarPanel calendarPanel = new CalendarPanel();

	// Combo box for selecting available locales
	private JComboBox<String> jcboLocale = new JComboBox<>();

	// Declare locales to store available locales
	private Locale locales[] = Calendar.getAvailableLocales();

	// Buttons Prior and Next for displaying prior and next month
	private JButton jbtPrior;
	private JButton jbtNext;
	private TitledBorder titledBorder;
	private JPanel jpLocale;

	/** Initialize the applet */
	public void init() {
		ResourceBundle res = ResourceBundle.getBundle("net.bohush.exercises.chapter35.Exercise09$MyResource");
		
		jbtPrior = new JButton(res.getString("prior"));
		jbtNext = new JButton(res.getString("next"));
		
		// Panel jpLocale to hold the combo box for selecting locales
		jpLocale = new JPanel(new FlowLayout());
		titledBorder = new TitledBorder(res.getString("choose"));
		jpLocale.setBorder(titledBorder);
		jpLocale.add(jcboLocale);

		// Initialize the combo box to add locale names
		for (int i = 0; i < locales.length; i++)
			jcboLocale.addItem(locales[i].getDisplayName());

		// Panel jpButtons to hold buttons
		JPanel jpButtons = new JPanel(new FlowLayout());
		jpButtons.add(jbtPrior);
		jpButtons.add(jbtNext);

		// Panel jpCalendar to hold calendarPanel and buttons
		JPanel jpCalendar = new JPanel(new BorderLayout());
		jpCalendar.add(calendarPanel, BorderLayout.CENTER);
		jpCalendar.add(jpButtons, BorderLayout.SOUTH);

		// Place jpCalendar and jpLocale to the applet
		add(jpCalendar, BorderLayout.CENTER);
		add(jpLocale, BorderLayout.SOUTH);

		// Register listeners
		jcboLocale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jcboLocale) {
					calendarPanel.changeLocale(locales[jcboLocale.getSelectedIndex()]);
					ResourceBundle res = ResourceBundle.getBundle("net.bohush.exercises.chapter35.Exercise09$MyResource", locales[jcboLocale.getSelectedIndex()]);					
					jbtPrior.setText(res.getString("prior"));
					jbtNext.setText(res.getString("next"));
					titledBorder.setTitle(res.getString("choose"));
					jpLocale.repaint();
				}
			}
		});

		jbtPrior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int currentMonth = calendarPanel.getMonth();
				if (currentMonth == 0) // The previous month is 11 for Dec
					calendarPanel.setYear(calendarPanel.getYear() - 1);
				calendarPanel.setMonth((currentMonth - 1) % 12);
			}
		});

		jbtNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int currentMonth = calendarPanel.getMonth();
				if (currentMonth == 11) // The next month is 0 for Jan
					calendarPanel.setYear(calendarPanel.getYear() + 1);

				calendarPanel.setMonth((currentMonth + 1) % 12);
			}
		});

		calendarPanel.changeLocale(locales[jcboLocale.getSelectedIndex()]);
	}

	/** Main method */
	public static void main(String[] args) {
		// Create a frame
		JFrame frame = new JFrame("Exercise09");

		// Create an instance of the applet
		Exercise09 applet = new Exercise09();

		// Add the applet instance to the frame
		frame.getContentPane().add(applet, BorderLayout.CENTER);

		// Invoke init() and start()
		applet.init();
		applet.start();

		// Display the frame
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static class MyResource_en extends java.util.ListResourceBundle {
		static final Object[][] contents = {
			{ "choose", "Choose a locale"},
			{ "prior", "Prior" },
			{ "next", "Next" }
		};

		public Object[][] getContents() {
			return contents;
		}
	}

	public static class MyResource_uk extends java.util.ListResourceBundle {
		static final Object[][] contents = {
			{ "choose", "Виберіть локалізацію"},
			{ "prior", "Попередній" },
			{ "next", "Наступний" }
		};

		public Object[][] getContents() {
			return contents;
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
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy",
					getLocale());
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
				jlblDay[i + 7].setText(daysInPrecedingMonth
						- startingDayOfMonth + 2 + i + "");
				jpDays.add(jlblDay[i + 7]); // Add to jpDays
			}

			// Display days of this month
			int daysInCurrentMonth = calendar
					.getActualMaximum(Calendar.DAY_OF_MONTH);
			for (int i = 1; i <= daysInCurrentMonth; i++) {
				jlblDay[i - 2 + startingDayOfMonth + 7]
						.setForeground(Color.black);
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
