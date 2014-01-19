package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise08 extends JFrame{
	private JComboBox<Integer> jcbSize;
	private JComboBox<String> jcbName;
	
	private JLabel jlblMessage = new JLabel("Java is Cool");
	
	private JCheckBox jchkCentered = new JCheckBox("Centered");
	private JCheckBox jchkBold = new JCheckBox("Bold");
	private JCheckBox jchkItalic = new JCheckBox("Italic");
	
	private static final long serialVersionUID = 1L;

	public Exercise08() {
		JPanel panel1 = new JPanel();
		JLabel jlblName = new JLabel("Font Name");
		JLabel jlblSize = new JLabel("Font Size");
		
		Integer[] sizeItems = new Integer[100];
		for (int i = 1; i <= 100; i++) {
			sizeItems[i - 1] = i;
		}
		jcbSize = new JComboBox<Integer>(sizeItems);
		jcbSize.setBackground(Color.WHITE);
		jcbSize.setSelectedItem(18);	
		
		GraphicsEnvironment e =	GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = e.getAvailableFontFamilyNames();
		jcbName = new JComboBox<String>(fontNames);
		jcbName.setBackground(Color.WHITE);
		jcbName.setSelectedItem("Times New Roman");
		
		panel1.add(jlblName);
		panel1.add(jcbName);
		panel1.add(jlblSize);
		panel1.add(jcbSize);
		panel1.setBackground(Color.WHITE);
		add(panel1, BorderLayout.NORTH);
		
		add(jlblMessage, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		jchkCentered.setMnemonic('C');
		jchkBold.setMnemonic('B');
		jchkItalic.setMnemonic('I');
		jchkCentered.setBackground(Color.WHITE);
		jchkBold.setBackground(Color.WHITE);
		jchkItalic.setBackground(Color.WHITE);
		panel2.add(jchkCentered);
		panel2.add(jchkBold);
		panel2.add(jchkItalic);
		add(panel2, BorderLayout.SOUTH);
		
		jchkCentered.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				 rewriteMessage();
			}
		});
		jchkBold.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				 rewriteMessage();
			}
		});
		jchkItalic.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				 rewriteMessage();
			}
		});
		jcbName.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				 rewriteMessage();
			}
		});
		jcbSize.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				 rewriteMessage();
			}
		});
		rewriteMessage();
	}
	
	private void rewriteMessage() {
		if (jchkCentered.isSelected()) {
			jlblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		} else {
			jlblMessage.setHorizontalAlignment(SwingConstants.LEFT);
		}
		int fontStyle = Font.PLAIN;
		if (jchkBold.isSelected()) {
			fontStyle += Font.BOLD;
		}
		if (jchkItalic.isSelected()) {
			fontStyle += Font.ITALIC;
		}
		Font font = new Font((String)jcbName.getSelectedItem(), fontStyle, (Integer)jcbSize.getSelectedItem());
		jlblMessage.setFont(font);
	}
	
	public static void main(String[] args) {
		Exercise08 frame = new Exercise08();
		frame.setTitle("Exercise08");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(new Dimension(frame.getWidth(), frame.getHeight() + 50));
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
