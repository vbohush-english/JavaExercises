package net.bohush.exercises.chapter17;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Exercise16 extends JFrame{
	private JSlider jsldRed = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	private JSlider jsldGreen = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	private JSlider jsldBlue = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	private JLabel jlblShowColors = new JLabel("Show Colors");
	private static final long serialVersionUID = 1L;

	public Exercise16() {
		JPanel panel1 = new JPanel(new GridLayout(3, 1));
		panel1.add(new JLabel("Red    "));
		panel1.add(new JLabel("Green  "));
		panel1.add(new JLabel("Blue   "));
		
		JPanel panel2 = new JPanel(new GridLayout(3, 1));
		panel2.add(jsldRed);
		panel2.add(jsldGreen);
		panel2.add(jsldBlue);
		
		jsldRed.setPaintLabels(true);
		jsldGreen.setPaintLabels(true);
		jsldBlue.setPaintLabels(true);
		
		jsldRed.setPaintTicks(true);
		jsldGreen.setPaintTicks(true);
		jsldBlue.setPaintTicks(true);
		
		jsldRed.setMajorTickSpacing(32);
		jsldGreen.setMajorTickSpacing(32);
		jsldBlue.setMajorTickSpacing(32);
		
		jsldRed.setMinorTickSpacing(4);
		jsldGreen.setMinorTickSpacing(4);
		jsldBlue.setMinorTickSpacing(4);
			
		
		ChangeColor changeColor = new ChangeColor();
		jsldRed.addChangeListener(changeColor);
		jsldGreen.addChangeListener(changeColor);
		jsldBlue.addChangeListener(changeColor);
		
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.setBorder(new TitledBorder("Choose colors"));
		panel3.add(panel1, BorderLayout.WEST);
		panel3.add(panel2, BorderLayout.CENTER);

		jlblShowColors.setHorizontalAlignment(JLabel.CENTER);
		jlblShowColors.setFont(new Font("Times New Roman", Font.BOLD, 24));
		add(jlblShowColors, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
	}
	
	class ChangeColor implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			jlblShowColors.setForeground(new Color(jsldRed.getValue(), jsldGreen.getValue(), jsldBlue.getValue()));		}
	}
	
	public static void main(String[] args) {
		Exercise16 frame = new Exercise16();
		frame.setTitle("Exercise16");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
