package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise16 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise16() {
		add(new WriteText());
	}
	
	public static void main(String[] args) {
		Exercise16 frame = new Exercise16();
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise16");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class WriteText extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString("Java is fun", 20, 20);
		FontMetrics fm = g.getFontMetrics();
		String toolTip = "";
		toolTip += "Ascent: " + fm.getAscent() + " ";
		toolTip += "Descent: " + fm.getDescent() + " ";
		toolTip += "Leading: " + fm.getLeading() + " ";
		toolTip += "Height: " + fm.getHeight() + " ";
		toolTip += "Width: " + fm.stringWidth("TimesRoman");
		setToolTipText(toolTip);
	}

}