package net.bohush.exercises.chapter12;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise02 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise02() {
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		p1.add(new JButton("Button 1"));
		p1.add(new JButton("Button 2"));
		p1.add(new JButton("Button 3"));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		p2.add(new JButton("Button 4"));
		p2.add(new JButton("Button 5"));
		p2.add(new JButton("Button 6"));
	    add(p1, BorderLayout.SOUTH);
	    add(p2, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		Exercise02 frame = new Exercise02();
		frame.setSize(400, 400);
		frame.setTitle("Exercise02");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
