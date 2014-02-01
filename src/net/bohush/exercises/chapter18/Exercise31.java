package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise31 extends JApplet{
	private static final long serialVersionUID = 1L;
	private long milliseconds = 0;
	private long startMilliseconds = 0;
	private JLabel jLabel = new JLabel("00:00:00:000");	
	private Timer timer;
	private JButton jButton1 = new JButton("Start");
	private JButton jButton2 = new JButton("Clear");
	
	public Exercise31() {
		JPanel panel1 = new JPanel();
		panel1.add(jButton1);
		panel1.add(jButton2);
		add(panel1, BorderLayout.SOUTH);
		
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		jLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
		jLabel.setBackground(Color.WHITE);
		add(jLabel, BorderLayout.CENTER);
		
		timer = new Timer(1, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				milliseconds = System.currentTimeMillis() - startMilliseconds;
				updateTime();
			}
		});
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					timer.stop();
					jButton1.setText("Resume");
				} else {
					if (startMilliseconds == 0) {
						startMilliseconds = System.currentTimeMillis();
					}
					timer.start();
					jButton1.setText("Pause");
				}
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				jButton1.setText("Start");
				jLabel.setText("00:00:00:000");
				milliseconds = 0;
				startMilliseconds = 0;
			}
		});
	}
	
	public void updateTime() {
		Time time = new Time(milliseconds);		
		jLabel.setText(time.toString());
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise31());
		frame.setTitle("Exercise31");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class Time {
		private int hour;
		private int minute;
		private int second;
		private int millis;
		
		public Time(long milliseconds) {
			setTime(milliseconds);
		}
		
		public Time() {
			setTime(System.currentTimeMillis());
		}
		
		public Time(int hour, int minute, int second, int millis) {
			this.hour = hour;
			this.minute = minute;
			this.second = second;
			this.millis = millis;
		}
		
		public int getHout() {
			return hour;
		}
		
		public int getMinute() {
			return minute;
		}
		
		public int getSecond() {
			return second;
		}
		
		public int getMilliseconds() {
			return millis;
		}
		
		public void setTime(long elapseTime) {
			millis = (int)(elapseTime % 1000);
		    long totalSeconds = elapseTime / 1000;
		    second = (int)(totalSeconds % 60);
		    long totalMinutes = totalSeconds / 60;
		    minute = (int)(totalMinutes % 60);
		    long totalHours = totalMinutes / 60;
		    hour = (int)(totalHours % 24);
		}
		
		public String toString() {
			return String.format("%02d:%02d:%02d:%03d", hour, minute, second, millis);
		}
	}

}
