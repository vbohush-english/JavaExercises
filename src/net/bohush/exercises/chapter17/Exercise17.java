package net.bohush.exercises.chapter17;

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise17  extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Exercise17() {
		
		Calendar date = new GregorianCalendar();
		
		String[] monthsNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		JLabel label = new JLabel(monthsNames[date.get(Calendar.MONDAY)] + " " + date.get(Calendar.YEAR), JLabel.CENTER);
		add(label, BorderLayout.NORTH);
		
		
		JPanel months = new JPanel(new GridLayout(0, 7, 0, 0));
		String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for (int i = 0; i < 7; i++) {
			JLabel tmp = new JLabel(dayNames[i], JLabel.CENTER);
			tmp.setBorder(new LineBorder(Color.BLACK));
			months.add(tmp);
		}
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
		
		JPanel navigationPanel = new JPanel();
		JButton jbtnPrior = new JButton("Prior");
		JButton jbtnNext = new JButton("Next");
		navigationPanel.add(jbtnPrior);
		navigationPanel.add(jbtnNext);
		add(navigationPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args){
		Exercise17 frame = new Exercise17();
		frame.setSize(500, 200);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Exercise17");
		frame.setVisible(true);

	}

}