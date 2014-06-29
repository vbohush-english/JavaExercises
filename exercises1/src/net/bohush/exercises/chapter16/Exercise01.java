package net.bohush.exercises.chapter16;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Exercise01 extends JFrame {
	private JLabel[] jlblCards = new JLabel[4];
	private int[] deck = new int[54];

	private static final long serialVersionUID = 1L;

	public Exercise01() {		
		changeCards();
		setLayout(new BorderLayout());
		
		JPanel cardPanel = new JPanel(new GridLayout(1, 4));
		for (int i = 0; i < 4; i++) {
			jlblCards[i] = new JLabel(new ImageIcon("image/Cards/" + deck[i] + ".png"));
			cardPanel.add(jlblCards[i]);
		}
		add(cardPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton jbtRefresh = new JButton("Refresh");
		jbtRefresh.addActionListener(new RefreshListener());
		buttonPanel.add(jbtRefresh);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void changeCards() {
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i + 1;
		}

		for (int i = 0; i < deck.length; i++) {
			int index = (int) (Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
	}

	class RefreshListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			changeCards();
			for (int i = 0; i < 4; i++) {
				jlblCards[i].setIcon(new ImageIcon("image/Cards/" + deck[i] + ".png"));
			}
		}
	}

	public static void main(String[] args) {
		Exercise01 frame = new Exercise01();
		frame.setSize(350, 180);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise01");
		frame.setVisible(true);
	}

}
