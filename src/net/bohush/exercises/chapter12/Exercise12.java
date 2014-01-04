package net.bohush.exercises.chapter12;

import java.awt.*;

import javax.swing.*;

public class Exercise12 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise12() {
		setLayout(new GridLayout(1, 4));
		JLabel jlbl1 = new JLabel("US", new ImageIcon("image/12_12/usIcon.gif"), JLabel.CENTER);
		JLabel jlbl2 = new JLabel("US", new ImageIcon("image/12_12/usIcon.gif"), JLabel.CENTER);
		JLabel jlbl3 = new JLabel("US", new ImageIcon("image/12_12/usIcon.gif"), JLabel.CENTER);
		JLabel jlbl4 = new JLabel("US", new ImageIcon("image/12_12/usIcon.gif"), JLabel.CENTER);
		
		jlbl1.setHorizontalTextPosition(JLabel.CENTER);
		jlbl1.setVerticalTextPosition(JLabel.BOTTOM);
		
		jlbl2.setHorizontalTextPosition(JLabel.RIGHT);
		
		jlbl3.setHorizontalTextPosition(JLabel.LEFT);
		
		jlbl4.setHorizontalTextPosition(JLabel.CENTER);
		jlbl4.setVerticalTextPosition(JLabel.TOP);
				
		add(jlbl1);
		add(jlbl2);
		add(jlbl3);
		add(jlbl4);		
	}
	
	public static void main(String[] args) {
		Exercise12 frame = new Exercise12();
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE );
		frame.setTitle("Exercise12");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
