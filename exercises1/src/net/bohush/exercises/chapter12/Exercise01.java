package net.bohush.exercises.chapter12;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise01 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise01() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		p1.add(new JButton("Button 1"));
		p1.add(new JButton("Button 2"));
		p1.add(new JButton("Button 3"));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		p2.add(new JButton("Button 4"));
		p2.add(new JButton("Button 5"));
		p2.add(new JButton("Button 6"));
	    add(p1);
	    add(p2);
	}
	
	public static void main(String[] args) {
		Exercise01 frame = new Exercise01();
		frame.setSize(700, 100);
		frame.setTitle("Exercise01");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
