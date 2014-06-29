package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise18 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea jta;
	private JButton jbtShowHistogram = new JButton("Show Histogram");
	private Histogram histogram = new Histogram();

	// Create a new frame to hold the histogram panel
	private JFrame histogramFrame = new JFrame();

	public Exercise18() {
		// Store text area in a scroll pane
		JScrollPane scrollPane = new JScrollPane(jta = new JTextArea());
		scrollPane.setPreferredSize(new Dimension(300, 200));
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);

		// Place scroll pane and button in the frame
		add(scrollPane, BorderLayout.CENTER);
		add(jbtShowHistogram, BorderLayout.SOUTH);

		// Register listener
		jbtShowHistogram.addActionListener(new ActionListener() {
			@Override
			/** Handle the button action */
			public void actionPerformed(ActionEvent e) {
				// Count the letters in the text area
				int[] count = countLetters();

				// Set the letter count to histogram for display
				histogram.showHistogram(count);

				// Show the frame
				histogramFrame.setVisible(true);
			}
		});

		// Create a new frame to hold the histogram panel
		histogramFrame.add(histogram);
		histogramFrame.pack();
		histogramFrame.setTitle("Histogram");
	}

	/** Count the letters in the text area */
	private int[] countLetters() {
		// Count for 26 letters
		int[] count = new int[26];

		// Get contents from the text area
		String text = jta.getText();

		// Count occurrence of each letter (case insensitive)
		for (int i = 0; i < text.length(); i++) {
			char character = text.charAt(i);

			if (character >= 'A' && character <= 'Z') {
				count[(int) character - 65]++; // The ASCII for 'A' is 65
			} else if (character >= 'a' && character <= 'z') {
				count[(int) character - 97]++; // The ASCII for 'a' is 97
			}
		}

		return count; // Return the count array
	}

	class Histogram extends JPanel {
		private static final long serialVersionUID = 1L;
		// Count the occurrence of 26 letters
		private int[] count;
		private Color[] colors;

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
			if (colors == null) {
				colors = new Color[count.length];
			}
			g.drawLine(10, height - 45, width - 10, height - 45);
			for (int i = 0; i < count.length; i++) {
				// Find the bar height
				int barHeight = (int) (((double) count[i] / (double) maxCount) * (height - 55));

				// Display a bar (i.e. rectangle)
				if (colors[i] == null) {
					colors[i] = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
				}
				g.setColor(colors[i]);
				g.fillRect(x, height - 45 - barHeight, individualWidth, barHeight);
				
				// Display a letter under the base line
				g.setColor(Color.BLACK);
				g.drawString((char) (65 + i) + "", x, height - 50 - barHeight);

				// Move x for displaying the next character
				x += interval;
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}
	}

	public static void main(String[] args) {
		Exercise18 frame = new Exercise18();
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise18");
		frame.pack();
		frame.setVisible(true);
	}
}