package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise16 extends JFrame {
	private boolean visible = true;
	JLabel label = new JLabel("123");
	private static final long serialVersionUID = 1L;

	public Exercise16() {
		add(label);
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (visible) {
					visible = false;
					label.setVisible(visible);
				} else {
					visible = true;
					label.setVisible(visible);
				}
			}
		});
		timer.start();
	}

	public static void main(String[] args) {
		Exercise16 frame = new Exercise16();
		frame.setTitle("Exercise07");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
