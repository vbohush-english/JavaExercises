package net.bohush.exercises.chapter36;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Exercise05 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel jLabel;
	
	public static void main(String[] args) {
		Exercise05 frame = new Exercise05();
		frame.pack();
		frame.setTitle("Exercise05");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public Exercise05() {
		setLayout(new BorderLayout(10, 10));
		ClockWithAlarm clockWithAlarm = new ClockWithAlarm("London", "GMT+1");

		clockWithAlarm.setTimeStyle(DateFormat.LONG);
		clockWithAlarm.setAlarmDate("2014,6,1");
		clockWithAlarm.setAlarmTime("20,55,10");
		add(clockWithAlarm, BorderLayout.CENTER);
		jLabel = new JLabel("Alart set to " + clockWithAlarm.getAlarmDate() + " " + clockWithAlarm.getAlarmTime());
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		add(jLabel, BorderLayout.SOUTH);
		clockWithAlarm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You have an appointment now");
				jLabel.setVisible(false);
			}
		});
	}
	
	static class ClockWithAlarm extends Exercise04.Clock {
		private static final long serialVersionUID = 1L;
		
		private ArrayList<ActionListener> actionListenerList; 
		private Timer timer;
		
		private String alarmDate = null;
		private String alarmTime = null;
		private int alarmYear;
		private int alarmMonth;
		private int alarmDay;
		private int alarmHour;
		private int alarmMinute;
		private int alarmSecond;

		public String getAlarmTime() {
			return alarmTime;
		}

		public void setAlarmTime(String alarmTime) {
			int[] time = parseData(alarmTime);
			this.alarmTime = alarmTime;
			alarmHour = time[0];
			alarmMinute = time[1];
			alarmSecond = time[2];
			if(alarmDate != null) {
				timer.start();
			}
		}

		public String getAlarmDate() {
			return alarmDate;
		}

		public void setAlarmDate(String alarmDate) {
			int[] date = parseData(alarmDate);
			this.alarmDate = alarmDate;
			alarmYear = date[0];
			alarmMonth = date[1];
			alarmDay = date[2];
			if(alarmTime != null) {
				timer.start();
			}
		}
		
		public void disableAlarm() {
			alarmDate = null;
			alarmTime = null;
			timer.stop();
		}
		
		private static int[] parseData(String data) {
			int[] result = new int[3];
			String[] integers = data.split(",");
			if(integers.length != 3) {
				throw new IllegalArgumentException();
			}
			try {
				result[0] = Integer.parseInt(integers[0]);
				result[1] = Integer.parseInt(integers[1]);
				result[2] = Integer.parseInt(integers[2]);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException();
			}
			return result;
		}
		
		public ClockWithAlarm() {
			this(null);
		}

		public ClockWithAlarm(String header, String timeZoneID) {
			super(header, timeZoneID);
			timer = new Timer(500, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					Calendar calendar;
					if(ClockWithAlarm.this.isUsingTimeZoneID()) {
						calendar = new GregorianCalendar(ClockWithAlarm.this.getTimeZone(), getLocale());
					} else {
						calendar = new GregorianCalendar(getLocale());
					}
					
					int currentYear = calendar.get(Calendar.YEAR);
					int currentMonth = calendar.get(Calendar.MONTH) + 1;
					int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
					int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
					int currentMinute = calendar.get(Calendar.MINUTE);
					int currentSecond = calendar.get(Calendar.SECOND);
					if((currentYear == alarmYear) &&
						(currentMonth == alarmMonth) &&
						(currentDay == alarmDay) &&
						(currentHour == alarmHour) &&
						(currentMinute == alarmMinute) &&
						(currentSecond == alarmSecond)) {
						disableAlarm();
						processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
					}
				}
			});
			timer.stop();
		}

		public ClockWithAlarm(String timeZoneID) {
			this("", timeZoneID);
		}

		@Override
		public String toString() {
			return "[" + alarmDay + "."	+ alarmMonth + "." + alarmYear + ", " + alarmHour + ":" + alarmMinute + ":" + alarmSecond + "]";
		}

		public synchronized void addActionListener(ActionListener listener) {
			if (actionListenerList == null) {
				actionListenerList = new ArrayList<ActionListener>();
			}

			if (!actionListenerList.contains(listener)) {
				actionListenerList.add(listener);
			}
		}
		
		public synchronized void removeActionListener(ActionListener listener) {
			if ((actionListenerList != null) && (actionListenerList.contains(listener))) {
				actionListenerList.remove(listener);
			}
		}
		
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

	}
}
