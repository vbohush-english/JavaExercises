package net.bohush.exercises.chapter12;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Exercise08 extends JFrame {
	
	public Exercise08() {		
		setLayout(new GridLayout(2, 3, 0, 0));		
		JLabel jlblArray[] = new JLabel[6];
		String[] colorsInString = {"black", "blue", "cyan", "green", "magenta", "orange"};
		Color[] colorsInColor = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE};		
		for (int i = 0; i < jlblArray.length; i++) {
			jlblArray[i] = new JLabel(colorsInString[i]);
			jlblArray[i].setBackground(Color.WHITE);
			jlblArray[i].setForeground(colorsInColor[i]);
			jlblArray[i].setBorder(new LineBorder(Color.YELLOW));
			jlblArray[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
			jlblArray[i].setToolTipText(colorsInString[i]);
			add(jlblArray[i]);
		}

	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise08 frame = new Exercise08();
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise08");
		frame.setVisible(true);
	}
	
}
