package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Exercise05 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtfFileName = new JTextField(30);
	private Histogram histogram = new Histogram();

	public Exercise05() {
		// control panel
		JPanel choseFilePanel = new JPanel();
		JLabel jlblFileName = new JLabel("Filename");
		JButton jbtView = new JButton("View");
		jbtView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtfFileName.getText().equals("")) {
					JOptionPane.showMessageDialog(null,	"Please enter filename.", "Error",	JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				File file = new File(jtfFileName.getText());
				if (!file.exists()) {
					JOptionPane.showMessageDialog(null,	"File \"" + jtfFileName.getText() + "\" doesn't exist!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (file.isDirectory()) {
					JOptionPane.showMessageDialog(
							null, "\"" + jtfFileName.getText() + "\" is a directory!\nPlease enter filename.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Scanner input = new Scanner(file);
					StringBuilder data = new StringBuilder("");
					while (input.hasNextLine()) {
						data.append(input.nextLine());
						data.append("\n");
					}
					
					int[] count = new int[26];
					for (int i = 0; i < data.length(); i++) {
						char character = data.charAt(i);
						if (character >= 'A' && character <= 'Z') {
							count[character - 'A']++;
						} else if (character >= 'a' && character <= 'z') {
							count[character - 'a']++;
						}
					}
					histogram.showHistogram(count);
					
					input.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "File \"" + jtfFileName.getText() + "\" not found!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		choseFilePanel.add(jlblFileName);
		choseFilePanel.add(jtfFileName);
		choseFilePanel.add(jbtView);
		add(choseFilePanel, BorderLayout.SOUTH);

		// view file panel
		add(histogram, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		Exercise05 frame = new Exercise05();
		frame.setTitle("Exercise05");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class Histogram extends JPanel {

		private static final long serialVersionUID = 1L;
		// Count the occurrence of 26 letters
		private int[] count;

		/** Set the count and display histogram */
		public void showHistogram(int[] count) {
			this.count = count;
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (count == null)
				return; // No display if count is null

			super.paintComponent(g);

			// Find the panel size and bar width and interval dynamically
			int width = getWidth();
			int height = getHeight();
			int interval = (width - 40) / count.length;
			int individualWidth = (int) (((width - 40) / 24) * 0.60);

			// Find the maximum count. The maximum count has the highest bar
			int maxCount = 0;
			for (int i = 0; i < count.length; i++) {
				if (maxCount < count[i])
					maxCount = count[i];
			}

			// x is the start position for the first bar in the histogram
			int x = 30;

			// Draw a horizontal base line
			g.drawLine(10, height - 45, width - 10, height - 45);
			for (int i = 0; i < count.length; i++) {
				// Find the bar height
				int barHeight = (int) (((double) count[i] / (double) maxCount) * (height - 55));

				// Display a bar (i.e. rectangle)
				g.drawRect(x, height - 45 - barHeight, individualWidth,
						barHeight);

				// Display a letter under the base line
				g.drawString((char) (65 + i) + "", x, height - 30);

				// Move x for displaying the next character
				x += interval;
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}
	}

}
