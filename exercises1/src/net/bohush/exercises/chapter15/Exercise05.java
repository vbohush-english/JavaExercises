package net.bohush.exercises.chapter15;

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise05  extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Exercise05() {
		setLayout(new BorderLayout(5, 5));
		
		Calendar date = new GregorianCalendar();
		JPanel head = new JPanel(new GridLayout(2, 1, 5, 5));
		JLabel label = new JLabel((date.get(Calendar.MONDAY) + 1) + "/" + date.get(Calendar.YEAR), JLabel.CENTER);
		head.add(label);
		
		String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		JPanel days = new JPanel(new GridLayout(1, 7, 0, 0));
		for (int i = 0; i < 7; i++) {
			days.add(new JLabel(dayNames[i], JLabel.CENTER));
		}
		
		head.add(days);
		add(head, BorderLayout.NORTH);
		
		JPanel months = new JPanel(new GridLayout(0, 7, 0, 0));
		date.set(Calendar.DAY_OF_MONTH, 1);
		int startDay = date.get(Calendar.DAY_OF_WEEK) - 2;	
		if (startDay == -1) {
			startDay = 6;
		}
		int endDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < endDay + startDay; i++) {
			if (i >= startDay) {
				JLabel tmp = new JLabel((i - startDay + 1) + "", JLabel.CENTER);
				tmp.setBorder(new LineBorder(Color.BLACK));
				months.add(tmp);
			} else {
				JLabel tmp = new JLabel();
				months.add(tmp);				
			}
		}
		add(months, BorderLayout.CENTER);
	}

	public static void main(String[] args){
		Exercise05 frame = new Exercise05();
		frame.setSize(500, 200);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Exercise05");
		frame.setVisible(true);

	}

}