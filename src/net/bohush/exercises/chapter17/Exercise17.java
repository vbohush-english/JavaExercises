package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise17  extends JFrame {
	JLabel label = new JLabel();
	Calendar date = new GregorianCalendar();
	
	JPanel months = new JPanel(new GridLayout(0, 7, 0, 0));

	
	private static final long serialVersionUID = 1L;
	
	public Exercise17() {
		drawCalendar();
		
		JPanel navigationPanel = new JPanel();
		JButton jbtnPrior = new JButton("Prior");
		jbtnPrior.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				date.set(Calendar.MONTH, date.get(Calendar.MONTH) - 1);	
				drawCalendar();
			}
		});
		JButton jbtnNext = new JButton("Next");
		jbtnNext.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				date.set(Calendar.MONTH, date.get(Calendar.MONTH) + 1);
				drawCalendar();
			}
		});
		navigationPanel.add(jbtnPrior);
		navigationPanel.add(jbtnNext);
		add(navigationPanel, BorderLayout.SOUTH);
	}

	public void drawCalendar() {
		date.set(Calendar.DAY_OF_MONTH, 1);
		
		//month title
		String[] monthsNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		label.setText(monthsNames[date.get(Calendar.MONDAY)] + " " + date.get(Calendar.YEAR));
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.NORTH);
		
		months.removeAll();
		months.repaint();
		String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for (int i = 0; i < 7; i++) {
			JLabel tmp = new JLabel(dayNames[i], JLabel.CENTER);
			tmp.setBorder(new LineBorder(Color.BLACK));
			months.add(tmp);
		}
		
		
		int startDay = date.get(Calendar.DAY_OF_WEEK) - 2;	
		if (startDay == -1) {
			startDay = 6;
		}
		int endDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < endDay + startDay; i++) {
			JLabel tmp;
			if (i >= startDay) {
				tmp = new JLabel((i - startDay + 1) + "", JLabel.CENTER);
			} else {
				GregorianCalendar prevDate = (GregorianCalendar)date.clone();
				prevDate.set(Calendar.MONTH, prevDate.get(Calendar.MONTH) - 1);
				tmp = new JLabel(( - (startDay - 1 - prevDate.getActualMaximum(Calendar.DAY_OF_MONTH) - i)) + "");				
				tmp.setForeground(Color.GRAY);
			}
			tmp.setBorder(new LineBorder(Color.BLACK));
			months.add(tmp);				
		}
		for (int i = endDay + startDay; i % 7 != 0; i++) {
			JLabel tmp = new JLabel((i - (endDay + startDay) + 1) + "", JLabel.CENTER);
			tmp.setBorder(new LineBorder(Color.BLACK));
			tmp.setForeground(Color.GRAY);
			months.add(tmp);
		}
		add(months, BorderLayout.CENTER);
	}
	public static void main(String[] args){
		Exercise17 frame = new Exercise17();
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Exercise17");
		frame.setVisible(true);

	}

}