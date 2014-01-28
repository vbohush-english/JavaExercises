package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise18 extends JApplet {
	private static final long serialVersionUID = 1L;
	private String[] countries;
	private DescriptionPanel descriptionPanel;
	private int currentFlag = -1;
	private int numberOfCountries;
	
	@Override
	public void init() {
		numberOfCountries = Integer.parseInt(getParameter("numberOfCountries")); 
		countries = new String[numberOfCountries * 3];
		for (int i = 0; i < numberOfCountries; i++) {
			countries[3 * i] = getParameter("name" + i);
			countries[3 * i + 1] = getParameter("description" + i);
			countries[3 * i + 2] = "image\\flag" + i + ".gif";
		}
		changeFlag();
	}
	
	private void changeFlag() {
		currentFlag++;
		if (currentFlag >= numberOfCountries) {
			currentFlag = 0;
		}
		descriptionPanel.setTitle(countries[3 * currentFlag]);
		descriptionPanel.setDescription(countries[3 * currentFlag + 1]);
		java.net.URL url = this.getClass().getResource(countries[3 * currentFlag + 2]);
		ImageIcon imageIcon = new ImageIcon(url);
		descriptionPanel.setImageIcon(imageIcon);
	}

	public Exercise18() {
		descriptionPanel = new DescriptionPanel();
		add(descriptionPanel);
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
			jlblImageTitle.addMouseListener(new MouseAdapter() {				
				@Override
				public void mouseReleased(MouseEvent e) {
					changeFlag();
				}
			});
			jtaDescription.addMouseListener(new MouseAdapter() {				
				@Override
				public void mouseReleased(MouseEvent e) {
					changeFlag();
				}
			});
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