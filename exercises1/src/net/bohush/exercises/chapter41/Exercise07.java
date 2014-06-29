package net.bohush.exercises.chapter41;

import java.sql.*;
import java.io.*;

import javax.swing.*;

import com.sun.rowset.JdbcRowSetImpl;

import java.awt.*;
import java.awt.event.*;

public class Exercise07 extends JApplet {
	private static final long serialVersionUID = 1L;

	// Prepared statement
	private DescriptionPanel descriptionPanel1 = new DescriptionPanel();
	private JdbcRowSetImpl rowSet = new JdbcRowSetImpl();
	private JComboBox<String> jcboCountry = new JComboBox<String>();

	/** Creates new form StoreAndRetrieveImage */
	public Exercise07() {
		try {
			connectDB(); // Connect to DB
			storeDataToTable(); // Store data to the table (including image)
			fillDataInComboBox(); // Fill in combo box
			retrieveFlagInfo((String) (jcboCountry.getSelectedItem()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		jcboCountry.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				retrieveFlagInfo((String) (evt.getItem()));
			}
		});

		add(jcboCountry, BorderLayout.NORTH);
		add(descriptionPanel1, BorderLayout.CENTER);
	}

	private void connectDB() throws Exception {
		// Load the driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		rowSet.setUrl("jdbc:mysql://localhost/javabook");
		rowSet.setUsername("scott");
		rowSet.setPassword("tiger");
		rowSet.setCommand("select * from Country");
		rowSet.execute();
		System.out.println("Database connected");
	}

	private void storeDataToTable() {
		String[] countries = { "Canada", "UK", "USA", "Germany", "Indian",
				"China" };

		String[] imageFilenames = { "image/ca.gif", "image/uk.gif",
				"image/us.gif", "image/germany.gif", "image/india.gif",
				"image/china.gif" };

		String[] descriptions = {
				"A text to describe Canadian " + "flag is omitted",
				"British flag ...", "American flag ...", "German flag ...",
				"Indian flag ...", "Chinese flag ..." };

		try {
			
			for (int i = 0; i < countries.length; i++) {
				rowSet.moveToInsertRow();
				rowSet.updateString(1, countries[i]);
				java.net.URL url = this.getClass().getResource(imageFilenames[i]);
				InputStream inputImage = url.openStream();
				rowSet.updateBinaryStream(2, inputImage, (int)(inputImage.available()));
				rowSet.updateString(3, descriptions[i]);
				rowSet.insertRow();
			}

			System.out.println("Table Country populated");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void fillDataInComboBox() throws Exception {
		rowSet.setCommand("select name from Country");
		rowSet.execute();
		rowSet.beforeFirst();
		
		while (rowSet.next()) {
			jcboCountry.addItem(rowSet.getString(1));
		}
	}

	private void retrieveFlagInfo(String name) {
		try {
			rowSet.setCommand("select flag, description from Country where name = \'" + name + "\'");
			rowSet.execute();
			rowSet.beforeFirst();
			
			if (rowSet.next()) {
				Blob blob = rowSet.getBlob(1);
				ImageIcon imageIcon = new ImageIcon(blob.getBytes(1,
						(int) blob.length()));
				descriptionPanel1.setImageIcon(imageIcon);
				descriptionPanel1.setName(name);
				String description = rowSet.getString(2);
				descriptionPanel1.setDescription(description);
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public static void main(String[] args) {
		Exercise07 applet = new Exercise07();
		JFrame frame = new JFrame();
		frame.getContentPane().add(applet);
		frame.setDefaultCloseOperation(3);
		frame.setTitle("Exercise07");
		frame.setSize(400, 320);
		frame.setVisible(true);
	}

	public class DescriptionPanel extends JPanel {
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