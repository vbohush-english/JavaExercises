package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise40 extends JFrame {
	private JLabel[] labels = new JLabel[100];
	public Exercise40() {
				
		JPanel panel1 = new JPanel(new GridLayout(10, 10, 0, 0));
		for (int i = 0; i < 100; i++) {
			labels[i] = new JLabel((int)(Math.random() * 2) + "", JLabel.CENTER); 
			panel1.add(labels[i]);	
		}
		panel1.setBorder(new LineBorder(Color.BLACK));
		add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();		
		JButton jbtRefresh = new JButton("Refresh");
		panel2.add(jbtRefresh);
		add(panel2, BorderLayout.SOUTH);
		
		jbtRefresh.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 100; i++) {
					labels[i].setText((int)(Math.random() * 2) + "");
				}
			}
		});


	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise40 frame = new Exercise40();
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise40");
		frame.setVisible(true);
	}
	
}
