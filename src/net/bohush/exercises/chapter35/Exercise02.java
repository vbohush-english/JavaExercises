package net.bohush.exercises.chapter35;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Exercise02 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField(20);
	private JTextField jTextField2 = new JTextField(20);
	private String[] availableStyles = {"Full", "Long", "Medium", "Short"};
	private JComboBox<Locale> jComboBox1;
	private JComboBox<String> jComboBox2;
	private JComboBox<String> jComboBox3;
	private JComboBox<String> jComboBox4;
	
	public Exercise02() {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel jPanel1 = new JPanel(new GridLayout(6, 1, 5, 5));
		jPanel1.add(new JLabel("Date:"));
		jPanel1.add(new JLabel("Time:"));
		jPanel1.add(new JLabel("Locale:"));
		jPanel1.add(new JLabel("Time Zone:"));
		jPanel1.add(new JLabel("Date Style:"));
		jPanel1.add(new JLabel("Time Style:"));
		mainPanel.add(jPanel1, BorderLayout.WEST);
		
		JPanel jPanel2 = new JPanel(new GridLayout(6, 1, 5, 5));
		
		jTextField1.setEditable(false);
		jPanel2.add(jTextField1);
		
		jTextField2.setEditable(false);
		jPanel2.add(jTextField2);
		
		Locale[] availableLocales = Calendar.getAvailableLocales();
		sortByString(availableLocales);
		jComboBox1 = new JComboBox<>(availableLocales);
		jPanel2.add(jComboBox1);
		
		String[] availableTimeZones = TimeZone.getAvailableIDs();
		jComboBox2 = new JComboBox<>(availableTimeZones);
		jPanel2.add(jComboBox2);

		jComboBox3 = new JComboBox<>(availableStyles);
		jPanel2.add(jComboBox3);
		
		jComboBox4 = new JComboBox<>(availableStyles);
		jPanel2.add(jComboBox4);
		mainPanel.add(jPanel2, BorderLayout.CENTER);
		
		ShowDate showDate = new ShowDate();
		jComboBox1.addItemListener(showDate);
		jComboBox2.addItemListener(showDate);
		jComboBox3.addItemListener(showDate);
		jComboBox4.addItemListener(showDate);
		showDate.itemStateChanged(null);
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}
	
	class ShowDate implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			GregorianCalendar calendar = new GregorianCalendar();
			int DateStyle = DateFormat.FULL;
			int TimeStyle = DateFormat.FULL;
			for (int i = 0; i < availableStyles.length; i++) {
				if(jComboBox3.getItemAt(jComboBox3.getSelectedIndex()).equals(availableStyles[i])) {
					DateStyle = jComboBox3.getSelectedIndex();
				}
				if(jComboBox4.getItemAt(jComboBox4.getSelectedIndex()).equals(availableStyles[i])) {
					TimeStyle = jComboBox4.getSelectedIndex();
				}
			}
			DateFormat formatter1 = DateFormat.getDateInstance(DateStyle, jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
			DateFormat formatter2 = DateFormat.getTimeInstance(TimeStyle, jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
			TimeZone timeZone = TimeZone.getTimeZone(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));
			formatter1.setTimeZone(timeZone);
			formatter2.setTimeZone(timeZone);
			jTextField1.setText(formatter1.format(calendar.getTime()));
			jTextField2.setText(formatter2.format(calendar.getTime()));
		}
		
	}

	public void sortByString(Object[] list) {
		for(int i = 0; i < list.length - 1; i++) {
			for(int j = 0; j < list.length - 1; j++) {
				if (list[j].toString().compareTo(list[j + 1].toString()) > 0) {
					Object tmp = list[j];
					list[j] = list [j + 1];
					list[j + 1] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise02");
		JApplet applet = new Exercise02();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}