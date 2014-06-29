package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercise15 extends JFrame{
	private JScrollBar jscbRed = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 255);
	private JScrollBar jscbGreen = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 255);
	private JScrollBar jscbBlue = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 255);
	private JLabel jlblShowColors = new JLabel("Show Colors");
	private static final long serialVersionUID = 1L;

	public Exercise15() {
		JPanel panel1 = new JPanel(new GridLayout(3, 1));
		panel1.add(new JLabel("Red    "));
		panel1.add(new JLabel("Green  "));
		panel1.add(new JLabel("Blue   "));
		
		JPanel panel2 = new JPanel(new GridLayout(3, 1));
		panel2.add(jscbRed);
		panel2.add(jscbGreen);
		panel2.add(jscbBlue);
		
		ChangeColor changeColor = new ChangeColor();
		jscbRed.addAdjustmentListener(changeColor);
		jscbGreen.addAdjustmentListener(changeColor);
		jscbBlue.addAdjustmentListener(changeColor);
		
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.setBorder(new TitledBorder("Choose colors"));
		panel3.add(panel1, BorderLayout.WEST);
		panel3.add(panel2, BorderLayout.CENTER);

		jlblShowColors.setHorizontalAlignment(JLabel.CENTER);
		jlblShowColors.setFont(new Font("Times New Roman", Font.BOLD, 24));
		add(jlblShowColors, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
	}
	
	class ChangeColor implements AdjustmentListener {
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			jlblShowColors.setForeground(new Color(jscbRed.getValue(), jscbGreen.getValue(), jscbBlue.getValue()));
		}
	}
	
	public static void main(String[] args) {
		Exercise15 frame = new Exercise15();
		frame.setTitle("Exercise15");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
