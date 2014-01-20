package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Exercise11 extends JFrame{
	private	JTextField jtfText = new JTextField("Type anything", 30);
	private JTextField jtfSize = new JTextField("30", 10);
	private static final long serialVersionUID = 1L;

	public Exercise11() {
		JPanel panel1 = new JPanel();
		JRadioButton jrbLeft = new JRadioButton("Left", true);
		JRadioButton jrbCenter = new JRadioButton("Center");
		JRadioButton jrbRight = new JRadioButton("Right");
		jrbLeft.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtfText.setHorizontalAlignment(SwingConstants.LEFT);			
			}
		});
		jrbCenter.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtfText.setHorizontalAlignment(SwingConstants.CENTER);			
			}
		});
		jrbRight.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtfText.setHorizontalAlignment(SwingConstants.RIGHT);			
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(jrbLeft);
		group.add(jrbCenter);
		group.add(jrbRight);
		panel1.add(jrbLeft);
		panel1.add(jrbCenter);
		panel1.add(jrbRight);
		panel1.setBorder(new TitledBorder("Horizontal Alignment"));
		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder("Column Size"));
		jtfSize.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					jtfText.setColumns(Integer.parseInt(jtfSize.getText()));	
				} catch (NumberFormatException e2) {
				}
			}
		});
		panel2.add(jtfSize);
		JPanel panel3 = new JPanel(new GridLayout(1, 2, 5, 5));
		panel3.add(panel1);
		panel3.add(panel2);
		JPanel panel4 = new JPanel();
		JLabel jlblText = new JLabel("Text field ");
		panel4.add(jlblText);
		panel4.add(jtfText);
		add(panel3, BorderLayout.SOUTH);
		add(panel4, BorderLayout.CENTER);		
	}
	
	public static void main(String[] args) {
		Exercise11 frame = new Exercise11();
		frame.setTitle("Exercise11");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
