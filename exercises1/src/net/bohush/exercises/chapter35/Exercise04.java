package net.bohush.exercises.chapter35;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Exercise04 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JTextArea jTextArea = new JTextArea();
	
	public Exercise04() {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		jTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		jTextArea.setEditable(false);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		mainPanel.add(jScrollPane, BorderLayout.CENTER);
		
		JPanel jPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		JButton jButton1 = new JButton("All locales");
		jPanel.add(jButton1);
		JButton jButton2 = new JButton("All Time Zones");
		jPanel.add(jButton2);
		mainPanel.add(jPanel, BorderLayout.SOUTH);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale[] availableLocales = Calendar.getAvailableLocales();
				sortByString(availableLocales);
				StringBuilder stringBuilder = new StringBuilder();
				for (Locale locale : availableLocales) {
					stringBuilder.append(locale.getDisplayName() + " " + locale.toString() + '\n');
				}
				jTextArea.setText(stringBuilder.toString());
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] availableTimeZones = TimeZone.getAvailableIDs();
				StringBuilder stringBuilder = new StringBuilder();
				for (String string : availableTimeZones) {
					stringBuilder.append(string + '\n');
				}
				jTextArea.setText(stringBuilder.toString());
			}
		});
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
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
		JFrame frame = new JFrame("Exercise04");
		JApplet applet = new Exercise04();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		//frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}