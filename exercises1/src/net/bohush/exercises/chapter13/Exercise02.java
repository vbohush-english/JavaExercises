package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise02 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise02() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(new OvalButton("Ok"));
		add(new OvalButton("Cancel"));
	}
	
	public static void main(String[] args) {
		Exercise02 frame = new Exercise02();
		frame.setSize(300, 100);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise02");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class OvalButton extends JButton {

	private static final long serialVersionUID = 1L;

	public OvalButton() {
		super();
	}
	
	public OvalButton(String title) {
		super(title);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawOval(width / 10, height / 10, width - width / 5, height - height / 5);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(130, 50);
	}
}