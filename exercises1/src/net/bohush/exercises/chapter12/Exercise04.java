package net.bohush.exercises.chapter12;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise04 extends JPanel {

	private static final long serialVersionUID = 1L;

	public Exercise04(int firstNumber) {
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		add(new JButton("Button " + firstNumber++));
		add(new JButton("Button " + firstNumber++));
		add(new JButton("Button " + firstNumber));
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		frame.setSize(700, 100);
		frame.setTitle("Exercise04");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Exercise04(1));
		frame.add(new Exercise04(4));
		frame.setVisible(true);
	}

}
