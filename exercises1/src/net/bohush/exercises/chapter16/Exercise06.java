package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise06 extends JFrame {
	private static final long serialVersionUID = 1L;
	private String messages1 = "Java is fun";
	private String messages2 = "Java is powerful";
	String currrentMessage = messages1;
	private JLabel label = new JLabel(currrentMessage);
	public Exercise06() {
		this.add(label, BorderLayout.CENTER);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (currrentMessage == messages1) {
					currrentMessage = messages2;
				} else {
					currrentMessage = messages1;
				}
				label.setText(currrentMessage);
				repaint();

			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new Exercise06();
		frame.setTitle("Exercise06");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}