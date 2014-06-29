package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercise12 extends JFrame{
	private JTextArea jtaTextArea = new JTextArea("", 10, 20);
	private JCheckBox jchkWrap = new JCheckBox("Wrap", false);
	private JRadioButton jrbWrapWords = new JRadioButton("Wrap Words", true);
	private JRadioButton jrbWrapCharacters = new JRadioButton("Wrap Characters");
	private static final long serialVersionUID = 1L;

	public Exercise12() {
		JScrollPane viewTextArea = new JScrollPane(jtaTextArea);
		add(viewTextArea, BorderLayout.CENTER);		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder("Wrap Options"));
		WrapTextArea wrapTextArea = new WrapTextArea();
		jchkWrap.addActionListener(wrapTextArea);
		jrbWrapWords.addActionListener(wrapTextArea);
		jrbWrapCharacters.addActionListener(wrapTextArea);
		ButtonGroup group = new ButtonGroup();
		group.add(jrbWrapWords);
		group.add(jrbWrapCharacters);
		panel1.add(jchkWrap);
		panel1.add(jrbWrapWords);
		panel1.add(jrbWrapCharacters);
		add(panel1, BorderLayout.SOUTH);
	}
	
	class WrapTextArea implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			jtaTextArea.setLineWrap(jchkWrap.isSelected());
			jtaTextArea.setWrapStyleWord(jrbWrapWords.isSelected());
		}
	}
	
	public static void main(String[] args) {
		Exercise12 frame = new Exercise12();
		frame.setTitle("Exercise12");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


}
