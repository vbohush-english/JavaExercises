package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercise09 extends JFrame{
	private JLabel jlblPicture = new JLabel("Grapes", new ImageIcon("image/17_09/grapes.gif"), JLabel.CENTER);
	private String[] verPositions = {"TOP", "CENTER", "BOTTOM"};
	private String[] horPositions = {"LEFT", "CENTER", "RIGHT", "LEADING", "TRAILING"};
	private JComboBox<String> jcbHorizontalAlignment = new JComboBox<>(horPositions);
	private JComboBox<String> jcbVertivalAlignment = new JComboBox<>(verPositions);
	private JComboBox<String> jcbHorizontalText = new JComboBox<>(horPositions);
	private JComboBox<String> jcbVertivalText = new JComboBox<>(verPositions);
	
	private static final long serialVersionUID = 1L;

	public Exercise09() {
		JPanel panel1 = new JPanel(new GridLayout(2, 1));
		panel1.add(new JLabel("Horizontal    "));
		panel1.add(new JLabel("Vertical      "));
		

		JPanel panel2 = new JPanel(new GridLayout(2, 1, 5, 5));
		panel2.add(jcbHorizontalAlignment);
		panel2.add(jcbVertivalAlignment);
		
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.setBorder(new TitledBorder("Horizontal Alignment"));
		panel3.add(panel1, BorderLayout.WEST);
		panel3.add(panel2, BorderLayout.EAST);
	
		JPanel panel4 = new JPanel(new GridLayout(2, 1, 5, 5));
		panel4.add(new JLabel("Horizontal    "));
		panel4.add(new JLabel("Vertical      "));
		
		JPanel panel5 = new JPanel(new GridLayout(2, 1, 5, 5));
		panel5.add(jcbHorizontalText);
		panel5.add(jcbVertivalText);
		
		JPanel panel6 = new JPanel(new BorderLayout());
		panel6.setBorder(new TitledBorder("Text position"));
		panel6.add(panel4, BorderLayout.WEST);
		panel6.add(panel5, BorderLayout.EAST);

		JPanel panel7 = new JPanel(new GridLayout(1, 2, 5, 5));
		panel7.add(panel3);
		panel7.add(panel6);
		add(panel7, BorderLayout.SOUTH);
		
		add(jlblPicture, BorderLayout.CENTER);
				
		MessageListener messageListener = new MessageListener();
		jcbHorizontalAlignment.addActionListener(messageListener);
		jcbVertivalAlignment.addActionListener(messageListener);
		jcbHorizontalText.addActionListener(messageListener);
		jcbVertivalText.addActionListener(messageListener);
		
		repaintMessage();
	}
	
	class MessageListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			repaintMessage();
		}		
	}
	
	private void repaintMessage() {
		int[] intVerPositions = {JLabel.TOP, JLabel.CENTER, JLabel.BOTTOM};
		int[] intHorPositions = {JLabel.LEFT, JLabel.CENTER, JLabel.RIGHT, JLabel.LEADING, JLabel.TRAILING};
		jlblPicture.setHorizontalAlignment(intHorPositions[jcbHorizontalAlignment.getSelectedIndex()]);
		jlblPicture.setVerticalAlignment(intVerPositions[jcbVertivalAlignment.getSelectedIndex()]);
		jlblPicture.setHorizontalTextPosition(intHorPositions[jcbHorizontalText.getSelectedIndex()]);
		jlblPicture.setVerticalTextPosition(intVerPositions[jcbVertivalText.getSelectedIndex()]);
	}
	
	public static void main(String[] args) {
		Exercise09 frame = new Exercise09();
		frame.setTitle("Exercise09");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(new Dimension(frame.getWidth(), frame.getHeight() + 50));
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
