package net.bohush.exercises.chapter12;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Exercise05 extends JFrame {
	
	public Exercise05() {
		setLayout(new GridLayout(4, 0, 0, 0));
		JTextField jtf1 = new JTextField("Department of Computer Science");
		JTextField jtf2 = new JTextField("School of Computing");
		JTextField jtf3 = new JTextField("Armstrong Athlantic State Univercity");
		JTextField jtf4 = new JTextField("Tel (912) 921-6440");
		jtf1.setBorder(new LineBorder(Color.BLACK));
		jtf2.setBorder(new LineBorder(Color.BLACK));
		jtf3.setBorder(new LineBorder(Color.BLACK));
		jtf4.setBorder(new LineBorder(Color.BLACK));
		add(jtf1);
		add(jtf2);
		add(jtf3);
		add(jtf4);
	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise05 frame = new Exercise05();
		frame.setSize(300, 170);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise05");
		frame.setVisible(true);
	}
	
}
