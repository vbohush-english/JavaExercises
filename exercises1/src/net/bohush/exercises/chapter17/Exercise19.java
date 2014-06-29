package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Exercise19 extends JFrame {

	private static final long serialVersionUID = 1L;

	// Declare an array of Strings for flag titles
	private String[] flagTitles = { "Canada", "China", "Denmark", "France",
			"Germany", "India", "Norway", "United Kingdom",
			"United States of America" };

	// Declare an ImageIcon array for the national flags of 9 countries
	private ImageIcon[] flagImage = { new ImageIcon("image/17_19/ca.gif"),
			new ImageIcon("image/17_19/china.gif"),
			new ImageIcon("image/17_19/denmark.gif"),
			new ImageIcon("image/17_19/fr.gif"),
			new ImageIcon("image/17_19/germany.gif"),
			new ImageIcon("image/17_19/india.gif"),
			new ImageIcon("image/17_19/norway.gif"),
			new ImageIcon("image/17_19/uk.gif"),
			new ImageIcon("image/17_19/us.gif") };

	// Declare an array of strings for flag descriptions
	private String[] flagDescription = new String[9];

	// Declare and create a description panel
	private DescriptionPanel descriptionPanel = new DescriptionPanel();

	// Create a combo box for selecting countries
	private JComboBox<String> jcbo = new JComboBox<String>(flagTitles);

	public static void main(String[] args) throws FileNotFoundException {
		Exercise19 frame = new Exercise19();
		frame.pack();
		frame.setTitle("Exercise19");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public Exercise19() throws FileNotFoundException {
		// Set text description
		for (int i = 0; i < flagDescription.length; i++) {
			String fileName = "tmp/description" + i + ".txt";
			File tmpFile = new File(fileName);
			if (tmpFile.exists()) {
				Scanner input = new Scanner(tmpFile);
				StringBuilder stringBuilder = new StringBuilder("");
				while (input.hasNextLine()) {
					stringBuilder.append(input.nextLine());
					stringBuilder.append('\n');
				}
				input.close();
				flagDescription[i] = stringBuilder.toString();
			} else {
				flagDescription[i] = "";
			}
			
		}

		// Set the first country (Canada) for display
		setDisplay(0);

		// Add combo box and description panel to the frame
		add(jcbo, BorderLayout.NORTH);
		add(descriptionPanel, BorderLayout.CENTER);

		// Register listener
		jcbo.addItemListener(new ItemListener() {
			@Override
			/** Handle item selection */
			public void itemStateChanged(ItemEvent e) {
				setDisplay(jcbo.getSelectedIndex());
			}
		});
	}

	/** Set display information on the description panel */
	public void setDisplay(int index) {
		descriptionPanel.setTitle(flagTitles[index]);
		descriptionPanel.setImageIcon(flagImage[index]);
		descriptionPanel.setDescription(flagDescription[index]);
	}

	class DescriptionPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		/** Label for displaying an image icon and a title */
		private JLabel jlblImageTitle = new JLabel();

		/** Text area for displaying text */
		private JTextArea jtaDescription = new JTextArea();

		public DescriptionPanel() {
			// Center the icon and text and place the text under the icon
			jlblImageTitle.setHorizontalAlignment(JLabel.CENTER);
			jlblImageTitle.setHorizontalTextPosition(JLabel.CENTER);
			jlblImageTitle.setVerticalTextPosition(JLabel.BOTTOM);

			// Set the font in the label and the text field
			jlblImageTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
			jtaDescription.setFont(new Font("Serif", Font.PLAIN, 14));

			// Set lineWrap and wrapStyleWord true for the text area
			jtaDescription.setLineWrap(true);
			jtaDescription.setWrapStyleWord(true);
			jtaDescription.setEditable(false);

			// Create a scroll pane to hold the text area
			JScrollPane scrollPane = new JScrollPane(jtaDescription);

			// Set BorderLayout for the panel, add label and scrollpane
			setLayout(new BorderLayout(5, 5));
			add(scrollPane, BorderLayout.CENTER);
			add(jlblImageTitle, BorderLayout.WEST);
		}

		/** Set the title */
		public void setTitle(String title) {
			jlblImageTitle.setText(title);
		}

		/** Set the image icon */
		public void setImageIcon(ImageIcon icon) {
			jlblImageTitle.setIcon(icon);
		}

		/** Set the text description */
		public void setDescription(String text) {
			jtaDescription.setText(text);
		}
	}
}