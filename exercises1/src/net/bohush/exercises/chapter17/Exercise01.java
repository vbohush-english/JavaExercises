package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Exercise01 extends JFrame {
	private MessagePanel messagePanel;

	private static final long serialVersionUID = 1L;

	public Exercise01() {
	
		//RadioButtons panel
		JPanel colorPanel = new JPanel();
		colorPanel.setBorder(new TitledBorder("Select Message Panel Background"));
		JRadioButton jrbRed = new JRadioButton("Red");
		JRadioButton jrbYellow = new JRadioButton("Yellow");
		JRadioButton jrbWhite = new JRadioButton("White", true);
		JRadioButton jrbGray = new JRadioButton("Gray");
		JRadioButton jrbGreen = new JRadioButton("Green");
		jrbRed.setMnemonic('R');
		jrbYellow.setMnemonic('Y');
		jrbWhite.setMnemonic('W');
		jrbGray.setMnemonic('G');
		jrbGreen.setMnemonic('E');
		jrbRed.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				messagePanel.setBackground(Color.RED);				
			}
		});
		jrbYellow.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				messagePanel.setBackground(Color.YELLOW);				
			}
		});
		jrbWhite.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				messagePanel.setBackground(Color.WHITE);				
			}
		});
		jrbGray.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				messagePanel.setBackground(Color.GRAY);				
			}
		});
		jrbGreen.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				messagePanel.setBackground(Color.GREEN);				
			}
		});
		ButtonGroup colorGroup = new ButtonGroup();
		colorGroup.add(jrbRed);
		colorGroup.add(jrbYellow);
		colorGroup.add(jrbWhite);
		colorGroup.add(jrbGray);
		colorGroup.add(jrbGreen);
		colorPanel.add(jrbRed);
		colorPanel.add(jrbYellow);
		colorPanel.add(jrbWhite);
		colorPanel.add(jrbGray);
		colorPanel.add(jrbGreen);
		add(colorPanel, BorderLayout.NORTH);
		
		//message panel
		messagePanel = new MessagePanel();
		messagePanel.setBackground(Color.WHITE);
		add(messagePanel, BorderLayout.CENTER);
		
		
		//buttons panel
		JPanel buttonPanel = new JPanel();
		JButton jbtLeft = new JButton("<=");
		jbtLeft.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				messagePanel.moveLeft();
			}
		});
		JButton jbtRight = new JButton("=>");
		jbtRight.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				messagePanel.moveRight();
			}
		});
		buttonPanel.add(jbtLeft);
		buttonPanel.add(jbtRight);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		Exercise01 frame = new Exercise01();
		frame.setTitle("Exercise01");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(frame.getWidth(), frame.getHeight() + 50);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class MessagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private String message = "Welcome to Java";
		private int x = 20;
		private int y = 20;
		private int step = 5;
		
		public void moveLeft() {
			x -= step;
			repaint();
		}
		
		public void moveRight() {
			x += step;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString(message, x, y);
		}
	}

}
