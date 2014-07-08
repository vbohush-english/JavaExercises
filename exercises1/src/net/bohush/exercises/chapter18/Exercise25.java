package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise25 extends JApplet {
	private static final long serialVersionUID = 1L;
	private DatesPanel[] datesPanels;
	private int[][][] dates;
	private JTextField jTextField = new JTextField(10);
	
	public Exercise25() {
		setLayout(new BorderLayout());
		JPanel jPanel2 = new JPanel();
		jPanel2.add(new JLabel("Check the boxes if your birthday is in these sets"));
		add(jPanel2, BorderLayout.NORTH);
		
		JPanel jPanel1 = new JPanel();
		JButton jButton = new JButton("Guess Birthday");
		jTextField.setEditable(false);
		jPanel1.add(jButton);
		jPanel1.add(jTextField);
		add(jPanel1, BorderLayout.SOUTH);
		
	    dates = new int[][][]{
	      {{ 1,  3,  5,  7},
	       { 9, 11, 13, 15},
	       {17, 19, 21, 23},
	       {25, 27, 29, 31}},
	      {{ 2,  3,  6,  7},
	       {10, 11, 14, 15},
	       {18, 19, 22, 23},
	       {26, 27, 30, 31}},
	      {{ 4,  5,  6,  7},
	       {12, 13, 14, 15},
	       {20, 21, 22, 23},
	       {28, 29, 30, 31}},
	      {{ 8,  9, 10, 11},
	       {12, 13, 14, 15},
	       {24, 25, 26, 27},
	       {28, 29, 30, 31}},
	      {{16, 17, 18, 19},
	       {20, 21, 22, 23},
	       {24, 25, 26, 27},
	       {28, 29, 30, 31}}};	    
		JPanel jPanel3 = new JPanel(new GridLayout(1, 5, 10, 10));		
		datesPanels = new DatesPanel[dates.length];
		for (int i = 0; i < dates.length; i++) {
			datesPanels[i] = new DatesPanel(dates[i]);
			jPanel3.add(datesPanels[i]);
		}
		add(jPanel3, BorderLayout.CENTER);
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int day = 0;
				for (int i = 0; i < dates.length; i++) {
					if(datesPanels[i].isSelected()) {
						day += dates[i][0][0];
					}
				}
				if (day == 0) {
					JOptionPane.showMessageDialog(null, "Please check at least one box!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					jTextField.setText(day + "");
				}
			}
		});
	}
	
	class DatesPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private	JCheckBox jCheckBox = new JCheckBox();
		public DatesPanel(int[][] dates) {
			setLayout(new BorderLayout());
			setBorder(new LineBorder(Color.BLACK));
			JPanel jPanel1 = new JPanel(new GridLayout(4, 4));
			for (int i = 0; i < dates.length; i++) {
				for (int j = 0; j < dates[i].length; j++) {
					jPanel1.add(new JLabel(dates[i][j] + ""));
				}				
			}
			add(jPanel1, BorderLayout.CENTER);
			add(jCheckBox, BorderLayout.SOUTH);
		}
		
		public boolean isSelected() {
			return jCheckBox.isSelected();
		}
		
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise25());
		frame.setTitle("Exercise25");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}