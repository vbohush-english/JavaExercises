package net.bohush.exercises.chapter12;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Exercise06 extends JFrame {
	
	public Exercise06() {
		setLayout(new GridLayout(2, 2, 0, 0));
		JLabel jlbl1 = new JLabel(new ImageIcon("image/12_06/uk.gif"));
		JLabel jlbl2 = new JLabel(new ImageIcon("image/12_06/fr.gif"));
		JLabel jlbl3 = new JLabel(new ImageIcon("image/12_06/norway.gif"));
		JLabel jlbl4 = new JLabel(new ImageIcon("image/12_06/my.jpg"));
		jlbl1.setBorder(new LineBorder(Color.BLACK));
		jlbl2.setBorder(new LineBorder(Color.BLACK));
		jlbl3.setBorder(new LineBorder(Color.BLACK));
		jlbl4.setBorder(new LineBorder(Color.BLACK));
		add(jlbl1);
		add(jlbl2);
		add(jlbl3);
		add(jlbl4);
	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise06 frame = new Exercise06();
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise06");
		frame.setVisible(true);
	}
	
}
