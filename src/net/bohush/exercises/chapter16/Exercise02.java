package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise02 extends JFrame {
	JButton[] jbtButtons = new JButton[6];
	
	private static final long serialVersionUID = 1L;

	public Exercise02() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		ClickListener clickListener = new  ClickListener();
		for (int i = 0; i < jbtButtons.length; i++) {
			jbtButtons[i] = new JButton("Button " + (i + 1));
			jbtButtons[i].addActionListener(clickListener);
			p1.add(jbtButtons[i]);
		}
		add(p1);
	}
	
	class ClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < jbtButtons.length; i++)
				if (e.getSource() == jbtButtons[i]) {
					System.out.println("Button " + (i + 1) + " clicked.");
					break;
				}
		}
	}
	
	public static void main(String[] args) {
		Exercise02 frame = new Exercise02();
		frame.setSize(700, 100);
		frame.setTitle("Exercise02");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
