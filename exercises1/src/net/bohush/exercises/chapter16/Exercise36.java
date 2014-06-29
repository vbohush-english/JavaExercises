package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise36 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise36() {
		final int SIZE = 3;
		setLayout(new GridLayout(SIZE, SIZE));
		for (int i = 0; i < SIZE * SIZE; i++) {
			add(new CoinLabel(CoinLabel.HEAD));
		}
	}
	
	public static void main(String[] args) {
		Exercise36 frame = new Exercise36();
		frame.setTitle("Exercise36");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class CoinLabel extends JLabel {
		private int value;
		static final int HEAD = 'H';
		static final int TAIL = 'T';
		private static final long serialVersionUID = 1L;

		public CoinLabel(int value) {
			super(""+ (char)value, JLabel.CENTER);
			this.value =  value;
			setBorder(new LineBorder(Color.BLACK));
			addMouseListener(new MouseAdapter() {
				
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					change();
				}
				
			});
		}
		
		public void change() {
			value =  (value == HEAD ? TAIL : HEAD);
			setText(""+ (char)value);
		}

	}
}
